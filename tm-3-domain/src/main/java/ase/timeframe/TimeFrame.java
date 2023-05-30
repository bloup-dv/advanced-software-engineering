package ase.timeframe;
import java.time.LocalTime;
import java.util.List;

import ase.category.Category;
import jakarta.persistence.*;


@Entity
@Table(name = "time_Frame")
public class TimeFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start")
    private LocalTime start;
    @Column(name = "end")
    private LocalTime end;
    @Transient
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "timeFrame")
    private List<Category> categoryList;


    public TimeFrame() {
    }


    public TimeFrame(LocalTime start, LocalTime end) {
        if(start.compareTo(end)<0) {
            this.start = start;
            this.end = end;
        }
        else {
            throw new IllegalArgumentException("End time should come after start time");
        }
    }

    public TimeFrame(Long id, LocalTime start, LocalTime end) {
        if(start.compareTo(end)<0) {
            this.id = id;
            this.start = start;
            this.end = end;
        }
        else {
            throw new IllegalArgumentException("End time should come after start time");
        }
    }

    public Long getId(){
        return id;
    }
    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

}
