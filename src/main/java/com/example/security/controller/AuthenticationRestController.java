package com.example.security.controller;

import com.example.logger.MyLogger;
import com.example.security.JwtAuthenticationRequest;
import com.example.security.JwtTokenUtil;
import com.example.security.JwtUser;
import com.example.security.service.JwtAuthenticationResponse;
import com.example.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paweł on 2017-04-17.
 */

@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) {

        MyLogger.log.info(this.getClass().getName() + " receiving request");
        MyLogger.log.info(this.getClass().getName() + " Mail: " + jwtAuthenticationRequest.getEmail());
        MyLogger.log.info(this.getClass().getName() + " Password: " + jwtAuthenticationRequest.getPassword());

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtAuthenticationRequest.getEmail(),
                            jwtAuthenticationRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(AuthenticationException e) {
            MyLogger.log.info(this.getClass().getName() + " Exception encountered");
        }


        JwtUser user =  jwtUserDetailsService.loadUserByUsername(jwtAuthenticationRequest.getEmail());
        String token = jwtTokenUtil.generateToken(user);

        MyLogger.log.info("SPRAWDZENIE POPRAWNOSCI WPROWADZONEGO HASLA");
        if(!user.getPassword().equals(jwtAuthenticationRequest.getPassword()))
            return new ResponseEntity<JwtAuthenticationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);

        MyLogger.log.info("sprawdzenie tokena " + jwtTokenUtil.getEmailFromToken(token));

        MyLogger.log.info(this.getClass().getName() + " User info: " + user.getUsername());
        MyLogger.log.info(this.getClass().getName() + " Authentication: " + SecurityContextHolder.getContext().getAuthentication().getName());
        MyLogger.log.info(this.getClass().getName() + " Token info: " +
                jwtTokenUtil.getClaimsFromToken(token).getSubject() + " " + jwtTokenUtil.getClaimsFromToken(token).getExpiration());

        Integer id;
        String userType;

        if(user.getClient() != null) {
            id = user.getClient().getId();
            userType = "Client";
        }
        else if(user.getCompany() != null){
            id = user.getCompany().getId();
            userType = "Company";
        }
        else {
            return new ResponseEntity<JwtAuthenticationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JwtAuthenticationResponse response = new JwtAuthenticationResponse(token, id, userType);

        return ResponseEntity.ok(response);
    }
}
