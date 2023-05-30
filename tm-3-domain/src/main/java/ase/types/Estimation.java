package ase.types;

import jakarta.persistence.*;

@Entity
@Table(name = "estimation")
public class Estimation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "estimation")
    private Long estimation;
    @Column(name = "unit")
    private Unit unit;

    public Estimation() {
    }

    public Estimation(Long estimation, Unit unit) {
        if(estimation >= 0L){
            this.estimation = estimation;
            this.unit = unit;
        }
        else throw new IllegalArgumentException("Duration value must not be negative");
    }

    public Estimation(Long id, Long estimation, Unit unit) {
        if(estimation >= 0L) {
            this.id = id;
            this.estimation = estimation;
            this.unit = unit;
        }
        else throw new IllegalArgumentException("Duration value must not be negative");
    }

    public Long getId() {
        return id;
    }

    public Long getEstimation() {
        return estimation;
    }

    public Unit getUnit() {
        return unit;
    }
}
