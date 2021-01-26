package com.byaxe.interview.searchengine.service.api;

import com.byaxe.interview.searchengine.core.enums.Language;

import java.util.List;

public interface ISplitter {

    List<String> splitString(String string);

    Language getLanguage();
}
