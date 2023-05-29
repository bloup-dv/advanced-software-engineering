package ase.session;

import java.util.Date;

public class Session {
    private Long id;

    private Date startTime;

    private Date endTime;

    public Long getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        //TODO endTime>startTime
    }
}
