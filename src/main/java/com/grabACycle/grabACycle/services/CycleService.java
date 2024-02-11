package com.grabACycle.grabACycle.services;

import com.grabACycle.grabACycle.entity.Cycle;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CycleService {

    Cycle createCycle(Cycle cycle);

    List<Cycle> fetchCycles();

    Cycle fetchCycleById(int cycleId);

    Cycle updateCycle(Cycle cycle);

    Cycle updateCycleById(int cycleId, Cycle cycle);

    Cycle updateBookingStatus(int cycleId, String userEmail);

    void deleteCycleById(int cycleId);

    Page<Cycle> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);


}
