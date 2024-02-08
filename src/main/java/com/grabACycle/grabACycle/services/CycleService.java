package com.grabACycle.grabACycle.services;

import com.grabACycle.grabACycle.entity.Cycle;

import java.util.List;
import java.util.Optional;

public interface CycleService {

    Cycle createCycle(Cycle cycle);

    List<Cycle> fetchCycles();

    Optional<Cycle> fetchCycleById(int cycleId);

    Cycle updateCycle(Cycle cycle);

    Cycle updateCycleById(int cycleId, Cycle cycle);

    void deleteCycle(int cycleId);
}
