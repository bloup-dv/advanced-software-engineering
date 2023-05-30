package ase.session;

import ase.activity.Activity;
import ase.activity.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SessionService {
    private final ActivityRepository activityRepository;
    private final SessionRepository sessionRepository;
    @Autowired
    public SessionService(ActivityRepository activityRepository, SessionRepository sessionRepository) {
        this.activityRepository = activityRepository;
        this.sessionRepository = sessionRepository;
    }

    /*public createActivity(Activity activity) throws IllegalArgumentException {
        if()
    }*/
    public void startSession(Activity activity) throws IllegalArgumentException{
        LocalDateTime startTime = LocalDateTime.now();
        if(!activityRepository.existsById(activity.getId())){
            throw new IllegalArgumentException("The Activity does not exist");
        }
        if(activity.getDueDate().isBefore(startTime)){
            throw new IllegalArgumentException("The start time cannot come after the due date");
        }
        //TODO add checks
        sessionRepository.save(new Session(startTime));

    }

    // TODO end session





}
