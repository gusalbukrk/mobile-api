package com.gusalbukrk.demo.model;

import com.gusalbukrk.demo.config.ListingPurchaseId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@IdClass(ListingPurchaseId.class)
@Getter
public class ListingPurchase {
  // @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  // private long id;

  @Id
  @ManyToOne
  private Listing listing;

  @Id
  @ManyToOne
  private Purchase purchase;

  // @Column(nullable = false) 
  // private String name;
}