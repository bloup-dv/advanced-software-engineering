package ase.persistance.hibernate.timeframe;

import ase.activity.Activity;
import ase.session.Session;
import ase.timeframe.TimeFrame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SpringDataTimeFrameRepository extends JpaRepository<TimeFrame, Long> {
    TimeFrame getById(Long id);


}