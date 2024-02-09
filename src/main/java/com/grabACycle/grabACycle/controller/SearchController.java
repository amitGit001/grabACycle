package com.grabACycle.grabACycle.controller;

import com.grabACycle.grabACycle.dao.CycleRepository;
import com.grabACycle.grabACycle.entity.Cycle;
import com.grabACycle.grabACycle.services.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private CycleService cycleService;

    //search implementation
    @RequestMapping("home")
    public String viewCyclePage1(Model model, @Param("keyword") String keyword)
    {
        List<Cycle> cycleList = cycleService.searchAllCycles(keyword);

        model.addAttribute("listCycles", cycleList);
        model.addAttribute("keyword", keyword);

        return "home";
    }

}
