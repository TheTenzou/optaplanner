package ru.thetenzou.optaplanertests.controller;

import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.thetenzou.optaplanertests.model.TimeTable;
import ru.thetenzou.optaplanertests.repository.TimeTableRepository;

@RestController
@RequestMapping("/timeTable")
public class TimeTableController {

    @Autowired
    private TimeTableRepository timeTableRepository;
    @Autowired
    private SolverManager<TimeTable, Long> solverManager;
    @Autowired
    private ScoreManager<TimeTable> scoreManager;

    @GetMapping
    public TimeTable getTimeTable() {
        SolverStatus solverStatus = getSolverStatus();
        TimeTable solution = timeTableRepository.findById(TimeTableRepository.SINGLETON_TIME_TABLE_ID);
        scoreManager.updateScore(solution);
        solution.setSolverStatus(solverStatus);
        return solution;
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

    public SolverStatus getSolverStatus() {
        return solverManager.getSolverStatus(TimeTableRepository.SINGLETON_TIME_TABLE_ID);
    }

    @PostMapping("/stopSolving")
    public void stopSolving() {
        solverManager.terminateEarly(TimeTableRepository.SINGLETON_TIME_TABLE_ID);
    }

}
