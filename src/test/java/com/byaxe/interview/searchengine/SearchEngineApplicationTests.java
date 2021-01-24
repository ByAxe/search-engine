package com.byaxe.interview.searchengine;

import com.byaxe.interview.searchengine.core.SearchEngineUtils;
import com.byaxe.interview.searchengine.dto.DocumentReference;
import com.byaxe.interview.searchengine.dto.Word;
import com.byaxe.interview.searchengine.service.api.ISearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SearchEngineApplicationTests {

    @Autowired
    private ISearchService searchService;

    @Test
    void whenNormalPhraseSplit_shouldReturnSuccessList() {
        final String testString = "Hello, my lovely World!";

        final List<String> expected = Arrays.asList("hello", "my", "lovely", "world");

        List<Word> actualWords = searchService.splitQuery(testString);

        List<String> actual = SearchEngineUtils.listOfWordsToListOfStrings(actualWords);

        Assert.isTrue(expected.equals(actual), "Actual result is not equal to expected");
    }

    @Test
    void whenNormalPhraseGetFirstDocument_shouldReturnSuccessNoResults() {
        final String testString = "Hello, my lovely World!";

        DocumentReference document = searchService.getFirstDocument(testString);

        Assert.isNull(document, "Document should be null");
    }

    @Test
    void whenNormalPhraseGetFirstNDocuments_shouldReturnSuccessNoResults() {
        final String testString = "Hello, my lovely World!";
        final long n = 10;

        List<DocumentReference> documents = searchService.getFirstNDocuments(testString, n);

        Assert.isTrue(documents.isEmpty(), "There should be no documents found");
    }

    @Test
    void whenNormalPhraseGetAllDocuments_shouldReturnSuccessNoResults() {
        final String testString = "Hello, my lovely World!";

        List<DocumentReference> documents = searchService.getAllDocuments(testString);

        Assert.isTrue(documents.isEmpty(), "There should be no documents found");
    }
}
