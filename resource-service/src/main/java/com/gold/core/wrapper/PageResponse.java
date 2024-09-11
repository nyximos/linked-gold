package com.gold.core.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PageResponse<T> {

    private long total;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> contents;

    public PageResponse(long total, List<T> contents) {
        this.total = total;
        this.contents = contents;
    }
}
