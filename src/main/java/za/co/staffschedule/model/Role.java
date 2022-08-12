package za.co.staffschedule.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends Domain  implements GrantedAuthority {
    @Column(name = "name")
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
