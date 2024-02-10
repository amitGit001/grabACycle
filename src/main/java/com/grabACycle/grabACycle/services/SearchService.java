package com.grabACycle.grabACycle.services;

import com.grabACycle.grabACycle.entity.Cycle;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchService {



    Page<Cycle> searchAllCyclesPaginated(int pageNo, int pageSize, String sortField, String sortDir, String keyword);

}
