package com.blackboard.backEnd.model;


import javax.persistence.*;

/**
 * Student class with default constructor, getters and setters
 */
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String last;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param first first name of customer
     */
    public void setFirstName(String first) {
        this.firstName = first;
    }

    /**
     *
     * @param last last name of customer
     */
    public void setLastName(String last) {
        this.last = last;
    }

    /**
     *
     * @param email email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param address physical address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @param city city of residence
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @param state state of residence
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @param zip 5-10 digit zip code
     */
    public void setZip(String zip) {
        this.zip = zip;
    }


    /**
     * get methods
     */

    public String getFirstName() {
        return firstName;
    }

    public String getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getLast() {
        return last;
    }


}
