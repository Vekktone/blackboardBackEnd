package com.blackboard.backEnd.model;

import java.util.List;

/**
 * This class is used for the email implementation. We use the mapper function to
 * map the JSON object to this java object.
 */
public class MailReturn {
    private String address;
    private String subjectM;
    private String bodyM;
    private List<Student> finalList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getSubjectM() {
        return subjectM;
    }

    public void setSubjectM(String subjectM) {
        this.subjectM = subjectM;
    }

    public String getBodyM() {
        return bodyM;
    }

    public void setBodyM(String bodyM) {
        this.bodyM = bodyM;
    }

    public List<Student> getFinalList() {
        return finalList;
    }

    public void setFinalList(List<Student> finalList) {
        this.finalList = finalList;
    }

}
