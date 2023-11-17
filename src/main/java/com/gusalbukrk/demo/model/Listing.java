package com.gusalbukrk.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor // https://stackoverflow.com/a/35602246 https://stackoverflow.com/a/51122581
public class Listing {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false) 
  private String name;

  private String description;

  @Column(nullable = false) 
  private float price;

  @Column(nullable = false) 
  private int quantity;

  @ManyToOne
  @JoinColumn(referencedColumnName = "user_id") 
  private Seller seller;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
    name = "listing_tag",
    joinColumns = @JoinColumn(name = "listing_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private List<Tag> tags;

  @OneToMany(mappedBy = "listing")
  private List<ListingPurchase> listingPurchases;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private Category category;
}
