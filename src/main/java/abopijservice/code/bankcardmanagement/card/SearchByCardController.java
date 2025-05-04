package abopijservice.code.bankcardmanagement.card;

import abopijservice.code.bankcardmanagement.user.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/cards/search")
@RequiredArgsConstructor
@Api(value = "/api/v1")
public class SearchByCardController {

    private final CardService cardService;

    @ApiOperation(value = "Search cards by different param (required false)")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<CardDTO> search(
            @RequestParam(required = false) StatusCard status,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) MoneyParam moneyParam,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer count
    ) {
        if (status != null) {
            return cardService.getCardsDTOByStatus(status, page, count);
        }
        if (number != null) {
            return cardService.getCardsDTOByNumber(number, page, count);
        }
        if (moneyParam != null) {
            return cardService.getCardsDTOByMoneyParam(moneyParam, page, count);
        }
        return new PageImpl<>(cardService.getAllCardsDTO());
    }
}
