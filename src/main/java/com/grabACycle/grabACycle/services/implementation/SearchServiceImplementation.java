package com.grabACycle.grabACycle.services.implementation;


import com.grabACycle.grabACycle.dao.CycleRepository;
import com.grabACycle.grabACycle.entity.Cycle;
import com.grabACycle.grabACycle.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImplementation implements SearchService {

    private CycleRepository cycleRepository;

    @Autowired
    public SearchServiceImplementation(CycleRepository cycleRepository) {
        this.cycleRepository = cycleRepository;
    }

    //Searching
    @Override
    public Page<Cycle> searchAllCyclesPaginated(int pageNo, int pageSize, String sortField, String sortDir, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable= PageRequest.of(pageNo-1, pageSize, sort);

        if(keyword != null)
        {
            return cycleRepository.search(keyword,pageable);
        }

        return cycleRepository.findAll(pageable);

    }

}
