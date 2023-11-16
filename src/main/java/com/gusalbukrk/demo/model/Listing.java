package com.gusalbukrk.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
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

  @ManyToMany
  @JoinTable(
    name = "listing_tag",
    joinColumns = @JoinColumn(name = "listing_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private List<Tag> tags;
}
