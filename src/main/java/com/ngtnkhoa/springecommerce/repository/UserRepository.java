package com.ngtnkhoa.springecommerce.repository;

import com.ngtnkhoa.springecommerce.entity.User;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  boolean existsByPhoneNumber(String phoneNumber);

  User findByEmail(String email);

  @Transactional
  @Modifying
  @Query("update User u set u.password = ?2 where u.email = ?1")
  void updatePassword(String email, String password);
}
