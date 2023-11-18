package com.gusalbukrk.demo.config;

import java.io.Serializable;

import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import com.gusalbukrk.demo.model.OrderListing;
import com.gusalbukrk.demo.model.OrderListingId;

@Component
public class OrderListingIdConverter implements BackendIdConverter {
  @Override
  public boolean supports(Class<?> delimiter) {
    return delimiter.equals(OrderListing.class);
  }

  @Override
  public Serializable fromRequestId(String id, Class<?> entityType) {
    if (id != null) {
      OrderListingId ptid = new OrderListingId();
      String[] idParts = id.split("-");
      ptid.setOrderId(Long.parseLong(idParts[0]));
      ptid.setListingId(Long.parseLong(idParts[1]));
      return ptid;
    }
    return BackendIdConverter.DefaultIdConverter.INSTANCE.fromRequestId(id, entityType);
  }

  @Override
  public String toRequestId(Serializable id, Class<?> entityType) {
    if (id instanceof OrderListingId) {
      OrderListingId ptid = (OrderListingId) id;
      return String.format("%s-%s", ptid.getOrderId(), ptid.getListingId());
    }
    return BackendIdConverter.DefaultIdConverter.INSTANCE.toRequestId(id, entityType);
  }
}
