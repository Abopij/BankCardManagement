package abopijservice.code.bankcardmanagement.card.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class MoneyTransferRequest {
    private UUID idSenderCard;
    private UUID idRecipientCard;
    private Double money;
}
