package com.gusalbukrk.demo.model;

import com.gusalbukrk.demo.config.ListingPurchaseId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(ListingPurchaseId.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor // https://stackoverflow.com/a/35602246 https://stackoverflow.com/a/51122581
public class ListingPurchase {
  @Id
  @ManyToOne
  private Listing listing;

  @Id
  @ManyToOne
  private Purchase purchase;

  @Column(nullable = false) 
  private int quantity;
}
