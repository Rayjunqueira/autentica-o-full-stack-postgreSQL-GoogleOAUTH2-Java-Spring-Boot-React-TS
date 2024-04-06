package com.example.scheduleapi.controllers;
import com.example.scheduleapi.dtos.UserDto;
import com.example.scheduleapi.mappers.UserMapper;
import com.example.scheduleapi.models.UserModel;
import com.example.scheduleapi.security.JwtTokenProvider;
import com.example.scheduleapi.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
public class HomeController {
    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    private OAuth2AuthorizedClientService oauthAuthorizedClientService;

    public HomeController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/")
    public ResponseEntity <?> home(OAuth2AuthenticationToken oauth2Token, HttpServletResponse response) {
        OAuth2AuthenticatedPrincipal oauth2Principal = oauth2Token.getPrincipal();
        OAuth2AuthorizedClient authorizedClient = oauthAuthorizedClientService.loadAuthorizedClient(
                oauth2Token.getAuthorizedClientRegistrationId(), oauth2Token.getName());
        String name = oauth2Principal.getAttribute("name");
        String email = oauth2Principal.getAttribute("email");

        Optional<UserModel> getUser = userService.findByEmail(email);


        Random random = new Random();
        int randomNumber = random.nextInt(9);
        String randomNumberAsString = String.valueOf(randomNumber);

        String jwt = null;
        String cryptoPassword = "";

        if (getUser.isPresent()) {
            try {
                UserModel user = getUser.get();
                cryptoPassword = passwordEncoder().encode(randomNumberAsString);
                user.setPassword(cryptoPassword);
                user.setName(name);
                user.setEmail(email);

                userService.save(user);

                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                email,
                                randomNumberAsString
                        )
                );
                jwt = jwtTokenProvider.generateToken(authentication);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!getUser.isPresent()) {
            UserDto newUserDto = new UserDto();
            newUserDto.setName(name);
            newUserDto.setEmail(email);
            newUserDto.setPassword(randomNumberAsString);

            UserModel newUser = userMapper.toUserModel(newUserDto);
            cryptoPassword = passwordEncoder().encode(randomNumberAsString);
            newUser.setPassword(cryptoPassword);
            userService.save(newUser);


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            randomNumberAsString
                    )
            );
            jwt = jwtTokenProvider.generateToken(authentication);
        }

        jwt = jwt.replace("/", "-");
        name = name.replace("/", "-");
        email = email.replace("/", "-");
        cryptoPassword = cryptoPassword.replace("/", "-");

        try {
            response.sendRedirect("http://localhost:3000/googleauth/" + jwt + "/" + name + "/" + email + "/" + cryptoPassword);

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}