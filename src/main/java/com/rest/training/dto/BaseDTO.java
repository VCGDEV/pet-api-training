package com.rest.training.dto;

import java.util.List;

import javax.ws.rs.core.Link;

import lombok.Data;

@Data
public class BaseDTO {
    private List<Link> links;
}
