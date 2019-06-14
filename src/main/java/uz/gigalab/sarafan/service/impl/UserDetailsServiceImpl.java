package uz.gigalab.sarafan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.gigalab.sarafan.domain.User;
import uz.gigalab.sarafan.repository.UserDetailsRepository;
import uz.gigalab.sarafan.service.UserDetailsService;

import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public Optional<User> findOne(String id) {
        return userDetailsRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userDetailsRepository.save(user);
    }
}
