package za.co.staffschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.staffschedule.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByUserName(String userName);
    Staff findByEmail(String email);
}
