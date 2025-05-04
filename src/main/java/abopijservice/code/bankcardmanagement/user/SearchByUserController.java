package abopijservice.code.bankcardmanagement.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/user/search")
@RequiredArgsConstructor
@Api(value = "/api/v1")
public class SearchByUserController {

    private final UserService userService;

    @ApiOperation(value = "Search users by different param (required false)")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<UserDTO> search(
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String username,
                             @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "10") Integer count
                             ) {
        if (email != null) {
            return userService.getUsersDTOByEmail(email, page, count);
        }
        if (username != null) {
            return userService.getUsersDTOByUsername(username, page, count);
        }
        return new PageImpl<>(userService.getAllUsersDTO());
    }
}
