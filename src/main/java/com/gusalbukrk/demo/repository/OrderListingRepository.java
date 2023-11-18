package com.gusalbukrk.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gusalbukrk.demo.model.OrderListing;
import com.gusalbukrk.demo.model.OrderListingId;

@RepositoryRestResource(collectionResourceRel = "ordersListings", path = "ordersListings")
public interface OrderListingRepository extends CrudRepository<OrderListing, OrderListingId> {}
