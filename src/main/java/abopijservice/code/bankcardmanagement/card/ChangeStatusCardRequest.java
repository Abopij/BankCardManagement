package abopijservice.code.bankcardmanagement.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusCardRequest {
    private UUID cardID;
    private StatusCard statusCard;
}
