package ase.session;

import ase.activity.Activity;
import jakarta.persistence.*;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    //TODO Nullable
    @Nullable
    @Column(name="end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
    public Session() {
    }

    public Session(LocalDateTime startTime, Activity activity) {
        this.startTime = startTime;
        this.endTime = null;
        this.activity = activity;
    }

    public Session(Long id, LocalDateTime startTime, @Nullable LocalDateTime endTime, Activity activity) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        //TODO endTime>startTime
    }
    public Activity getActivity(){
        return activity;
    }
}
