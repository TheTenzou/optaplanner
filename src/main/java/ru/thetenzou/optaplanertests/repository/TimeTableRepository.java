package ru.thetenzou.optaplanertests.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.thetenzou.optaplanertests.model.Lesson;
import ru.thetenzou.optaplanertests.model.TimeTable;

import javax.transaction.Transactional;

@Service
@Transactional
public class TimeTableRepository {

    public static final Long SINGLETON_TIME_TABLE_ID = 1L;

    @Autowired
    private TimeslotRepository timeslotRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private LessonRepository lessonRepository;

    public TimeTable findById(Long id) {
        if (!SINGLETON_TIME_TABLE_ID.equals(id)) {
            throw new IllegalArgumentException("There is no timeTable with id: " + id);
        }

        return new TimeTable(
                timeslotRepository.findAll(),
                roomRepository.findAll(),
                lessonRepository.findAll()
        );
    }

    public void save(TimeTable timeTable) {
        for (Lesson lesson : timeTable.getLessonList()) {
            lessonRepository.save(lesson);
        }
    }
}
