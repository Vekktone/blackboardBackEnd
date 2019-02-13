package com.blackboard.backEnd.service;

import com.blackboard.backEnd.model.Student;
import com.blackboard.backEnd.model.EditObj;

import java.io.FileNotFoundException;
import java.util.List;

public interface StudentService {

    void parseFileUpdateDB(String filename) throws FileNotFoundException;

    void addStud(Student student);

    void editStud(EditObj studentEdit);

    void deleteStud(EditObj student);

    List<Student> getRecords(long num);
    List<Student> searchData(String searchFirst);

}
