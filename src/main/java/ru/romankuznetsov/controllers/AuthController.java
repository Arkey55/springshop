package ru.romankuznetsov.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.romankuznetsov.beans.JwtTokenUtil;
import ru.romankuznetsov.dto.JwtRequest;
import ru.romankuznetsov.dto.JwtResponse;
import ru.romankuznetsov.service.UserService;

@RestController
@AllArgsConstructor
@Api("Set of endpoint for new user authentication")
public class AuthController {
    private UserService userService;
    private JwtTokenUtil jwtTokenUtil;
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    @ApiOperation("Create token foe user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", defaultValue = "bob1", required = true, dataTypeClass = String.class, type = "String", paramType = "query",
                    value = "Username"),
            @ApiImplicitParam(name = "password", defaultValue = "111", required = true, dataTypeClass = String.class, type = "String", paramType = "query",
                    value = "Password")})
    public ResponseEntity<?> createToken(@RequestBody JwtRequest jwtRequest){
//        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//        } catch (BadCredentialsException e){
//            return new ResponseEntity<>()
//        }
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
