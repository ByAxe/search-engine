package com.byaxe.interview.searchengine.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RawDocument {
    private UUID id;
    private byte[] content;
    private long size;
    private String docExtension;
}
