package com.alkes.alkse.service;

import com.alkes.alkse.model.Admin;
import com.alkes.alkse.repository.AdminRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Admin admin = adminRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + username));
        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .authorities(admin.getRole())
                .build();
    }

    public List<Admin> findAll(){
        return adminRepository.findAll();
    }

    public Optional<Admin> findByIdAdmin(Long id) {
        return adminRepository.findById(id);
    }
    public Admin saveAdmin(Admin admin) {
        String rawPass = admin.getPassword();
        String encoded = passwordEncoder.encode(rawPass);

        System.out.println("DEBUG SaveAdmin: raw=" + rawPass + " | encoded=" + encoded);

        admin.setPassword(encoded);
        admin.setRole("ROLE_ADMIN");
        return adminRepository.save(admin);
    }

    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }
    public void updatePassword(Long id, String newPassword) {
        adminRepository.findById(id).ifPresent(admin -> {
            admin.setPassword(passwordEncoder.encode(newPassword));
            adminRepository.save(admin);
        });
    }

}
