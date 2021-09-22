package com.rest.training.dto.hateoas;

public interface LinkItem {
    void accept(LinkVisitor visitor);
}
