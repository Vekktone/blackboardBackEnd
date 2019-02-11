package com.blackboard.backEnd.controller;

import com.blackboard.backEnd.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.blackboard.backEnd.model.EditObj;
import com.blackboard.backEnd.model.LogInfo;
import com.blackboard.backEnd.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequestMapping("/customer")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    private EditObj editCust;

    /**
     * get records endpoint for retrieval of data
     * @param num how many records to return
     * @return list of num Student records
     */
    @GetMapping("/getRecords/{num}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Student> getRecords(@PathVariable int num) {

        return this.studentService.getRecords(num);
    }

    /**
     * search data endpoint for extracting a customer record based on first, last, or email
     * @param searchText the string to search for
     * @return list of results that match the search string
     */
    @GetMapping("/searchData/{searchText}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Student> searchData(@PathVariable String searchText) {

        return this.studentService.searchData(searchText);
    }

    /**
     * endpoint to add a customer to the database.
     * @param object Student object received from the front end
     * @param bindingResult if there sre errors in request
     */
    @PostMapping("/addCust")
    @CrossOrigin(origins = "http://localhost:4200")
    public void addCust(@RequestBody @Valid Student object, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            // handle error
            LogInfo.sendError("addCust error!");
        }
        else
        {
            this.studentService.addCust(object);
        }

    }

    /**
     * endpoint to edit customer information from frontend
     * @param editCustomer customer and id object for matching an existing customer into the database
     * @param bindingResult for request errors
     */
    @PostMapping("/editCust")
    @CrossOrigin(origins = "http://localhost:4200")
    public void editCust(@RequestBody @Valid String editCustomer, BindingResult bindingResult) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            editCust = mapper.readValue(editCustomer, EditObj.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(bindingResult.hasErrors()){
            // handle error
            LogInfo.sendError("editCust error!");
        }
        else
        {
            this.studentService.editCust(editCust);
        }

    }

    /**
     * endpoint to delete a customer from database
     * @param editCustomer customer and id object for matching a customer in data
     * @param bindingResult for request errors
     */
    @PostMapping("/deleteCust")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteCust(@RequestBody @Valid String editCustomer, BindingResult bindingResult) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            editCust = mapper.readValue(editCustomer, EditObj.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(bindingResult.hasErrors()){
            // handle error
            LogInfo.sendError("editCust error!");
        }
        else
        {
            this.studentService.deleteCust(editCust);
        }
    }
}
