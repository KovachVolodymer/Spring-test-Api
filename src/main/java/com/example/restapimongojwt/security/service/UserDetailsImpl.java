package com.example.restapimongojwt.security.service;

import com.example.restapimongojwt.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDetailsImpl implements UserDetails {

    private String id;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String id, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // Тут повертаємо email як ім'я користувача. Ви можете вибрати поле, яке вам підходить.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Ваша логіка для перевірки терміну дії облікового запису (true, якщо обліковий запис діє).
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Ваша логіка для перевірки блокування облікового запису (true, якщо обліковий запис не заблокований).
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Ваша логіка для перевірки терміну дії облікових даних (true, якщо облікові дані дійсні).
    }

    @Override
    public boolean isEnabled() {
        return true; // Ваша логіка для перевірки активації облікового запису (true, якщо обліковий запис активний).
    }
}