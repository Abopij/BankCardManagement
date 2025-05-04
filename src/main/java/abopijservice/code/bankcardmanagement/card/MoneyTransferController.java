package abopijservice.code.bankcardmanagement.card;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/transfer")
@RequiredArgsConstructor
@Api(value = "api/v1/")
public class MoneyTransferController {

    private final MoneyTransferService moneyTransferService;

    @ApiOperation(value = "Transfer money between Clients")
    @PostMapping
    public ResponseEntity<?> moneyTransfer(@RequestBody UUID idSenderCard, @RequestBody UUID idRecipientCard, @NonNull @RequestBody Double money) {
        return moneyTransferService.transferMoney(idSenderCard, idRecipientCard, money)
                ? ResponseEntity.ok("Ok") : ResponseEntity.status(400).build();
    }

}
