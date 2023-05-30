package ase.session;

import ase.activity.Activity;

import java.util.List;

public interface SessionRepository {
    void save(Session session);
    List<Session> findAll();
    List<Session> findAllByActivity(Activity activity);
    Session getById(Long id);
    boolean existsById(Long id);
    boolean isActive();
    Session findActive();
    void delete(Session session);
}
