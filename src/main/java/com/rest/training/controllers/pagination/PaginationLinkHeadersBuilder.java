package com.rest.training.controllers.pagination;

import java.util.stream.Stream;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;

public class PaginationLinkHeadersBuilder {
    private static final String PREV_REL = "prev"; //<http://localhost:8081/pet-api-training/webapi/pets/pages>; rel="prev"
    private static final String NEXT_REL = "next"; //<http://localhost:8081/pet-api-training/webapi/pets/pages>; rel="next"
    private static final String FIRST_REL = "first"; //<http://localhost:8081/pet-api-training/webapi/pets/pages>; rel="first"
    private static final String LAST_REL = "last"; //<http://localhost:8081/pet-api-training/webapi/pets/pages>; rel="last"

    private final int pageCount;
    private final int currentPage;

    public PaginationLinkHeadersBuilder(int pageCount, int currentPage) {
        this.pageCount = pageCount;
        this.currentPage = currentPage;
    }

    public Stream<Link> buildLinks(UriInfo uriInfo) {
        if(pageCount == 1 && currentPage ==1) {
            return Stream.empty();
        }

        Stream.Builder<Link> streamLinkBuilder = Stream.builder();


        // TODO add first page to the links stream
        if(currentPage > 1) {
            streamLinkBuilder.accept(//pets/pages?params
                Link.fromUriBuilder(
                    uriInfo.getRequestUriBuilder()
                    .replaceQueryParam("page", currentPage - 1)
                ).rel(PREV_REL).build()
            );
        }

        // TODO add validation for next and last pages
        streamLinkBuilder.accept(//pets/pages?params
                Link.fromUriBuilder(
                    uriInfo.getRequestUriBuilder()
                    .replaceQueryParam("page", currentPage + 1)
                ).rel(NEXT_REL).build()
            );

        //TODO add last page to the links stream

        return streamLinkBuilder.build();
    }
}
