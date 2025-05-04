package abopijservice.code.bankcardmanagement.card;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public List<CardDTO> getAllCardsByUser(@RequestParam(required = false) UUID userID) {
        return cardService.getAllCardsDTOByUser(userID);
    }

    @PutMapping("/status")
    public ResponseEntity<?> changeStatusCard(@RequestParam UUID cardID, @RequestParam StatusCard statusCard) {
        CardDTO cardDTO = cardService.changeStatusCard(cardID, statusCard);
        return cardDTO != null ? ResponseEntity.ok(cardDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public CardDTO saveCard(@RequestParam CardRequest cardRequest, @RequestParam(required = false) UUID userID) {
        return cardService.saveCard(cardRequest, userID);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteCard(@RequestParam UUID id) {
        return cardService.deleteCard(id) ?
                ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
