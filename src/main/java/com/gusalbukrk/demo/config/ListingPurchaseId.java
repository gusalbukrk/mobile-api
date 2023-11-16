package com.gusalbukrk.demo.config;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class ListingPurchaseId implements Serializable {
  private long listing;
  private long purchase;
}
