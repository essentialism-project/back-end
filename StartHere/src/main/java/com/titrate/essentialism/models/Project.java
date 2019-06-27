package com.titrate.essentialism.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personalvalueid", nullable = false)
    @JsonIgnoreProperties({"projects", "hibernateLazyInitializer"})
    private PersonalValue personalvalue;


    private String projectname;


    public Project() {
    }

    public Project( String projectname ,PersonalValue personalvalue) {
        this.personalvalue = personalvalue;
        this.projectname = projectname;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public long getProjectid() {
        return projectid;
    }

    public void setProjectid(long projectid) {
        this.projectid = projectid;
    }



    public PersonalValue getPersonalvalue() {
        return personalvalue;
    }

    public void setPersonalvalue(PersonalValue personalvalue) {
        this.personalvalue = personalvalue;
    }

}
