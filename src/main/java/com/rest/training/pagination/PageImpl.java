package com.rest.training.pagination;

import java.util.Collection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PageImpl<T> implements Page<T> {
  
    private Collection<T> content;
    private int currentPage;
    private int pageCount;
    private int totalCount;
    private int size;
    
    @Override
    public Collection<T> getContent() {
        return this.content;
    }

    @Override
    public int pageCount() {
        return this.pageCount;
    }

    @Override
    public int totalCount() {
        return this.totalCount;
    }

    @Override
    public int currentPage() {
        return this.currentPage;
    }

    @Override
    public int pageSize() {
        return this.size;
    }
}
