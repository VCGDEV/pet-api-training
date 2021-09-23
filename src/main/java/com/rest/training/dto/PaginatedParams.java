package com.rest.training.dto;

import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import lombok.Data;

@Data
public class PaginatedParams {
    @QueryParam("search") String search;
    @Min(value = 1, message = "5000")
    @QueryParam("page") @DefaultValue("1") Integer page;
    @QueryParam("size") @DefaultValue("5") Integer size;

}
