package com.byaxe.interview.searchengine.dto;

import com.byaxe.interview.searchengine.core.enums.Language;
import lombok.Data;

/**
 * Represents word and its metadata
 */
@Data
public class Word {
    private final Language language;
    private final String content;
}
