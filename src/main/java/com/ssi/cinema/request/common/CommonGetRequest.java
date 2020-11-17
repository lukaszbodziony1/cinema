package com.ssi.cinema.request.common;

import lombok.Getter;

import java.util.List;

@Getter
public class CommonGetRequest {
    private List<Filter> filters;
    private Order order;
}
