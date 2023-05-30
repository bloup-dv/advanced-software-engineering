package ase.types;

import ase.activity.Activity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "timeFrame_id")
    private TimeFrame timeFrame;
    @Transient
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "category")
    private List<Activity> activityList;

    public Category() {
    }

    public Category(String name, TimeFrame timeFrame) {
        this.name = name;
        this.timeFrame = timeFrame;
    }

    public Category(Long id, String name, TimeFrame timeFrame) {
        this.id = id;
        this.name = name;
        this.timeFrame = timeFrame;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }
}
