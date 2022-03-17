package com.example.integration.routes;

import com.example.integration.model.Order;
import com.example.integration.model.OrderLine;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class PriceAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange){
        OrderLine newBody = newExchange.getIn().getBody(OrderLine.class);
        if (oldExchange == null) {
            Order order = new Order();
            order.setOrderNo(UUID.randomUUID().toString());
            order.setOrderDate(Instant.now().toString());
            order.setOrderPrice(newBody.getPrice());
            order.addOrderLine(newBody);

            newExchange.getIn().setBody(order, Order.class);
            return newExchange;
        }
        OrderLine newOrderLine = newExchange.getIn().getBody(OrderLine.class);
        Order order = oldExchange.getIn().getBody(Order.class);
        order.setOrderPrice(order.getOrderPrice() + newOrderLine.getPrice());
        order.addOrderLine(newOrderLine);
        oldExchange.getIn().setBody(order);
        return oldExchange;
    }
}
