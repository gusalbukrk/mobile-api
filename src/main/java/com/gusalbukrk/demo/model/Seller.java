package com.gusalbukrk.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;

@Entity
@PrimaryKeyJoinColumn(name = "user_id") // rename pk column to make it clear it's a foreign key
@Getter
public class Seller extends User {
  @Column(nullable = false, unique = true)
  private String cnpj;

  @OneToMany(mappedBy = "seller")
  private List<Listing> listings;
}
