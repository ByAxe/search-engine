package com.byaxe.interview.searchengine.dto;

import com.byaxe.interview.searchengine.core.enums.Language;
import lombok.Builder;
import lombok.Data;

/**
 * Represents word and its metadata
 */
@Builder
@Data
public class Word {
    private final Language language;
    private final String content;
}
