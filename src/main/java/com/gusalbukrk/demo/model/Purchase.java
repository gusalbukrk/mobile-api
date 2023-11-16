package com.gusalbukrk.demo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

@Entity
@Getter
public class Purchase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false) 
  @Temporal(TemporalType.DATE)
  private Date date;

  @Column(nullable = false) 
  private float total;

  @ManyToOne
  @JoinColumn(referencedColumnName = "user_id") 
  private Buyer buyer;
}
