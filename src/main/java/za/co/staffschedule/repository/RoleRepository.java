package za.co.staffschedule.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.staffschedule.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String theRoleName);

}
