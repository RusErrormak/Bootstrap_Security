package org.ruserrormak.bootstrap_spring.repository;

import org.ruserrormak.bootstrap_spring.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.roles WHERE u.email = :email")
    Users findByEmail(String email);
    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    Users findByUsername(String username);
}
