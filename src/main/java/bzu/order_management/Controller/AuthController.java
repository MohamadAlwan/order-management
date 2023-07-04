package bzu.order_management.Controller;


import bzu.order_management.DTO.LoginDto;
import bzu.order_management.DTO.SignUpDto;
import bzu.order_management.Entity.Role;
import bzu.order_management.Entity.User;
import bzu.order_management.Payload.JWTAuthResponse;
import bzu.order_management.Reposetory.RoleRepository;
import bzu.order_management.Reposetory.UserRepository;
import bzu.order_management.Security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Api(value = "Auth controller exposes sign in and signup REST APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @ApiOperation(value = "REST API to Signin or Login user to order management application")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @ApiOperation(value = "REST API to Register or Signup user order management application")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");
        if (optionalRole.isPresent()) {
            Role roles = optionalRole.get();
            user.setRoles(Collections.singleton(roles));
            userRepository.save(user);

            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Role not found!", HttpStatus.INTERNAL_SERVER_ERROR);
        }




    }

}
