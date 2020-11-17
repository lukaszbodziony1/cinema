package com.ssi.cinema.repository;

import com.ssi.cinema.exception.genric.WrongOrderNameException;
import com.ssi.cinema.request.common.Filter;
import com.ssi.cinema.request.common.Order;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CommonRepository {
    protected String buildGetQuery(List<Filter> filters, Order order, String entityName) {
        String query = "from " + entityName;

        if (filters != null)
            query += buildFilterQuery(filters);

        if (isProperOrder(order)) {
            query += buildOrderQuery(order);
        }

        return query;
    }

    private String buildFilterQuery(List<Filter> filters) {
        StringBuilder sb = new StringBuilder();
        boolean firstIteration = true;

        for (Filter filter : filters) {
            if(firstIteration) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }

            sb.append(filter.getName());
            sb.append(" like '%");
            sb.append(filter.getValue());
            sb.append("%'");
            firstIteration = false;
        }

        return sb.toString();
    }

    private boolean isProperOrder(Order order) {
        if (order == null)
            return false;
        if (order.getOrderDirection() != null)
            if (!order.getOrderDirection().equals("asc") && !order.getOrderDirection().equals("desc")) {
                throw new WrongOrderNameException("Wrong order name!");
            }

        return true;
    }

    private String buildOrderQuery(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append(" order by ");
        sb.append(order.getOrderBy());
        sb.append(" ");
        if (order.getOrderDirection() != null)
            sb.append(order.getOrderDirection());

        return sb.toString();
    }
}
