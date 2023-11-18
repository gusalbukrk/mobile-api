package com.gusalbukrk.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.gusalbukrk.demo.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {}
