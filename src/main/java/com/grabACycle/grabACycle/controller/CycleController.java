package com.grabACycle.grabACycle.controller;


import com.grabACycle.grabACycle.entity.Cycle;
import com.grabACycle.grabACycle.exception.exceptions.*;
import com.grabACycle.grabACycle.exception.messages.CycleErrorResponse;
import com.grabACycle.grabACycle.services.CycleService;
import com.grabACycle.grabACycle.web.dto.CycleDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class CycleController
{

    static int PAGE_SIZE=20;
    private CycleService cycleService;

    private static final Logger logger = LoggerFactory.getLogger(CycleController.class);

    @Autowired
    public CycleController(CycleService cycleService)
    {
        this.cycleService=cycleService;
    }


    // Display list of cycles
    @GetMapping("/")
    public String viewHomePage(Model model)
    {
        logger.info("Request received to view home page");
        return findPaginated(1, "id","asc",model); // default page number=1
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value="pageNo") int pageNo, @RequestParam("sortField")  String sortField, @RequestParam("sortDir") String sortDir, Model model)
    {
        logger.info("Request received to find paginated list of cycles. Page number: {}, Sort Field: {}, Sort Direction: {}", pageNo, sortField, sortDir);

        if (pageNo <= 0) {
            logger.error("Invalid page number received: {}", pageNo);
            throw new InvalidPageNumException("Page number must be greater than 0");
        }

        if (!(sortField.equals("id") || sortField.equals("bookingStatus") || sortField.equals("model") || sortField.equals("name") || sortField.equals("type") || sortField.equals("bookedBy"))){
            logger.error("Invalid sort field received: {}", sortField);
            throw new InvalidSortFieldException("Invalid sort field: " + sortField);
        }


        if(!(sortDir.equals("asc") || sortDir.equals("desc")))
        {
            logger.error("Invalid sort direction received: {}", sortDir);
            throw new InvalidSortDirException("Invalid sort Direction: "+sortDir);
        }


        int pageSize= PAGE_SIZE; // we can take the pagesize from front end as well using path variable, or declare the size in application.properties

        Page<Cycle> page=cycleService.findPaginated(pageNo, pageSize,sortField, sortDir);
        int totalPages=page.getTotalPages();
        if (pageNo > totalPages) {
            logger.error("Page number exceeds total pages. Received: {}, Total Pages: {}", pageNo, totalPages);
            throw new InvalidPageNumException("Page number exceeds total pages");
        }

        List<Cycle> listCycles=page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listCycles", listCycles);
        model.addAttribute("search", false);

        return "home";

    }

    // fetches data of the specified page number
    @GetMapping("/cycles/page/{pageNo}")
    @ResponseBody
    public CycleDto fetchPage(@PathVariable(value="pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir){
        logger.info("Request received to fetch page. Page number: {}, Sort Field: {}, Sort Direction: {}", pageNo, sortField, sortDir);


        int pageSize= PAGE_SIZE; // we can take the pagesize from front end as well using path variable, or declare the size in application.properties

        if (pageNo <= 0) {
            logger.error("Invalid page number received: {}", pageNo);

            throw new InvalidPageNumException("Page number must be greater than 0");
        }

        if (!(sortField.equals("id") || sortField.equals("bookingStatus") || sortField.equals("model") || sortField.equals("name") || sortField.equals("type") || sortField.equals("bookedBy"))){
            logger.error("Invalid sort field received: {}", sortField);
            throw new InvalidSortFieldException("Invalid sort field: " + sortField);

        }


        if(!(sortDir.equals("asc") || sortDir.equals("desc")))
        {
            logger.error("Invalid sort direction received: {}", sortDir);

            throw new InvalidSortDirException("Invalid sort Direction: "+sortDir);
        }

        Page<Cycle> page = cycleService.findPaginated(pageNo, pageSize,sortField, sortDir);

        int totalPages=page.getTotalPages();
        if (pageNo > totalPages) {
            logger.error("Page number exceeds total pages. Received: {}, Total Pages: {}", pageNo, totalPages);

            throw new InvalidPageNumException("Page number exceeds total pages");
        }

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
        logger.info("Request received to delete cycle with ID: {}", cycleId);

        try {
            // call delete cycle method
            cycleService.deleteCycleById(cycleId);
        }
        catch (Exception e)
        {
            logger.error("Cycle with ID {} not found", cycleId);

            throw new CycleNotFoundException("Cycle with ID " + cycleId + " could not be deleted.");
        }
        return fetchPage(pageNo, sortField, sortDir);
    }

    // Add a new Cycle form
    @GetMapping("/showNewCycleForm")
    public String showNewCycleForm(@RequestParam(value="returnToPage") int page,
                                   @RequestParam(value="sortField") String sortField,
                                   @RequestParam(value="sortDir") String sortDir,
                                   Model model)
    {
        logger.info("Request received to show new cycle form.");

        if (page <= 0) {
            logger.error("Invalid value for returnToPage parameter: {}", page);

            throw new InvalidPageNumException("Invalid value for returnToPage parameter");
        }

        if (!(sortField.equals("id") || sortField.equals("bookingStatus") || sortField.equals("model") || sortField.equals("name") || sortField.equals("type") || sortField.equals("bookedBy")))
        {
            logger.error("Sort field parameter is missing or empty");
            throw new InvalidSortFieldException("Invalid sort field: " + sortField);
        }


        if(!(sortDir.equals("asc") || sortDir.equals("desc")))
        {
            logger.error("Invalid sort direction parameter: {}", sortDir);

            throw new InvalidSortDirException("Invalid sort Direction: "+sortDir);
        }

        // create model attribute to bind form data
        Cycle cycle=new Cycle();
        model.addAttribute("cycle", cycle); // thymleaf template will access this empty cycle object for binding form data
        model.addAttribute("returnToPage", page);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        return "add_cycle";
    }

    @PostMapping("/createCycle")
    public String saveCycle(@Valid @ModelAttribute("cycle") Cycle cycle, Model model,
                            @RequestParam(value = "sortField") String sortField,
                            @RequestParam(value = "sortDir") String sortDir,
                            @RequestParam(value = "returnToPage") int page)
    {
        logger.info("Request received to save new cycle ");

        try {
            // Save cycle to database
            cycleService.createCycle(cycle);
        } catch (Exception e) {
            logger.error("Error occurred while saving the cycle", e);

            throw new CycleCreationException("Error occurred while saving the cycle");
        }


        return "redirect:/page/" + page + "?sortField=" + sortField + "&sortDir=" + sortDir;
    }



    // Update cycle form
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable int id,
                                    @RequestParam(value = "returnToPage") int page,
                                    @RequestParam(value="sortField") String sortField,
                                    @RequestParam(value="sortDir") String sortDir,
                                    @Valid Model model)
    {

        logger.info("Request received to show update cycle form for ID: {}", id);

        if (!(sortField.equals("id") || sortField.equals("bookingStatus") || sortField.equals("model") || sortField.equals("name") || sortField.equals("type") || sortField.equals("bookedBy")))
            throw new InvalidSortFieldException("Invalid sort field: " + sortField);

        if(!(sortDir.equals("asc") || sortDir.equals("desc")))
        {
            throw new InvalidSortDirException("Invalid sort Direction: "+sortDir);
        }

        Cycle cycle=null;
        try {
            // get cycle from the service layer
            cycle = cycleService.fetchCycleById(id);
            logger.info("Cycle details retrieved for update: {}", cycle);

            // set cycle as a model attribute to pre-populate the form data
            model.addAttribute("cycle", cycle);
            model.addAttribute("returnToPage", page);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
        }
        catch (Exception e)
        {
            logger.error("Cycle with ID {} not found", id);

            throw new CycleNotFoundException("Cycle does not exist");
        }

        return "update_cycle";

    }

        @GetMapping("/updateBookingStatus/{cycleId}")
        @ResponseBody
        public Cycle updateBookingStatus(@PathVariable int cycleId, Principal principal){

            logger.info("Request received to update booking status for cycle with ID: {}", cycleId);

            String userEmail = principal.getName();

          Cycle cycle = null;

          try {
              logger.info("Updating booking status for cycle with ID: {} is successful", cycleId);

              cycle =  cycleService.updateBookingStatus(cycleId, userEmail);
          }catch (Exception e)
          {
              logger.error("Cycle with ID {} not found", cycleId);

              throw new UpdateBookingStatusException("Booking Failed");
          }
          return cycle;

        }




}
