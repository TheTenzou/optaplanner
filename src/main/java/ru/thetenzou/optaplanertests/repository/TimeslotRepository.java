package ru.thetenzou.optaplanertests.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.thetenzou.optaplanertests.model.TimeSlot;

import java.util.List;

public interface TimeslotRepository extends PagingAndSortingRepository<TimeSlot, Long> {

    @Override
    List<TimeSlot> findAll();

}
