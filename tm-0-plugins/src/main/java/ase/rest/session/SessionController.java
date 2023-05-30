package ase.rest.session;

import ase.activity.Activity;
import ase.activity.ActivityDTO;
import ase.activity.ActivityDTOToActivityMapper;
import ase.session.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionDTOToSessionMapper sessionDTOToSessionMapper;
    private final SessionToSessionDTOMapper sessionToSessionDTOMapper;
    private final SessionService sessionService;
    private final ActivityDTOToActivityMapper activityDTOToActivityMapper;

    @Autowired
    public SessionController(SessionDTOToSessionMapper sessionDTOToSessionMapper,
                             SessionToSessionDTOMapper sessionToSessionDTOMapper,
                             SessionService sessionService,
                             ActivityDTOToActivityMapper activityDTOToActivityMapper) {
        this.sessionDTOToSessionMapper = sessionDTOToSessionMapper;
        this.sessionToSessionDTOMapper = sessionToSessionDTOMapper;
        this.sessionService = sessionService;
        this.activityDTOToActivityMapper = activityDTOToActivityMapper;
    }

    @PostMapping(value = "/startSession")
    public void createSession(@RequestBody ActivityDTO activityDTO) throws IllegalArgumentException {
        this.sessionService.startSession(this.activityDTOToActivityMapper.apply(activityDTO));
    }

    @PutMapping(value = "/editSession", params = {"endTime"})
    public void updateSession(@RequestBody SessionDTO sessionDTO, @RequestParam LocalDateTime endTime)
            throws IllegalArgumentException {
        this.sessionService.updateSession(this.sessionDTOToSessionMapper.apply(sessionDTO), endTime);
    }

    @GetMapping(value = "/listByAcitivity", params = {"activity"})
    public List<Session> listSessions(@RequestParam ActivityDTO activityDTO) throws IllegalArgumentException {
        return this.sessionService.listSessionsByActivity(this.activityDTOToActivityMapper.apply(activityDTO));
    }

    @DeleteMapping(value = "/deleteSession")
    public void deleteSession(@RequestParam SessionDTO sessionDTO) throws IllegalArgumentException {
        this.sessionService.deleteSession(this.sessionDTOToSessionMapper.apply(sessionDTO));
    }
}
