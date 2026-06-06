package in.ecom.dao_entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "auth_user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthUser implements UserDetails {

    @Id
    private String userId;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    private String phoneNumber;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

       private List<Roles> roles=new ArrayList<>();

    @PrePersist
    public void setUserId() {
        this.userId = "USER" + UUID.randomUUID().toString().toUpperCase().replace("-", "").substring(0, 8);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRoleName())).toList();

    }

    @Override
    public String getUsername() {
        return email==null ? phoneNumber:email;
    }
}