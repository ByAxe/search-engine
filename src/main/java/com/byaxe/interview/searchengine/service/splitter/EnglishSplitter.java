package com.byaxe.interview.searchengine.service.splitter;

import com.byaxe.interview.searchengine.core.enums.Language;
import com.byaxe.interview.searchengine.service.api.ISplitter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EnglishSplitter implements ISplitter {

    @Override
    public List<String> splitString(String string) {
        // TODO implement
        return Arrays.asList(string);
    }

    @Override
    public Language getLanguage() {
        return Language.ENGLISH;
    }
}
