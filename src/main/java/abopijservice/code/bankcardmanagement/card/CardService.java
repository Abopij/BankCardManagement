package abopijservice.code.bankcardmanagement.card;

import abopijservice.code.bankcardmanagement.card.search.CardSpecs;
import abopijservice.code.bankcardmanagement.card.search.MoneyParam;
import abopijservice.code.bankcardmanagement.user.User;
import abopijservice.code.bankcardmanagement.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepo cardRepo;
    private final UserService userService;

    public CardDTO saveCard(CardRequest cardRequest) {
        User user;
        if (cardRequest.getUserID() != null && SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority("ADMIN"))
        ) {
            user = userService.getUserByID(cardRequest.getUserID());

            if (cardRequest.getValidityDate() == null) {
                cardRequest.setValidityDate(
                        LocalDate.now().plusYears(1)
                );
            }
        } else {
            user = userService.getUserByUsername(
                    SecurityContextHolder.getContext().getAuthentication().getName()
            );

            cardRequest.setValidityDate(
                    LocalDate.now().plusYears(1)
            );
        }

        return save(cardRequest, user);
    }

    private CardDTO save(CardRequest cardRequest, User user) {
        Card card = new Card(
                StatusCard.ACTIVE,
                user,
                CreateVisaNumberService.create(),
                cardRequest.getValidityDate(),
                0
        );

        return convertToDTO(cardRepo.save(card));
    }

    public boolean deleteCard(UUID id) {
        if (id == null) return false;

        if (SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority("ADMIN"))
                ||
                cardRepo
                        .getReferenceById(id)
                        .getUser().getUsername()
                        .equals(SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                        )
        ) {
            delete(id);
            return true;
        }

        return false;
    }


    private void delete(UUID cardId) {
        cardRepo.deleteById(cardId);
    }

    private Card update(Card card) {
        return cardRepo.saveAndFlush(card);
    }

    public List<CardDTO> getAllCardsDTO() {
        return cardRepo.findAll().stream().map(this::convertToDTO).toList();
    }

    public List<CardDTO> getAllCardsDTOByUser(UUID userID) {
        if (
                userID != null &&
                        SecurityContextHolder.getContext()
                                .getAuthentication()
                                .getAuthorities()
                                .contains(new SimpleGrantedAuthority("ADMIN"))
        ) {
            return cardRepo.getAllByUserId(userID).stream().map(this::convertToDTO).toList();
        }

        return cardRepo.getAllByUserUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).stream().map(this::convertToDTO).toList();
    }


    public CardDTO changeStatusCard(UUID cardId, StatusCard statusCard) {
        Card card = cardRepo.getReferenceById(cardId);

        if (SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .contains(new SimpleGrantedAuthority("ADMIN"))
        ) {
            card.setStatus(statusCard);
            return convertToDTO(update(card));
        }

        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user.getCard().contains(card)) {
            card.setStatus(statusCard);
            return convertToDTO(update(card));
        }

        return null;
    }

    public Page<CardDTO> getCardsDTOByStatus(StatusCard statusCard, int numberPage, int countMaxInPage) {
        Specification<Card> filter = Specification.where(null);
        filter = filter.and(CardSpecs.equalsToStatus(statusCard));

        return cardRepo.findAll(filter, PageRequest.of(numberPage, countMaxInPage)).map(this::convertToDTO);
    }

    public Page<CardDTO> getCardsDTOByNumber(String number, int numberPage, int countMaxInPage) {
        Specification<Card> filter = Specification.where(null);
        filter = filter.and(CardSpecs.equalsToNumber(number));

        return cardRepo.findAll(filter, PageRequest.of(numberPage, countMaxInPage)).map(this::convertToDTO);
    }

    public Page<CardDTO> getCardsDTOByMoneyParam(MoneyParam moneyParam, int numberPage, int countMaxInPage) {
        Specification<Card> filter = Specification.where(null);
        if (moneyParam.isGreater()) {
            filter = filter.and(CardSpecs.moneyGreaterThenOrEq(moneyParam.getMoney()));
        } else {
            filter = filter.and(CardSpecs.moneyLessThen(moneyParam.getMoney()));
        }

        return cardRepo.findAll(filter, PageRequest.of(numberPage, countMaxInPage)).map(this::convertToDTO);
    }

    private CardDTO convertToDTO(Card card) {
        return new CardDTO(
                card.getId(),
                card.getStatus(),
                card.getNumber(),
                card.getValidityDate(),
                card.getMoney()
        );
    }

}
