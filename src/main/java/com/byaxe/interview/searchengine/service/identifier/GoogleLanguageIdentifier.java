package com.byaxe.interview.searchengine.service.identifier;

import com.byaxe.interview.searchengine.core.enums.Language;
import com.byaxe.interview.searchengine.service.api.ILanguageIdentifier;
import org.springframework.stereotype.Service;

@Service
public class GoogleLanguageIdentifier implements ILanguageIdentifier {

    /**
     * Make a request to google translate api
     * That can identify entered language
     *
     * @param characterGroup input
     * @return
     */
    @Override
    public Language identifyLanguage(String characterGroup) {
        // TODO implement
        return null;
    }
}
