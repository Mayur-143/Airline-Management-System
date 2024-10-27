//package com.example.AirlineManagementSystem.service;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.example.AirlineManagementSystem.model.CustomUserDetails;
//import com.example.AirlineManagementSystem.model.User;
//import com.example.AirlineManagementSystem.repository.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService{
//
//    private UserRepository userRepository;
//
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        super();
//        this.userRepository = userRepository;
//
//    }
//
//
//
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // TODO Auto-generated method stub
//
//        User user=userRepository.findByPrimaryEmail(email).orElse(null);
//        System.out.println(user);
//        if (user==null) {
//            throw new UsernameNotFoundException("Username of password is not found!");
//        }
//        else{
//
//            return new CustomUserDetails(user.getPrimaryEmail(), user.getPassword());
//        }
//
//    }
//
//    public Collection<? extends GrantedAuthority> authorities() {
//        return Arrays.asList(new SimpleGrantedAuthority("USER"));
//    }
//
//
//}