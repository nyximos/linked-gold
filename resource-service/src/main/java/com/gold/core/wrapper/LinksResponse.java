package com.gold.core.wrapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinksResponse {
    private String self;
    private String next;
    private String prev;
    private String first;
    private String last;

    public LinksResponse(String self, String next, String prev, String first, String last) {
        this.self = self;
        this.next = next;
        this.prev = prev;
        this.first = first;
        this.last = last;
    }
}