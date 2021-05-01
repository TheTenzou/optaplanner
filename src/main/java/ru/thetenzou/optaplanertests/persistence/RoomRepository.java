package ru.thetenzou.optaplanertests.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.thetenzou.optaplanertests.domain.Room;

import java.util.List;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

    @Override
    List<Room> findAll();

}
