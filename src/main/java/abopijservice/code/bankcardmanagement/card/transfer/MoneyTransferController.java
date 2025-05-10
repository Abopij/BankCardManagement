package abopijservice.code.bankcardmanagement.card.transfer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
@Api(value = "api/v1/")
public class MoneyTransferController {

    private final MoneyTransferService moneyTransferService;

    @ApiOperation(value = "Transfer money between Cards")
    @PostMapping
    public ResponseEntity<?> moneyTransfer(@RequestBody MoneyTransferRequest moneyTransferRequest) {
        return moneyTransferService.transferMoney(moneyTransferRequest)
                ? ResponseEntity.ok("Ok") : ResponseEntity.status(400).build();
    }

}
