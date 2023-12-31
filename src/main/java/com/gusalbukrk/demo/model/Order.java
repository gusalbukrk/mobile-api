package com.gusalbukrk.demo.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor // https://stackoverflow.com/a/35602246 https://stackoverflow.com/a/51122581
@Table(name = "\"order\"") // order is a MySQL reserved word (https://stackoverflow.com/a/3611916)
public class Order {
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

  @OneToMany(mappedBy = "order")
  private List<OrderListing> orderListings;

  @OneToOne(mappedBy = "order")
  private Review review;
}
