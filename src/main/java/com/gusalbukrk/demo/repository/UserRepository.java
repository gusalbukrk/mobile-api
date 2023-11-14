package com.gusalbukrk.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.gusalbukrk.demo.model.User;

// if we don’t need all the resources defined in CrudRepository, we can extend [instead of the
// CrudRepository] the basic Repository interface and define only the resources we want
// an HTTP status 405 is returned when an endpoint without appropriate repository method is hit
public interface UserRepository extends Repository<User, Long> {
  // would receive 405 HTTP error if `Long` weren't capitalized
  Optional<User> findById(Long id);

  List<User> findAll();
}
