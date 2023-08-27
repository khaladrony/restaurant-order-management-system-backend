package com.rony.restaurant.repository;


import com.rony.restaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("select count(u) from User u where u.id <> ?1 AND u.username = ?2 ")
    int countByUsername(Long id, String username);
}
