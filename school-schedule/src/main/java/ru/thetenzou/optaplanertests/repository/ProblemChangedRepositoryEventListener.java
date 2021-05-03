package ru.thetenzou.optaplanertests.repository;

import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;
import ru.thetenzou.optaplanertests.controller.TimeTableController;
import ru.thetenzou.optaplanertests.model.Lesson;
import ru.thetenzou.optaplanertests.model.Room;
import ru.thetenzou.optaplanertests.model.Timeslot;

/**
 * make sure that CRUD operation are not allowed
 */
@Component
@RepositoryEventHandler
public class ProblemChangedRepositoryEventListener {

    @Autowired
    private TimeTableController timeTableController;

    @HandleBeforeCreate
    @HandleBeforeSave
    @HandleBeforeDelete
    private void timeslotCreateSaveDelete(Timeslot timeslot) {
        assertNotSolving();
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    @HandleBeforeDelete
    private void roomCreateSaveDelete(Room room) {
        assertNotSolving();
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    @HandleBeforeDelete
    private void lessonCreateSaveDelete(Lesson lesson) {
        assertNotSolving();
    }

    private void assertNotSolving() {
        if (timeTableController.getSolverStatus() != SolverStatus.NOT_SOLVING) {
            throw new IllegalArgumentException("The solver is solving. Please stop solving first.");
        }
    }
}
