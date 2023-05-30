package ase.session;

import ase.types.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ase.activity.Activity;
import ase.activity.ActivityRepository;
import ase.category.Category;
import ase.session.Session;
import ase.session.SessionRepository;
import ase.session.SessionService;
import ase.timeframe.TimeFrame;
import ase.types.Estimation;
import ase.types.Unit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private ActivityRepository activityRepository;

    private SessionService sessionService;

    private Session session;
    private Activity activity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        sessionService = new SessionService(activityRepository, sessionRepository);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDate date = LocalDate.of(2024, 03, 28);
        LocalTime time = LocalTime.of(10, 34);
        LocalDateTime dueDate = LocalDateTime.of(date, time);
        Estimation estimation = new Estimation(150L, Unit.MINUTES);
        LocalTime start = LocalTime.of(7, 0);
        LocalTime end = LocalTime.of(17, 0);
        TimeFrame timeFrame = new TimeFrame(start, end);
        Category category = new Category("category", timeFrame);
        activity = new Activity("activity", dueDate, estimation, category);
        session = new Session( startTime, activity);
    }

    @Test
    void testStartSession() {
        Mockito.when(activityRepository.existsById(activity.getId())).thenReturn(true);
        sessionService.startSession(activity);
        Mockito.verify(activityRepository, Mockito.times(1)).save(activity);
        Mockito.verify(sessionRepository, Mockito.times(0)).delete(session);

    }

    @Test
    void testStartSessionWithNull() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sessionService.startSession(null),
                "Expected startSession to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("The Activity is invalid"));
        Mockito.verify(sessionRepository, Mockito.times(0)).save(session);
    }
    @Test
    void testStartSessionWithOverdueStartDate() {
        LocalDate date = LocalDate.of(2023, 03, 28);
        LocalTime time = LocalTime.of(10, 34);
        LocalDateTime overdueDate = LocalDateTime.of(date, time);
        Mockito.when(activityRepository.existsById(activity.getId())).thenReturn(true);
        activity.setDueDate(overdueDate);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sessionService.startSession(activity),
                "Expected startSession to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("this Activity is already overdue"));
        Mockito.verify(sessionRepository, Mockito.times(0)).save(session);
        Mockito.verify(activityRepository, Mockito.times(0)).save(activity);
    }
    @Test
    void testStartSessionWithActivityStatusDone() {
        activity.setStatus(Status.DONE);
        Mockito.when(activityRepository.existsById(activity.getId())).thenReturn(true);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sessionService.startSession(activity),
                "Expected startSession to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("this Activity has already been completed."));
        Mockito.verify(sessionRepository, Mockito.times(0)).save(session);
        Mockito.verify(activityRepository, Mockito.times(0)).save(activity);
    }
    @Test
    void testStartSessionWithNotExistentActivity() {
        Mockito.when(activityRepository.existsById(activity.getId())).thenReturn(false);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sessionService.startSession(activity),
                "Expected startSession to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("The Activity does not exist"));
        Mockito.verify(sessionRepository, Mockito.times(0)).save(session);
    }
    @Test
    void testStopSession() {
        Mockito.when(sessionRepository.isActive()).thenReturn(true);
        Mockito.when(sessionRepository.findActive()).thenReturn(session);
        sessionService.stopSession();
        Mockito.verify(sessionRepository, Mockito.times(1)).save(session);
        Mockito.verify(sessionRepository, Mockito.times(0)).delete(session);
    }

    @Test
    void testStopSessionWithNoActiveSessions() {
        Mockito.when(sessionRepository.isActive()).thenReturn(false);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sessionService.stopSession(),
                "Expected stopSession to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("There is no active Session"));
        Mockito.verify(sessionRepository, Mockito.times(0)).save(session);
    }
    @Test
    void testStopSessionWithInvalidSessions() {
        Mockito.when(sessionRepository.isActive()).thenReturn(true);
        Mockito.when(sessionRepository.findActive()).thenReturn(null);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sessionService.stopSession(),
                "Expected stopSession to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("The Session is invalid"));
        Mockito.verify(sessionRepository, Mockito.times(0)).save(session);
    }
    @Test
    void testListSessionsByActivity() {
        activity.setStatus(Status.IN_PROGRESS);
        List<Session> expectedSessions = new ArrayList<Session>();
        expectedSessions.add(session);
        Mockito.when(activityRepository.existsById(activity.getId())).thenReturn(true);
        Mockito.when(sessionRepository.findAllByActivity(activity)).thenReturn(expectedSessions);
        List<Session> actualSessions = sessionService.listSessionsByActivity(activity);
        assertEquals(expectedSessions, actualSessions);
        Mockito.verify(activityRepository, Mockito.times(1)).existsById(activity.getId());
        Mockito.verify(sessionRepository, Mockito.times(1)).findAllByActivity(activity);
    }
    @Test
    void testListSessionsByActivityWithStatusTodo() {
        activity.setStatus(Status.TODO);
        List<Session> expectedSessions = new ArrayList<Session>();
        Mockito.when(activityRepository.existsById(activity.getId())).thenReturn(true);
        List<Session> actualSessions = sessionService.listSessionsByActivity(activity);
        assertEquals(expectedSessions, actualSessions);
        Mockito.verify(activityRepository, Mockito.times(1)).existsById(activity.getId());
        Mockito.verify(sessionRepository, Mockito.times(0)).findAllByActivity(activity);
    }
}