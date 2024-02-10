package com.grabACycle.grabACycle.services.implementation;

import com.grabACycle.grabACycle.dao.CycleRepository;
import com.grabACycle.grabACycle.entity.Cycle;
import com.grabACycle.grabACycle.services.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"cycle"})
public class CycleServiceImplementation implements CycleService {

    @Autowired
    private CycleRepository cycleRepository;

    @Override
    @CachePut(cacheNames = "cycle")
    public Cycle createCycle(Cycle cycle)
    {
        Cycle tempCycle = new Cycle();

        tempCycle.setId(cycle.getId());
        tempCycle.setName(cycle.getName());
        tempCycle.setModel(cycle.getModel());
        tempCycle.setType(cycle.getType());
        tempCycle.setBookingStatus(false);

        return cycleRepository.save(tempCycle);
    }

    @Override
    @Cacheable(cacheNames = "cycle")
    public List<Cycle> fetchCycles()
    {
        return cycleRepository.findAll();
    }

    @Override
//    @Cacheable(cacheNames = "cycle",unless="#result == null")
    public Cycle fetchCycleById(int cycleId)
    {
        Optional<Cycle> optional=cycleRepository.findById(cycleId);

        Cycle cycle=null;
        if(optional.isPresent())
        {
            cycle=optional.get();
        }

        else{
            throw new RuntimeException("Cycle not found for id:"+cycleId);
        }

        return cycle;
    }

    @Override
    @CachePut(cacheNames = "cycle")
    public Cycle updateCycle(Cycle cycle)
    {
        Cycle tempCycle = new Cycle();

        tempCycle.setId(cycle.getId());
        tempCycle.setName(cycle.getName());
        tempCycle.setModel(cycle.getModel());
        tempCycle.setType(cycle.getType());
        tempCycle.setBookingStatus(false);

        return cycleRepository.save(tempCycle);
    }

    @Override
    public Cycle updateCycleById(int cycleId, Cycle cycle)
    {
        Cycle tempCycle = cycleRepository.findById(cycleId).get();

        tempCycle.setName(cycle.getName());
        tempCycle.setModel(cycle.getModel());
        tempCycle.setType(cycle.getType());
        tempCycle.setBookingStatus(false);

        return cycleRepository.save(tempCycle);
    }

    @Override
    public Cycle updateBookingStatus(int cycleId) {

        Optional<Cycle> optional = cycleRepository.findById(cycleId);

        if(optional.isPresent()){

            Cycle tempCycle=optional.get();

            boolean currentStatus = tempCycle.isBookingStatus();

            tempCycle.setBookingStatus(!currentStatus);

             cycleRepository.save(tempCycle);

             return tempCycle;
        }

        return null;


    }

    @Override
    @CacheEvict(cacheNames = "user", allEntries = true)
    public void deleteCycleById(int cycleId)
    {
       cycleRepository.deleteById(cycleId);
    }

    @Override
    public Page<Cycle> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable= PageRequest.of(pageNo-1, pageSize, sort);

        return cycleRepository.findAll(pageable);
    }


}
