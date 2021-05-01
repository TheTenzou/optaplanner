package ru.thetenzou.optaplanertests.controller;

import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.thetenzou.optaplanertests.model.TimeTable;
import ru.thetenzou.optaplanertests.repository.TimeTableRepository;
import ru.thetenzou.optaplanertests.repository.TimeslotRepository;

@RestController
@RequestMapping("/timeTable")
public class TimeTableController {

    @Autowired
    private TimeTableRepository timeTableRepository;
    @Autowired
    private SolverManager<TimeTable, Long> solverManager;

    @GetMapping
    public TimeTable getTimeTable() {
        return timeTableRepository.findById(1L);
    }

    @PostMapping("/solve")
    public void solve() {
        solverManager.solveAndListen(
                TimeTableRepository.SINGLETON_TIME_TABLE_ID,
                timeTableRepository::findById,
//                (problemId) -> timeTableRepository.findById(problemId),
                timeTableRepository::save
        );
    }


}
