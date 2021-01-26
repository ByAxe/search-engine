package com.byaxe.interview.searchengine.service.identifier;

import com.byaxe.interview.searchengine.core.enums.Language;
import com.byaxe.interview.searchengine.service.api.ILanguageIdentifier;
import org.springframework.stereotype.Service;

@Service
public class LibraryLanguageIdentifier implements ILanguageIdentifier {

    @Override
    public Language identifyLanguage(String characterGroup) {
        // TODO implement
        return Language.ENGLISH;
    }
}
