package com.titrate.essentialism.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//import java.util.List;

@Entity
@Table(name = "personalvalues")
public class PersonalValue extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long personalvaluesid;

    @Column(nullable = false)
    private String personalvalue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties({"personalvalues", "hibernateLazyInitializer"})
    private User user;


    @Column
    private String description;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "projects")
//    @JsonIgnoreProperties({"projects"})
//    private String project;
//
//    @OneToMany(mappedBy = "personalvalue",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    @JsonIgnoreProperties("personalvalue")

    @Column
    private ArrayList<String> projects;


    public PersonalValue()
    {
    }

    public PersonalValue(String personalvalue, User user)
    {
        this.personalvalue = personalvalue;
        this.user = user;
    }


    public long getPersonalvaluesid()
    {

        return personalvaluesid;
    }

    public void setPersonalvaluesid(long personalvaluesid)
    {
        this.personalvaluesid = personalvaluesid;
    }

    public String getPersonalvalue()
    {
        return personalvalue;
    }

    public void setPersonalvalue(String personalvalue)
    {
        this.personalvalue = personalvalue;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public ArrayList<String> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<String> projects) {
        this.projects = projects;
    }

}