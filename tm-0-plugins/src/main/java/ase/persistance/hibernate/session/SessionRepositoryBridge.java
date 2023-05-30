package ase.persistance.hibernate.session;


import ase.session.Session;
import ase.session.SessionRepository;
import ase.persistance.hibernate.session.SpringDataSessionRepository;
import ase.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionRepositoryBridge implements SessionRepository {
    private SpringDataSessionRepository springDataSessionRepository;

    @Autowired
    public SessionRepositoryBridge(SpringDataSessionRepository springDataSessionRepository) {
        this.springDataSessionRepository = springDataSessionRepository;
    }

    @Override
    public void save(Session session) {
        this.springDataSessionRepository.save(session);
    }

    @Override
    public Session getById(Long id) {
        return springDataSessionRepository.getById(id);
    }

    @Override
    public List<Session> findAll() {
        return springDataSessionRepository.findAll();
    }

    @Override
    public void delete(Session session) {
        springDataSessionRepository.delete(session);
    }
}