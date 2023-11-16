package com.gusalbukrk.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.gusalbukrk.demo.model.Listing;

public interface ListingRepository extends CrudRepository<Listing, Long> {}
