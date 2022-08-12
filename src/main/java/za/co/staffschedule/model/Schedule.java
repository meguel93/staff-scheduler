package za.co.staffschedule.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class Schedule extends Domain {
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "work_date")
    private LocalDate workDate;

    @Column(name = "shift_hours_worked")
    private int shiftInHours;
}
