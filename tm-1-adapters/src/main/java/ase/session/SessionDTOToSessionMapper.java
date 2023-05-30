package ase.session;

import ase.activity.Activity;
import ase.activity.ActivityDTOToActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SessionDTOToSessionMapper implements Function<SessionDTO, Session> {
    private ActivityDTOToActivityMapper activityDTOToActivityMapper;
    @Autowired
    public SessionDTOToSessionMapper(ActivityDTOToActivityMapper activityDTOToActivityMapper){
        this.activityDTOToActivityMapper = activityDTOToActivityMapper;
    }
    @Override
    public Session apply(SessionDTO sessionDTO) { return map(sessionDTO);}
    private Session map(SessionDTO sessionDTO){
        if(sessionDTO != null) {
            return new Session(sessionDTO.getId(), sessionDTO.getStartTime(), sessionDTO.getEndTime(), activityDTOToActivityMapper.apply(sessionDTO.getActivityDTO()));
        }
        else{
            return null;
        }
    }
}
