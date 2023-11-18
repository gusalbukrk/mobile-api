package com.gusalbukrk.demo.config;

import java.io.Serializable;

import com.gusalbukrk.demo.model.Listing;
import com.gusalbukrk.demo.model.Purchase;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class ListingPurchaseId implements Serializable {
  @ManyToOne
  private Listing listing;

  @ManyToOne
  private Purchase purchase;
}
