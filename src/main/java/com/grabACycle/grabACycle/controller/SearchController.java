package com.grabACycle.grabACycle.controller;

import com.grabACycle.grabACycle.dao.CycleRepository;
import com.grabACycle.grabACycle.entity.Cycle;
import com.grabACycle.grabACycle.services.CycleService;
import com.grabACycle.grabACycle.services.SearchService;
import io.micrometer.core.instrument.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SearchController {

    private int PAGE_SIZE=20;

    @Autowired
    private SearchService searchService;

    //search implementation
    @PostMapping("/search/page/{pageNo}")
    public String viewCyclePaginated(Model model,
                                 @PathVariable(value="pageNo") int pageNo,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir ,
                                 @ModelAttribute("keyword") String keyword)
    {
        System.out.println(keyword);
        Page<Cycle> page = searchService.searchAllCyclesPaginated(pageNo, PAGE_SIZE, sortField, sortDir, keyword);
        List<Cycle> listCycles = page.getContent();
        model.addAttribute("listCycles", listCycles);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("search", true);


        return "home";
    }

    @GetMapping("/search/{keyword}/page/{pageNo}")
    public String viewCyclePage(Model model,
                                 @PathVariable(value="pageNo") int pageNo,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir ,
                                 @PathVariable String keyword)
    {
        System.out.println(keyword);
        Page<Cycle> page = searchService.searchAllCyclesPaginated(pageNo, PAGE_SIZE, sortField, sortDir, keyword);
        List<Cycle> listCycles = page.getContent();
        model.addAttribute("listCycles", listCycles);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("search", true);


        return "home";
    }



}
