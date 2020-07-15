package petonline.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import petonline.core.config.Paths;
import petonline.core.config.security.JwtTokenProvider;
import petonline.core.model.User;
import petonline.core.model.dto.LoginRequest;
import petonline.core.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(Paths.AUTH)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService service;

    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody LoginRequest dto) {
        try {
            String username = dto.getUsername();
            String password = dto.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = this.service.findByEmail(username);

            String token = "";
            if (null != user) {
                token = tokenProvider.createToken(username, user.getPermissions().stream().map(a -> a.getAuthority()).collect(Collectors.toList()));
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }

    }
}
