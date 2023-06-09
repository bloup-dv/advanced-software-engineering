package ase.activity;

import ase.session.Session;
import ase.category.Category;
import ase.types.Estimation;
import ase.types.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @OneToOne
    @JsonIgnoreProperties("activity")
    @JoinColumn(name = "estimation_id")
    private Estimation estimation;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "status")
    private Status status;
    @Transient
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "activity")
    private List<Session> sessionList;

    public Activity() {
    }

    public Activity(String name, LocalDateTime dueDate, Estimation estimation, Category category) {
        this.name = name;
        this.dueDate = dueDate;
        this.estimation = estimation;
        this.category = category;
        this.status = Status.TODO;
        this.sessionList = new ArrayList<Session>();
    }

    public Activity(Long id, String name, LocalDateTime dueDate, Estimation estimation, Category category, Status status) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.estimation = estimation;
        this.category = category;
        this.status = status;
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
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
    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    public void addSession(Session session){
        if( this.category.getTimeFrame().getEnd().compareTo(session.getStartTime().toLocalTime()) > 0){
            sessionList.add(session);
        }
        else {
            throw new IllegalArgumentException("the session's start datetime must be within the defined timeslot");
        }
    }
}
