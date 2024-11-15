package com.company.controller;

import com.company.dto.UserDto;
import com.company.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * registration and sending code via email
     * @param userDto
     * @return responseEntity
     */

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto) {

        String jwt = authService.signup(userDto);;

        return ResponseEntity.ok().body(jwt);
    }

    /**
     * checking mail code is valid
     * @param jwt
     * @param code
     * @return responseEntity
     */
    @GetMapping("/check/{code}")
    public ResponseEntity<UserDto> checkMailCode(@RequestHeader("Authorization") String jwt, @PathVariable("code") String code) {

        UserDto userDto = authService.checkMailCode(jwt, code);

        return ResponseEntity.ok().body(userDto);
    }
}
