package com.canoerent.repository;

import com.canoerent.model.Rent;
import com.canoerent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    void deleteByEmail(String email);
}
