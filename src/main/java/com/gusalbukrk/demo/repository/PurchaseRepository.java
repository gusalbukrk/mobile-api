package com.gusalbukrk.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.gusalbukrk.demo.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {}
