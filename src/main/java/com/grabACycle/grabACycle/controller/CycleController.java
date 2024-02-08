package com.grabACycle.grabACycle.controller;


import com.grabACycle.grabACycle.entity.Cycle;
import com.grabACycle.grabACycle.services.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CycleController
{

    private CycleService cycleService;

    @Autowired
    public CycleController(CycleService cycleService)
    {
        this.cycleService=cycleService;
    }


    // Display list of cycles
    @GetMapping("/")
    public String viewHomePage(Model model)
    {
        return findPaginated(1, "id","asc",model); // default page number=1
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value="pageNo") int pageNo, @RequestParam("sortField")  String sortField, @RequestParam("sortDir") String sortDir, Model model)
    {
        int pageSize=5; // we can take the pagesize from front end as well using path variable, or declare the size in application.properties

        Page<Cycle> page=cycleService.findPaginated(pageNo, pageSize,sortField, sortDir);

        List<Cycle> listCycles=page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listCycles", listCycles);

        return "home";

    }

    // Add a new Cycle form
    @GetMapping("/showNewCycleForm")
    public String showNewCycleForm(Model model)
    {
        // create model attribute to bind form data
        Cycle cycle=new Cycle();
        model.addAttribute("cycle", cycle); // thymleaf template will access this empty cycle object for binding form data
        return "add_cycle";
    }

    @PostMapping("/createCycle")
    public String saveCycle(@ModelAttribute("cycle") Cycle cycle)
    {
        // save cycle to database
        cycleService.createCycle(cycle);
        return "redirect:/";
    }



    // Update cycle form
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable int id, Model model)
    {
        // get cycle from the service layer
        Cycle cycle= cycleService.fetchCycleById(id);

        // set cycle as a model attribute to pre-populate the form data
        model.addAttribute("cycle", cycle);

        return "update_cycle";

    }


    // Delete Cycle
    @GetMapping("/deleteCycle/{id}")
    @ResponseBody
    public String deleteCycle(@PathVariable int id)
    {

        // call delete cycle method
        cycleService.deleteCycleById(id);

        return "true";
    }

}
