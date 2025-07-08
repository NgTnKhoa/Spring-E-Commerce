package com.ngtnkhoa.springecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ngtnkhoa.springecommerce.enums.UserRole;
import com.ngtnkhoa.springecommerce.validator.ValidPassword;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
public class User extends Base implements UserDetails {

  @NotBlank(message = "Name is required")
  @Column(name = "name")
  private String name;

  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @NotBlank(message = "Username is required")
  @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters long")
  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @NotBlank(message = "Password is required")
  @ValidPassword
  @Column(name = "password")
  private String password;

  @Pattern(regexp = "^[0-9]{10}$", message = "User mobile phone must have 10 digits")
  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private UserRole role;

  @OneToOne(mappedBy = "user")
  private ForgotPassword forgotPassword;

  @OneToMany(mappedBy = "user")
  @Cascade(CascadeType.ALL)
  private List<Token> tokens;

  @OneToMany(mappedBy = "user")
  @Cascade(CascadeType.ALL)
  private List<Payment> payments;

  @OneToMany(mappedBy = "user")
  @Cascade(CascadeType.ALL)
  private List<Order> orders;

  @OneToMany(mappedBy = "user")
  @Cascade(CascadeType.ALL)
  private List<Review> reviews;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
