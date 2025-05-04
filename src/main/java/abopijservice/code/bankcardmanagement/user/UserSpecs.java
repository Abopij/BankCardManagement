package abopijservice.code.bankcardmanagement.user;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecs {
    public static Specification<User> equalsToUsername(String username) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.like(root.get("username"), "%" + username + "%"));
    }

    public static Specification<User> equalsToEmail(String email) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.like(root.get("email"), "%" + email + "%"));
    }
}
