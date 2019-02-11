package com.blackboard.backEnd.dao;

import com.blackboard.backEnd.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The customer DAO uses hibernate object relational mapping (ORM) framework to match database info
 * to the model.
 */
@Repository
public interface StudentDAO extends CrudRepository<Student, Long> {

    List<Student> findByState(String state);

    List<Student> findByStateNot(String state);

    List<Student> findByFirstNameOrLastOrEmail(String searchFirst, String searchLast, String searchEmail);

}
