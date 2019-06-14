package uz.gigalab.sarafan.service;

import uz.gigalab.sarafan.domain.User;

import java.util.Optional;

public interface UserDetailsService {
    Optional<User> findOne(String id);
    User save(User user);
}
