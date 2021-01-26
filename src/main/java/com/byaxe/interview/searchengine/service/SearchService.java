package com.byaxe.interview.searchengine.service;

import com.byaxe.interview.searchengine.core.enums.Language;
import com.byaxe.interview.searchengine.dto.DocumentReference;
import com.byaxe.interview.searchengine.dto.Word;
import com.byaxe.interview.searchengine.repository.api.ISearchRepository;
import com.byaxe.interview.searchengine.service.api.ASearchService;
import com.byaxe.interview.searchengine.service.api.ILanguageIdentifier;
import com.byaxe.interview.searchengine.service.api.ISplitter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
@Service
public class SearchService extends ASearchService {
    private final Map<Language, ISplitter> splitters;
    private final ISplitter characterGroupSplitter;
    private final ISplitter defaultSplitter;
    private final ISearchRepository searchRepository;

    private final ILanguageIdentifier languageIdentifier;

    public SearchService(List<ISplitter> splittersList, ISearchRepository searchRepository,
                         @Qualifier("libraryLanguageIdentifier") ILanguageIdentifier languageIdentifier) {

        this.splitters = splittersList.stream()
                .collect(Collectors.toMap(ISplitter::getLanguage, Function.identity()));
        this.searchRepository = searchRepository;

        this.characterGroupSplitter = splitters.get(Language.NONE);
        this.defaultSplitter = splitters.get(Language.DEFAULT);

        this.languageIdentifier = languageIdentifier;
    }

    /**
     * Perform basic normalisation of the word
     * To make search simpler
     *
     * @param originalWord source word
     * @return normalized word
     */
    @Override
    protected Word normalizeWord(Word originalWord) {
        // https://docs.oracle.com/javase/tutorial/i18n/text/normalizerapi.html
        final String normalizedContent = Normalizer.normalize(originalWord.getContent(), Normalizer.Form.NFKD);

        return Word.builder()
                .content(normalizedContent)
                .language(originalWord.getLanguage())
                .build();
    }

    @Override
    public @NonNull List<Word> splitQuery(@NonNull String query) {
        // Split on character groups
        final List<String> characterGroups = characterGroupSplitter.splitString(query);

        final List<Word> words = characterGroups.stream()
                // Identify language for each character group
                .map(s -> Pair.of(languageIdentifier.identifyLanguage(s), s))

                // Build a pairs, as they are still not necessarily words,
                // but at least we know the probable language for each character group
                .map(p -> splitters.getOrDefault(p.getLeft(), defaultSplitter)
                        .splitString(p.getRight()))

                // Flatten all new word groups into one stream
                .flatMap(List::stream)

                // Identify language for each word (redundancy for better quality)
                .map(s -> Word.builder()
                        .content(s)
                        .language(languageIdentifier.identifyLanguage(s))
                        .build())
                .collect(Collectors.toList());

        return words;
    }

    @Override
    public @NonNull List<Word> splitQueryIntoNormalizedWords(@NonNull String query) {
        final List<Word> words = splitQuery(query);

        final List<Word> normalizedWords = normalizeWords(words);

        return normalizedWords;
    }

    @Override
    public @NonNull List<DocumentReference> getAllDocuments(@NonNull String query) {
        return getFirstNDocuments(query, -1);
    }

    @Override
    public @NonNull List<DocumentReference> getFirstNDocuments(@NonNull String query, long n) {
        final List<Word> queryAsListOfWords = splitQueryIntoNormalizedWords(query);

        final List<DocumentReference> firstNDocuments = searchRepository.getFirstNDocuments(queryAsListOfWords, n);

        return firstNDocuments;
    }

    @Override
    public DocumentReference getFirstDocument(@NonNull String query) {
        final List<DocumentReference> firstDocument = getFirstNDocuments(query, 1);

        return firstDocument.size() != 0 ? firstDocument.get(0) : null;
    }
}
