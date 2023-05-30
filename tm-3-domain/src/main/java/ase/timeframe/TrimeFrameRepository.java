package ase.timeframe;

import ase.activity.Activity;

import java.util.List;

public interface TrimeFrameRepository {
    void save(TimeFrame timeFrame);
    boolean existsById(Long id);
    List<TimeFrame> findAll();
    void delete(TimeFrame timeFrame);
}
