package ase.persistance.hibernate.session;

import ase.activity.Activity;
import ase.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SpringDataSessionRepository extends JpaRepository<Session, Long> {
    Session getById(Long id);
    boolean findByEndTimeIsNull();
    Long findFirstByEndTimeIsNull();
    List<Session> findAllByActivity(Activity activity);
    boolean existsById(Long id);


}
