package com.grabACycle.grabACycle.restcontroller;

import com.grabACycle.grabACycle.entity.Cycle;
import com.grabACycle.grabACycle.services.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CycleRestController {


    @Autowired
    private CycleService cycleService;

    @PostMapping("/cycle")
    public Cycle createCycle(@RequestBody Cycle cycle)
    {
        return cycleService.createCycle(cycle);
    }

    @GetMapping("/cycle")
    public List<Cycle> getAllCycles()
    {
        return cycleService.fetchCycles();
    }

    @GetMapping("/cycle/{cycleId}")
    public Optional<Cycle> getCycleById(@PathVariable int cycleId)
    {
        return Optional.ofNullable(cycleService.fetchCycleById(cycleId));
    }

    @PutMapping("/cycle")
    public Cycle updateCycle(@RequestBody Cycle cycle)
    {
        return cycleService.updateCycle(cycle);
    }

    @PatchMapping("/cycle/{cycleId}")
    public Cycle updateCycleById(@PathVariable int cycleId, @RequestBody Cycle cycle)
    {
        return cycleService.updateCycleById(cycleId, cycle);
    }

    @DeleteMapping("/cycle/{cycleId}")
    public void deleteCycle(@PathVariable int cycleId)
    {
        cycleService.deleteCycleById(cycleId);
    }
}
