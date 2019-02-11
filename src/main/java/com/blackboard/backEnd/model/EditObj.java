package com.blackboard.backEnd.model;

/**
 * This object maps a student and an ID into one object.
 * The ID is then set on the student object for matching purposes.
 */
public class EditObj {

    private Student student;
    private int id;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
