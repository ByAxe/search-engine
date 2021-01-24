package com.byaxe.interview.searchengine.dto;

import lombok.Data;

import java.util.UUID;

/**
 * Part of document from storage
 */
@Data
public class Document {

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
    private String contentPlusSurrounding;
}
