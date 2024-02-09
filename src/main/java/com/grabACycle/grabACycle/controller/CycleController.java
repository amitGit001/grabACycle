package com.grabACycle.grabACycle.controller;


import com.grabACycle.grabACycle.entity.Cycle;
import com.grabACycle.grabACycle.services.CycleService;
import com.grabACycle.grabACycle.web.dto.CycleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CycleController
{

    static int PAGE_SIZE= 12;

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
        int pageSize= PAGE_SIZE; // we can take the pagesize from front end as well using path variable, or declare the size in application.properties

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

    // fetches data of the specified page number
    @GetMapping("/cycles/page/{pageNo}")
    @ResponseBody
    public CycleDto fetchPage(@PathVariable(value="pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir){


        int pageSize= PAGE_SIZE; // we can take the pagesize from front end as well using path variable, or declare the size in application.properties

        Page<Cycle> page = cycleService.findPaginated(pageNo, pageSize,sortField, sortDir);

        List<Cycle> listCycles=page.getContent();
        long totalItems= page.getTotalElements();

        CycleDto cycleDto = new CycleDto(listCycles, totalItems);

        return cycleDto;
    }

    // Delete Cycle
    @GetMapping("/deleteCycle/{id}")
    @ResponseBody
    public CycleDto deleteCycle(@PathVariable(value="id") int cycleId, @RequestParam("page") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir)
    {

        // call delete cycle method
        cycleService.deleteCycleById(cycleId);

        return fetchPage(pageNo, sortField, sortDir);
    }

    // Add a new Cycle form
    @GetMapping("/showNewCycleForm")
    public String showNewCycleForm(@RequestParam(value="returnToPage") int page,
                                   @RequestParam(value="sortField") String sortField,
                                   @RequestParam(value="sortDir") String sortDir,
                                   Model model)
    {
        // create model attribute to bind form data
        Cycle cycle=new Cycle();
        model.addAttribute("cycle", cycle); // thymleaf template will access this empty cycle object for binding form data
        model.addAttribute("returnToPage", page);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        return "add_cycle";
    }

    @PostMapping("/createCycle")
    public String saveCycle(@ModelAttribute("cycle") Cycle cycle, Model model,
                            @RequestParam(value = "sortField") String sortField,
                            @RequestParam(value = "sortDir") String sortDir,
                            @RequestParam(value = "returnToPage") int page)
    {
        System.out.println(cycle);
        // save cycle to database
        cycleService.createCycle(cycle);


        return "redirect:/page/" + page + "?sortField=" + sortField + "&sortDir=" + sortDir;
    }



    // Update cycle form
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable int id,
                                    @RequestParam(value = "returnToPage") int page,
                                    @RequestParam(value="sortField") String sortField,
                                    @RequestParam(value="sortDir") String sortDir,
                                    Model model)
    {
        System.out.println("Showing form");
        // get cycle from the service layer
        Cycle cycle= cycleService.fetchCycleById(id);
        System.out.println(cycle);
        // set cycle as a model attribute to pre-populate the form data
        model.addAttribute("cycle", cycle);
        model.addAttribute("returnToPage", page);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);



        return "update_cycle";

    }




}
