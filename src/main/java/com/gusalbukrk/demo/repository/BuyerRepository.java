package com.gusalbukrk.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.gusalbukrk.demo.model.Buyer;

public interface BuyerRepository extends CrudRepository<Buyer, Long> {}
