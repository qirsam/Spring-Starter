package com.qirsam.spring.database.repository;

import com.qirsam.spring.database.entity.Role;
import com.qirsam.spring.database.entity.User;
import com.qirsam.spring.dto.PersonalInfo2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.history.RevisionRepository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>,
        FilterUserRepository,
        RevisionRepository<User, Long, Integer> {

    @Query("select u from User u " +
           "where u.firstname like %:firstname% and u.lastname like %:lastname% ")
    List<User> findAllBy(String firstname, String lastname);

    @Query(nativeQuery = true,
            value = "SELECT * FROM users u WHERE u.username = :username")
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("update User u " +
           "set u.role = :role " +
           "where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findFirstByOrderByIdDesc();

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findFirst3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Query(value = "select u from User u",
            countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);

//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);

    @Query(value = "SELECT firstname," +
                   "lastname," +
                   "birth_date birthDate " +
                   "FROM users " +
                   "WHERE company_id= :companyId",
            nativeQuery = true)
    List<PersonalInfo2>  findAllByCompanyId(Integer companyId);
}
