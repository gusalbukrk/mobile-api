package com.gusalbukrk.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;

@Entity
@PrimaryKeyJoinColumn(name = "user_id") // rename pk column to make it clear it's a foreign key
@Getter
public class Buyer extends User {
  @Column(nullable = false, unique = true)
  private String cpf;
}
