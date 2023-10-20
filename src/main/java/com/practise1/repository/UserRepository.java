package com.practise1.repository;


import com.practise1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where account = ? and password = ?  ", nativeQuery = true)
    Optional<User> searchUser(@Param("account")String account,
                              @Param("password")String password);
}
