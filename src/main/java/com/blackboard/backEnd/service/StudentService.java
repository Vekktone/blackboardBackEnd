package com.blackboard.backEnd.service;

import com.blackboard.backEnd.model.Student;
import com.blackboard.backEnd.model.EditObj;

import java.io.FileNotFoundException;
import java.util.List;

public interface StudentService {

    void parseFileUpdateDB(String filename) throws FileNotFoundException;

    void addCust(Student student);

    void editCust(EditObj customerEdit);

    void deleteCust(EditObj customer);

    List<Student> getRecords(long num);
    List<Student> searchData(String searchFirst);

}
