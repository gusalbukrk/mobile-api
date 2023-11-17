package com.gusalbukrk.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "user_id") // rename pk column to make it clear it's a foreign key
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class Seller extends User {
  @Column(nullable = false, unique = true)
  private String cnpj;

  @OneToMany(mappedBy = "seller")
  private List<Listing> listings;
}
