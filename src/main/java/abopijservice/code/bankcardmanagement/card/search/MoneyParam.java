package abopijservice.code.bankcardmanagement.card.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyParam {
    private double money;
    private boolean isGreater;
}