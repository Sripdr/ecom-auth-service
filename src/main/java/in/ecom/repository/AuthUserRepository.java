package in.ecom.repository;

import in.ecom.dao_entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

    Optional<AuthUser> findByEmail(String email);
    Optional<AuthUser> findByPhoneNumber(String phoneNumber);

    Optional<AuthUser> findByEmailAndPhoneNumber(String email, String phoneNumber);


}