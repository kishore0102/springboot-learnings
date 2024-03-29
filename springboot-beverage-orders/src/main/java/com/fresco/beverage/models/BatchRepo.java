package com.fresco.beverage.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepo extends JpaRepository<Batch, Integer> {
}
