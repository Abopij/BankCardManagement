package abopijservice.code.bankcardmanagement.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {

    private LocalDate validityDate;

    private UUID userID;
}
