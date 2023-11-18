package com.gusalbukrk.demo.config;

import java.io.Serializable;

import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import com.gusalbukrk.demo.model.ListingPurchase;

@Component
public class ListingPurchaseIdConverter implements BackendIdConverter {
  @Override
  public boolean supports(Class<?> delimiter) {
    return delimiter.equals(ListingPurchase.class);
  }

  @Override
  public Serializable fromRequestId(String id, Class<?> entityType) {
    if (id != null) {
      ListingPurchaseId ptid = new ListingPurchaseId();
      String[] idParts = id.split("-");
      ptid.setListingId(Long.parseLong(idParts[0]));
      ptid.setPurchaseId(Long.parseLong(idParts[1]));
      return ptid;
    }
    return BackendIdConverter.DefaultIdConverter.INSTANCE.fromRequestId(id, entityType);
  }

  @Override
  public String toRequestId(Serializable id, Class<?> entityType) {
    if (id instanceof ListingPurchaseId) {
      ListingPurchaseId ptid = (ListingPurchaseId) id;
      return String.format("%s-%s", ptid.getListingId(), ptid.getPurchaseId());
    }
    return BackendIdConverter.DefaultIdConverter.INSTANCE.toRequestId(id, entityType);
  }
}
