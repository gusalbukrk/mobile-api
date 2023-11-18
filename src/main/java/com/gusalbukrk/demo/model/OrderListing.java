package com.gusalbukrk.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor // https://stackoverflow.com/a/35602246 https://stackoverflow.com/a/51122581
public class OrderListing {
  @EmbeddedId
  private OrderListingId id;

  @ManyToOne
  @JoinColumn(name = "order_id", insertable = false, updatable = false)
  private Order order;

  @ManyToOne
  @JoinColumn(name = "listing_id", insertable = false, updatable = false)
  private Listing listing;

  @Column(nullable = false) 
  private int quantity;
}
