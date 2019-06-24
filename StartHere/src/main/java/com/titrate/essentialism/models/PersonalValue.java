package com.titrate.essentialism.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

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
}