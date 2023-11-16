package com.gusalbukrk.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false) 
  private String name;

  @ManyToOne
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> subcategories;
}
