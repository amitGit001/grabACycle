package com.grabACycle.grabACycle.dao;

import com.grabACycle.grabACycle.entity.Cycle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CycleRepository extends JpaRepository<Cycle, Integer> {

    //search
    @Query("SELECT cycle FROM Cycle cycle WHERE CONCAT(cycle.id, ' ', cycle.name, ' ', cycle.model, ' ', cycle.type) LIKE %?1%")
    public Page<Cycle> search(String keyword, Pageable pageable);

}
