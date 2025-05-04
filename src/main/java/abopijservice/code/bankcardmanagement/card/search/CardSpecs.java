package abopijservice.code.bankcardmanagement.card.search;

import abopijservice.code.bankcardmanagement.card.Card;
import abopijservice.code.bankcardmanagement.card.StatusCard;
import org.springframework.data.jpa.domain.Specification;

public class CardSpecs {
    public static Specification<Card> equalsToStatus(StatusCard status) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("status"), status));
    }

    public static Specification<Card> equalsToNumber(String number) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.like(root.get("number"), "%" + number + "%"));
    }

    public static Specification<Card> moneyGreaterThenOrEq(double money) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("money"), money));
    }

    public static Specification<Card> moneyLessThen(double money) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.lessThan(root.get("money"), money));
    }
}
