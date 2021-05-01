package ru.thetenzou.optaplanertests.controller;

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

    @GetMapping
    public TimeTable getTimeTable() {
        return timeTableRepository.findById(1L);
    }

    @PostMapping("/solve")
    public void solve() {
        throw new UnsupportedOperationException();
    }


}
