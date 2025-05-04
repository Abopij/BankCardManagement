package abopijservice.code.bankcardmanagement.card;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private UUID id;

    private StatusCard status;

    private String number;

    private LocalDate validityDate;

    private double money;
}
