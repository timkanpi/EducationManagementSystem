package ru.sber.EducationManagementSystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sber.EducationManagementSystem.entity.Role;
import ru.sber.EducationManagementSystem.entity.User;
import ru.sber.EducationManagementSystem.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User username= " + username + " not found"));
    }

    /**
     * Поиск пользователя по username
     *
     * @param username email пользователя
     * @return true - если пользователь с username существует
     */
    public boolean isExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * Создать пользователя в системе
     *
     * @param username "email пользователя"
     * @param roleSet  Set ролей пользователя
     * @return созданный пользователь
     */
    public User createUser(String username, String password, Set<Role> roleSet) {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(roleSet)
                .build();

        user = userRepository.save(user);

        log.info("User создан: {}", user);

        return user;
    }
}
