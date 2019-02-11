package com.blackboard.backEnd.service;

import com.blackboard.backEnd.dao.StudentDAO;
import com.blackboard.backEnd.model.Student;
import com.blackboard.backEnd.model.EditObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    private FileParser fp = new FileParser();

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    /**
     * this method is called when a file is uploaded to the backend. The file is
     * parsed and the database updated.
     * @param filename name of file to be parsed
     * @throws FileNotFoundException if the file can't be found
     */
    public void parseFileUpdateDB(String filename) throws FileNotFoundException {

        List<Student> studentList;

        studentList = fp.parseFile(filename);

        studentDAO.save(studentList);

    }

    /**
     * getRecords method which calls the respective DAO methods until num records are extracted.
     * @param num the number of records to be extracted
     * @return a list of num Student records.
     */
    public List<Student> getRecords(long num){

        List<Student> allStudents = new ArrayList<>();
        List<Student> selectStudents = new ArrayList<>();
        int limit;

        allStudents.addAll(studentDAO.findByState("TX"));
        allStudents.addAll(studentDAO.findByStateNot("TX"));

        //adds first num records to a new list from the allCustomersList
        for (limit = 0; limit < num; limit++) {

            if (limit > allStudents.size()-1){ return selectStudents; }

            selectStudents.add(allStudents.get(limit));

        }
        return selectStudents;
    }

    /**
     * searchData method which calls the DAO to find by state, first, or last.
     * @param searchText the main search string to be found
     * @return list of records that match the search
     */
    public List<Student> searchData(String searchText){

        return studentDAO.findByFirstNameOrLastOrEmail(searchText, searchText, searchText);

    }

    /**
     * adds a student to the db through the DAO
     * @param student Object to add to db
     */
    public void addCust(Student student){

        studentDAO.save(student);
    }

    /**
     * edits a customer by updating db with new info
     * @param customerEdit the edit object which contains the true ID and the customer to edit
     */
    public void editCust(EditObj customerEdit){

        Student custEdit = customerEdit.getStudent();
        custEdit.setId(customerEdit.getId());
        studentDAO.save(custEdit);

    }

    /**
     * deletes a customer by updating db with new info
     * @param customerEdit the edit object which contains the true ID and the customer to delete
     */
    public void deleteCust(EditObj customerEdit){

        Student custEdit = customerEdit.getStudent();
        custEdit.setId(customerEdit.getId());
        studentDAO.delete(custEdit);

    }
}
