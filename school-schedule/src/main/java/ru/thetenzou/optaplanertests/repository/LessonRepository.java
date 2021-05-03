package ru.thetenzou.optaplanertests.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.thetenzou.optaplanertests.model.Lesson;

import java.util.List;

public interface LessonRepository extends PagingAndSortingRepository<Lesson, Long> {

    @Override
    List<Lesson> findAll();

}
