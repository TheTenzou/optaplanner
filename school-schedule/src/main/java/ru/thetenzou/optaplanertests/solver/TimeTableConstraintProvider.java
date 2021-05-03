package ru.thetenzou.optaplanertests.solver;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;
import ru.thetenzou.optaplanertests.model.Lesson;

import java.time.Duration;

public class TimeTableConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                roomConflict(constraintFactory),
                teacherConflict(constraintFactory),
                studentGroupConflict(constraintFactory),
                teacherRoomStability(constraintFactory),
                teacherTimeEfficiency(constraintFactory),
                studentGroupSubjectVariety(constraintFactory)
//                covidCounterStreamRoom(constraintFactory)
        };
    }

    private Constraint roomConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getRoom))
                .penalize("Room conflict", HardSoftScore.ONE_HARD);
    }

    private Constraint teacherConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getTeacher))
                .penalize("Teacher conflict", HardSoftScore.ONE_HARD);
    }

    private Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getStudentGroup))
                .penalize("Student group conflict", HardSoftScore.ONE_HARD);
    }

    private Constraint covidCounterStreamRoom(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getStudentGroup),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()),
                        Joiners.equal(
                                (lesson) -> lesson.getTimeslot().getStartTime(),
                                (lesson) -> lesson.getTimeslot().getEndTime())
                )
                .filter(((lesson1, lesson2) -> lesson1.getRoom().getName().compareTo(lesson2.getRoom().getName()) < 0))
                .penalize("Covid counter stream room", HardSoftScore.ONE_HARD);

    }

    private Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getTeacher)
                )
                .filter((lesson1, lesson2) -> lesson1.getRoom() != lesson2.getRoom())
                .penalize("Teacher room stability", HardSoftScore.ONE_SOFT);
    }

    private Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class, Joiners.equal(Lesson::getTeacher),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
                            lesson2.getTimeslot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                })
                .reward("Teacher time efficiency", HardSoftScore.ONE_SOFT);
    }

    private Constraint studentGroupSubjectVariety(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getSubject),
                        Joiners.equal(Lesson::getStudentGroup),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
                            lesson2.getTimeslot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                })
                .penalize("Student group subject variety", HardSoftScore.ONE_SOFT);
    }
}
