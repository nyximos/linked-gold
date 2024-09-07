package com.gold.core.wrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageResponse {
    private int currentPage;
    private int lastPage;
    private int limit;
    private int offset;
    private int total;

    public PageResponse(int currentPage, int lastPage, int limit, int offset, int total) {
        this.currentPage = currentPage;
        this.lastPage = lastPage;
        this.limit = limit;
        this.offset = offset;
        this.total = total;
    }

    private static int calculateCurrentPage(int page) {
        return page + 1;
    }

    private static int calculateMaxPage(long total, int limit) {
        return (int) Math.ceil((double) total / limit);
    }

}
