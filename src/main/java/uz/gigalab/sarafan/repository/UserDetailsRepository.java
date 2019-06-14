package uz.gigalab.sarafan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.gigalab.sarafan.domain.User;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, String > {
}
