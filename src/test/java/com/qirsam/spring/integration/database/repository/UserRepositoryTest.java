package com.qirsam.spring.integration.database.repository;

import com.qirsam.spring.database.entity.Role;
import com.qirsam.spring.database.entity.User;
import com.qirsam.spring.database.repository.UserRepository;
import com.qirsam.spring.dto.UserFilter;
import com.qirsam.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;


    @Test
    @Commit
    void checkAuditing() {
        var ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1L));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkCustomImplementation() {
        UserFilter filter = new UserFilter(
                null, "%ov%", LocalDate.now()
        );
        var users = userRepository.findAllByFilter(filter);
        System.out.println();

    }
    @Test
    void checkProjections() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkPageable() {
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var page = userRepository.findAllBy(pageable);
        page.forEach(user -> System.out.println(user.getCompany().getName()));

        while (page.hasNext()) {
            page = userRepository.findAllBy(page.nextPageable());
            page.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkSort() {
        var sortBy = Sort.sort(User.class);
        var sort = sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));

        var sortById = Sort.by("id");
        var allUsers = userRepository.findFirst3ByBirthDateBefore(LocalDate.now(), sort.descending());
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirstTop() {
        var firstUser = userRepository.findFirstByOrderByIdDesc();
        assertThat(firstUser).isPresent();
        firstUser.ifPresent(user -> assertThat(user.getId()).isEqualTo(5L));
    }

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