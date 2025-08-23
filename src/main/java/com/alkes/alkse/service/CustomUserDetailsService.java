package com.alkes.alkse.service;

import com.alkes.alkse.model.Admin;
import com.alkes.alkse.repository.AdminRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    public CustomUserDetailsService (AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("DEBUG LoadUser: username=" + admin.getUsername() +
                " | passwordFromDB=" + admin.getPassword());

        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword()) // langsung dari DB
                .roles("ADMIN")
                .build();
    }

}
