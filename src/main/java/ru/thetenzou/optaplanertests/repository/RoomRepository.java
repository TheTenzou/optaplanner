package ru.thetenzou.optaplanertests.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.thetenzou.optaplanertests.model.Room;

import java.util.List;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

    @Override
    List<Room> findAll();

}
