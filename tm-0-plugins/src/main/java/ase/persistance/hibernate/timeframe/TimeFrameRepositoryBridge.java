package ase.persistance.hibernate.timeframe;


import ase.activity.Activity;
import ase.timeframe.TimeFrame;
import ase.timeframe.TrimeFrameRepository;
import ase.persistance.hibernate.activity.SpringDataActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeFrameRepositoryBridge implements ase.timeframe.TrimeFrameRepository {
    private SpringDataTimeFrameRepository springDataTimeFrameRepository;

    @Autowired
    public TimeFrameRepositoryBridge(SpringDataTimeFrameRepository springDataTimeFrameRepository)
    {
        this.springDataTimeFrameRepository = springDataTimeFrameRepository;
    }

    @Override
    public void save(TimeFrame timeFrame) {
        this.springDataTimeFrameRepository.save(timeFrame);
    }


    @Override
    public List<TimeFrame> findAll() {
        return springDataTimeFrameRepository.findAll();
    }
    @Override
    public boolean existsById(Long id) {
       if(springDataTimeFrameRepository.findById(id).isPresent()){
           return true;
        }
       else{
           return false;
       }
    }

    @Override
    public void delete(TimeFrame timeFrame) {
        springDataTimeFrameRepository.delete(timeFrame);
    }
}