package com.byaxe.interview.searchengine.dto;

import com.byaxe.interview.searchengine.core.enums.Language;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * Indexed and normalized document
 */
@Data
public class IndexedDocument {
    private UUID id;
    private Language mainLanguage;
    private List<String> words;
    private long lengthInSymbols;
}
