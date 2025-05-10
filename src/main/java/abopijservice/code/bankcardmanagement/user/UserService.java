package abopijservice.code.bankcardmanagement.user;

import abopijservice.code.bankcardmanagement.user.search.UserSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public void save(User user) {
        userRepo.save(user);
    }

    public User getUserByID(UUID id) {
        return userRepo.getReferenceById(id);
    }

    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public List<UserDTO> getAllUsersDTO() {
        return userRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    public boolean changeEmail(String username, String email) {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if (principal.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            user = userRepo.getUserByUsername(username);
        } else {
            user = userRepo.getUserByUsername(principal.getName());
        }

        user.setEmail(email);

        update(user);

        return true;
    }

    public void update(User user) {
        userRepo.saveAndFlush(user);
    }

    public User getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    public Page<UserDTO> getUsersDTOByEmail(String email, int numberPage, int countMaxInPage) {
        Specification<User> filter = Specification.where(null);
        filter = filter.and(UserSpecs.equalsToEmail(email));

        return userRepo.findAll(filter, PageRequest.of(numberPage, countMaxInPage)).map(this::convertToDTO);
    }

    public Page<UserDTO> getUsersDTOByUsername(String username, int numberPage, int countMaxInPage) {
        Specification<User> filter = Specification.where(null);
        filter = filter.and(UserSpecs.equalsToUsername(username));

        return userRepo.findAll(filter, PageRequest.of(numberPage, countMaxInPage)).map(this::convertToDTO);
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

}
