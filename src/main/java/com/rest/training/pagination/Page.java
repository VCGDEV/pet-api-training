package com.rest.training.pagination;

import java.util.Collection;

public interface Page<T> {
    Collection<T> getContent();
    int currentPage();
    int pageCount();
    int totalCount();
    int pageSize();
}
