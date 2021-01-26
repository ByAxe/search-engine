package com.byaxe.interview.searchengine.service.api;

import com.byaxe.interview.searchengine.core.enums.Language;

public interface ILanguageIdentifier {

    /**
     * Try to guess the language by given set of characters
     *
     * @param characterGroup input
     * @return probably, correct language
     */
    Language identifyLanguage(String characterGroup);
}
