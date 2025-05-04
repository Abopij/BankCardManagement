package abopijservice.code.bankcardmanagement.security.auth;

import abopijservice.code.bankcardmanagement.security.JwtService;
import abopijservice.code.bankcardmanagement.user.Role;
import abopijservice.code.bankcardmanagement.user.User;
import abopijservice.code.bankcardmanagement.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User(request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getEmail(),
                request.getRole() != null ? request.getRole() : Role.USER
        );

        userService.save(user);

        return new AuthenticationResponse(jwtService.generateToken(user));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (request.getEmail() != null) {
            User user = userService.getUserByEmail(request.getEmail());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            request.getPassword()
                    )
            );
            return new AuthenticationResponse(jwtService.generateToken(user));

        } else {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            User user = userService.getUserByUsername(request.getUsername());

            return new AuthenticationResponse(jwtService.generateToken(user));
        }
    }
}
