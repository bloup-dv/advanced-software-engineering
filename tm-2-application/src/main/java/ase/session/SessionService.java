package ase.session;

import ase.activity.Activity;
import ase.activity.ActivityRepository;
import ase.types.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            throw new IllegalArgumentException("this Activity is already overdue");
        }
        if(activity.getStatus().getStatus().equals(Status.DONE)){
            throw new IllegalArgumentException("this Activity has already been completed.");
        }
        activity.setStatus(Status.IN_PROGRESS);
        activityRepository.save(activity);
        sessionRepository.save(new Session(startTime, activity));

    }

    public void stopSession() throws IllegalArgumentException{
        LocalDateTime currentTime = LocalDateTime.now();
        if(!sessionRepository.isActive()){
            throw new IllegalArgumentException("There is no active Session");
        }
        if(sessionRepository.findActive() == null){
            throw new IllegalArgumentException("The Session is invalid");
        }
        Session session = sessionRepository.findActive();
        session.setEndTime(currentTime);
        sessionRepository.save(session);

    }

    public List<Session> listSessionsByActivity(Activity activity) throws IllegalArgumentException{
        if(!activityRepository.existsById(activity.getId())){
            throw new IllegalArgumentException("Activity does not exist");
        }
        if (activity.getStatus().getStatus().equals(Status.TODO)){
            return new ArrayList<Session>();
        }
        else {
            return sessionRepository.findAllByActivity(activity);
        }
    }

    public void updateSession(Session session, LocalDateTime endTime) throws IllegalArgumentException{
        if(!sessionRepository.existsById(session.getId())){
            throw new IllegalArgumentException("Session does not exist");
        }
        if(!activityRepository.existsById(session.getActivity().getId())){
            throw new IllegalArgumentException("Acitivity does not exist");
        }
        if(session.getStartTime().isAfter(endTime)){
            throw new IllegalArgumentException("Session startTime may not come after endTime");
        }
        sessionRepository.save(new Session(session.getId(),session.getStartTime(), endTime, session.getActivity()));
    }
    public void deleteSession(Session session) throws IllegalArgumentException{
        if(!sessionRepository.existsById(session.getId())){
            throw new IllegalArgumentException("Session does not exist");
        }
        sessionRepository.delete(session);
    }





}
