package ase.session;

import ase.activity.Activity;

import java.util.List;

public interface SessionRepository {
    void save(Session session);
    List<Session> findAll();
    Session getById(Long id);
    void delete(Session session);
}
