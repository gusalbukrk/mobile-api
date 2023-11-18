package com.gusalbukrk.demo.config;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ListingPurchaseId implements Serializable {
  @Column(name = "listing_id")
  private long listingId;

  @Column(name = "purchase_id")
  private long purchaseId;
}
