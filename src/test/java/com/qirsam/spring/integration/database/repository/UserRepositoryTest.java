package com.qirsam.spring.integration.database.repository;

import com.qirsam.spring.database.entity.Role;
import com.qirsam.spring.database.repository.UserRepository;
import com.qirsam.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkQueries() {
        var users = userRepository.findAllBy("a", "ov");
        System.out.println(users);
    }

    @Test
    void findAllByUsername() {
        var users = userRepository.findAllByUsername("petr@gmail.com");
        assertThat(users).hasSize(1);
    }

    @Test
    void checkUpdate() {
        var ivan = userRepository.getReferenceById(1L);
        assertThat(ivan.getRole()).isEqualByComparingTo(Role.ADMIN);

        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertThat(resultCount).isEqualTo(2);

        var theSameIvan = userRepository.getReferenceById(1L);
        assertThat(theSameIvan.getRole()).isEqualByComparingTo(Role.USER);

    }

}