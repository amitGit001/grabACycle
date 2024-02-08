package com.grabACycle.grabACycle.dao;

import com.grabACycle.grabACycle.entity.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleRepository extends JpaRepository<Cycle, Integer> {
}
