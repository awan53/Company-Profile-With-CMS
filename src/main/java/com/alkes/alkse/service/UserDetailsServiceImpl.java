package com.alkes.alkse.service;

import com.alkes.alkse.model.Admin;
import com.alkes.alkse.repository.AdminRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    public UserDetailsServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Gunakan password yang sudah hashed dari DB
        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword()) // <-- ini HASH, jangan di-encode ulang
                .roles("ADMIN") // sesuaikan dengan role
                .build();
    }
}
