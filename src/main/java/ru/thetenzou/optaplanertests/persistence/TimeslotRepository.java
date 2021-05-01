package ru.thetenzou.optaplanertests.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.thetenzou.optaplanertests.domain.TimeSlot;

import java.util.List;

public interface TimeslotRepository extends PagingAndSortingRepository<TimeSlot, Long> {

    @Override
    List<TimeSlot> findAll();

}
