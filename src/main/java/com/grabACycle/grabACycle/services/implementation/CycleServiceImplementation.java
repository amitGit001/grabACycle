package com.grabACycle.grabACycle.services.implementation;

import com.grabACycle.grabACycle.dao.CycleRepository;
import com.grabACycle.grabACycle.entity.Cycle;
import com.grabACycle.grabACycle.services.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CycleServiceImplementation implements CycleService {

    @Autowired
    private CycleRepository cycleRepository;

    @Override
    public Cycle createCycle(Cycle cycle)
    {
        Cycle tempCycle = new Cycle();

        tempCycle.setCycleId(cycle.getCycleId());
        tempCycle.setCycleName(cycle.getCycleName());
        tempCycle.setCycleModel(cycle.getCycleModel());
        tempCycle.setCycleType(cycle.getCycleType());
        tempCycle.setBookingStatus(false);

        return cycleRepository.save(tempCycle);
    }

    @Override
    public List<Cycle> fetchCycles()
    {
        return cycleRepository.findAll();
    }

    @Override
    public Optional<Cycle> fetchCycleById(int cycleId)
    {
        return cycleRepository.findById(cycleId);
    }

    @Override
    public Cycle updateCycle(Cycle cycle)
    {
        Cycle tempCycle = new Cycle();

        tempCycle.setCycleId(cycle.getCycleId());
        tempCycle.setCycleName(cycle.getCycleName());
        tempCycle.setCycleModel(cycle.getCycleModel());
        tempCycle.setCycleType(cycle.getCycleType());
        tempCycle.setBookingStatus(false);

        return cycleRepository.save(tempCycle);
    }

    @Override
    public Cycle updateCycleById(int cycleId, Cycle cycle)
    {
        Cycle tempCycle = cycleRepository.findById(cycleId).get();

        tempCycle.setCycleId(cycle.getCycleId());
        tempCycle.setCycleName(cycle.getCycleName());
        tempCycle.setCycleModel(cycle.getCycleModel());
        tempCycle.setCycleType(cycle.getCycleType());
        tempCycle.setBookingStatus(false);

        return cycleRepository.save(tempCycle);
    }

    @Override
    public void deleteCycle(int cycleId)
    {
        cycleRepository.deleteById(cycleId);
    }
}
