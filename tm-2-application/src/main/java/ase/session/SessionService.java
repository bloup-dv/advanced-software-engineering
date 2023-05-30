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

    public void startSession(Activity activity) throws IllegalArgumentException {
        LocalDateTime startTime = LocalDateTime.now();
        isActivityValid(activity, startTime);
        updateActivity(activity);
        sessionRepository.save(new Session(startTime, activity));
    }

    private void isActivityValid(Activity activity, LocalDateTime startTime) {
        checkActivityExists(activity);
        if (activity.getDueDate().isBefore(startTime)) {
            throw new IllegalArgumentException("this Activity is already overdue");
        }
        if (activity.getStatus().equals(Status.DONE)) {
            throw new IllegalArgumentException("this Activity has already been completed.");
        }
    }

    private void updateActivity(Activity activity) {
        activity.setStatus(Status.IN_PROGRESS);
        activityRepository.save(activity);
    }

    public void stopSession() throws IllegalArgumentException {
        if (!sessionRepository.isActive()) {
            throw new IllegalArgumentException("There is no active Session");
        }
        if (sessionRepository.findActive() == null) {
            throw new IllegalArgumentException("The Session is invalid");
        }
        Session session = sessionRepository.findActive();
        session.setEndTime(LocalDateTime.now());
        sessionRepository.save(session);
    }

    public List<Session> listSessionsByActivity(Activity activity) throws IllegalArgumentException {
        checkActivityExists(activity);
        if (activity.getStatus().equals(Status.TODO)) {
            return new ArrayList<Session>();
        } else {
            return sessionRepository.findAllByActivity(activity);
        }
    }

    public void updateSession(Session session, LocalDateTime endTime) throws IllegalArgumentException {
        checkSessionExists(session);
        checkActivityExists(session.getActivity());
        if (session.getStartTime().isAfter(endTime)) {
            throw new IllegalArgumentException("Session startTime may not come after endTime");
        }
        sessionRepository.save(new Session(session.getId(), session.getStartTime(), endTime, session.getActivity()));
    }

    public void deleteSession(Session session) throws IllegalArgumentException {
        checkSessionExists(session);
        sessionRepository.delete(session);
    }

    public void checkSessionExists(Session session) {
        if (!sessionRepository.existsById(session.getId())) {
            throw new IllegalArgumentException("Session does not exist");
        }
    }

    public void checkActivityExists(Activity activity) {
        if(activity== null){
            throw new IllegalArgumentException("The Activity is invalid");
        }
        if (!activityRepository.existsById(activity.getId())) {
            throw new IllegalArgumentException("The Activity does not exist");
        }
    }
}
