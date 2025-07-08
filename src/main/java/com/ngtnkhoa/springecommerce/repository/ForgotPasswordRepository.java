package com.ngtnkhoa.springecommerce.repository;

import com.ngtnkhoa.springecommerce.entity.ForgotPassword;
import com.ngtnkhoa.springecommerce.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

  @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
  Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
}
