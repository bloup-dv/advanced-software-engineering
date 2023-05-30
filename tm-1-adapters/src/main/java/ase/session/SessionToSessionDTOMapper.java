package ase.session;

import ase.activity.ActivityToActivityDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SessionToSessionDTOMapper implements Function<Session, SessionDTO> {
    private ActivityToActivityDTOMapper activityToActivityDTOMapper;
    @Autowired
    public SessionToSessionDTOMapper(ActivityToActivityDTOMapper activityToActivityDTOMapper){
        this.activityToActivityDTOMapper = activityToActivityDTOMapper;
    }
    @Override
    public SessionDTO apply(Session session) {return map(session);}
    private SessionDTO map(Session session) {
        return new SessionDTO(
                session.getId(),
                session.getStartTime(),
                session.getEndTime(),
                activityToActivityDTOMapper.apply(session.getActivity()));
    }
}
