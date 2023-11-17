package com.gusalbukrk.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.gusalbukrk.demo.config.ListingPurchaseId;
import com.gusalbukrk.demo.model.ListingPurchase;

public interface ListingPurchaseRepository extends CrudRepository<ListingPurchase, ListingPurchaseId> {}
