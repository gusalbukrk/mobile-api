package com.gusalbukrk.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.gusalbukrk.demo.model.Seller;

public interface SellerRepository extends CrudRepository<Seller, Long> {}
