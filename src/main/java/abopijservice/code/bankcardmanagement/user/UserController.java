package abopijservice.code.bankcardmanagement.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Api(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Change email")
    @PatchMapping("/change/email")
    public ResponseEntity<?> changeEmail(@RequestBody String email, @RequestBody(required = false) String username) {
        return userService.changeEmail(username, email) ? ResponseEntity.ok("Ok") : ResponseEntity.status(400).build();
    }
}
