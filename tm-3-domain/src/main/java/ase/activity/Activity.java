package ase.activity;

import ase.session.Session;
import ase.types.Category;
import ase.types.Estimation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity {
    private Long id;
    private String name;
    private Date dueDate;
    private Estimation estimation;
    private Category category;
    private List<Session> sessionList;

    public Activity(String name, Date dueDate, Estimation estimation, Category category) {
        this.name = name;
        this.dueDate = dueDate;
        this.estimation = estimation;
        this.category = category;
        this.sessionList = new ArrayList<Session>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Estimation getEstimation() {
        return estimation;
    }

    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }
}
