package ru.thetenzou.optaplanertests.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.thetenzou.optaplanertests.model.Timeslot;

import java.util.List;

public interface TimeslotRepository extends PagingAndSortingRepository<Timeslot, Long> {

    @Override
    List<Timeslot> findAll();

}
