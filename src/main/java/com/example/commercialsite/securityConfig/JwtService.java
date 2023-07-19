package com.example.commercialsite.securityConfig;

import com.example.commercialsite.dto.request.LoginRequest;
import com.example.commercialsite.dto.response.LoginResponse;
import com.example.commercialsite.entity.User;
import com.example.commercialsite.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }


    public LoginResponse createJwtToken(LoginRequest loginRequest) throws Exception{
        String userName = loginRequest.getUserName();
        String userPassword = loginRequest.getUserPassword();

        authenticate(userName , userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        User user = userRepo.findByEmail(userName).get();

        LoginResponse loginResponse  = new LoginResponse(
                user,
                newGeneratedToken
        );
        return loginResponse;
    }



    private void authenticate(String userName,String userPassword) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
        }catch (BadCredentialsException e){
            throw new Exception("invalid credentials",e);
        }
    }


}

