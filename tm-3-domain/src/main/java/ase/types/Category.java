package ase.types;

public class Category {

    private Long id;
    private String name;

    private TimeFrame timeFrame;

    public Category(String name, TimeFrame timeFrame) {
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
