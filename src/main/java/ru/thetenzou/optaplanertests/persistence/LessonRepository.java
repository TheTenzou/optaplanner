package ru.thetenzou.optaplanertests.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.thetenzou.optaplanertests.domain.Lesson;

import java.util.List;

public interface LessonRepository extends PagingAndSortingRepository<Lesson, Long> {

    @Override
    List<Lesson> findAll();

}
