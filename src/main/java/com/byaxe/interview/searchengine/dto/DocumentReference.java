package com.byaxe.interview.searchengine.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * Part of document from storage (reference to the real Document)
 */
@Data
@Builder
public class DocumentReference {

    /**
     * Document unique id
     */
    private UUID id;

    /**
     * Symbol with that starts exact match within found document
     */
    private long startMatch;

    /**
     * Symbol with that ends exact match within found document
     */
    private long endMatch;

    /**
     * Like in Google Search, when you see the first result as paragraph
     * within found context (searched phrase inside of it)
     */
    private List<String> coincidedContent;
}
