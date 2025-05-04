package abopijservice.code.bankcardmanagement.card.transfer;

import abopijservice.code.bankcardmanagement.card.Card;
import abopijservice.code.bankcardmanagement.card.CardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoneyTransferService {

    private final CardRepo cardRepo;

    public boolean transferMoney(UUID senderId, UUID recipientId, double amount) {
        Card sender = cardRepo.findById(senderId).orElseThrow();
        Card recipient = cardRepo.findById(recipientId).orElseThrow();

        if (sender.getUser().equals(recipient.getUser())) {
            if ((sender.getMoney() - amount) < 0.0) {
                return false;
            }
            if (cardRepo.existsById(sender.getId()) || cardRepo.existsById(recipient.getId())) {
                return false;
            }
            sender.setMoney(sender.getMoney() - amount);
            recipient.setMoney((recipient.getMoney() + amount));

            cardRepo.saveAndFlush(sender);
            cardRepo.saveAndFlush(recipient);
            return true;
        }

        return false;
    }
}
