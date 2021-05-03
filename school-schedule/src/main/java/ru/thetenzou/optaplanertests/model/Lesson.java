package ru.thetenzou.optaplanertests.model;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import javax.persistence.*;

@PlanningEntity
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String subject;
    private String teacher;
    private String studentGroup;

    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    @ManyToOne
    private Timeslot timeslot;
    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    @ManyToOne
    private Room room;

    public Lesson(){
    }

    public Lesson(String subject, String teacher, String studentGroup) {
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeSlot) {
        this.timeslot = timeSlot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                ", studentGroup='" + studentGroup + '\'' +
                ", timeSlot=" + timeslot +
                ", room=" + room +
                '}';
    }
}
