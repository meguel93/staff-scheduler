package za.co.staffschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.staffschedule.model.Schedule;
import za.co.staffschedule.model.Staff;

import java.util.Date;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    //@Query("select s from schedule where s.")
    Schedule findByStaffUserNameAndWorkDate(String username, Date workDate);
}
