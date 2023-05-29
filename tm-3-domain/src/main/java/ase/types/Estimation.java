package ase.types;

public class Estimation {
    private Long id;
    private Long estimation;
    private Unit unit;

    public Estimation(Long estimation, Unit unit) {
        if(estimation >= 0L){
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
