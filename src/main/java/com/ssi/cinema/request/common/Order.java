package com.ssi.cinema.request.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Order {
    @JsonProperty("order_by")
    private String orderBy;

    @JsonProperty("order_direction")
    private String orderDirection;
}
