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

@RequestMapping("/student")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    private EditObj editStud;

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
     * search data endpoint for extracting a student record based on first, last, or email
     * @param searchText the string to search for
     * @return list of results that match the search string
     */
    @GetMapping("/searchData/{searchText}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Student> searchData(@PathVariable String searchText) {

        return this.studentService.searchData(searchText);
    }

    /**
     * endpoint to add a student to the database.
     * @param object Student object received from the front end
     * @param bindingResult if there sre errors in request
     */
    @PostMapping("/addStud")
    @CrossOrigin(origins = "http://localhost:4200")
    public void addStudent(@RequestBody @Valid Student object, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            // handle error
            LogInfo.sendError("addStudent error!");
        }
        else
        {
            this.studentService.addStud(object);
        }

    }

    /**
     * endpoint to edit student information from frontend
     * @param editStudent student and id object for matching an existing customer into the database
     * @param bindingResult for request errors
     */
    @PostMapping("/editStud")
    @CrossOrigin(origins = "http://localhost:4200")
    public void editStud(@RequestBody @Valid String editStudent, BindingResult bindingResult) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            editStud = mapper.readValue(editStudent, EditObj.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(bindingResult.hasErrors()){
            // handle error
            LogInfo.sendError("editStud error!");
        }
        else
        {
            this.studentService.editStud(editStud);
        }

    }

    /**
     * endpoint to delete a student from database
     * @param editStudent student and id object for matching a student in data
     * @param bindingResult for request errors
     */
    @PostMapping("/deleteStud")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deleteStud(@RequestBody @Valid String editStudent, BindingResult bindingResult) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            editStud = mapper.readValue(editStudent, EditObj.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(bindingResult.hasErrors()){
            // handle error
            LogInfo.sendError("editStud error!");
        }
        else
        {
            this.studentService.deleteStud(editStud);
        }
    }
}
