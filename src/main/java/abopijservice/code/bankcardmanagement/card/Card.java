package abopijservice.code.bankcardmanagement.card;

import abopijservice.code.bankcardmanagement.card.masked.EncryptStringConverter;
import abopijservice.code.bankcardmanagement.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusCard status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "number")
    @Convert(converter = EncryptStringConverter.class)
    private String number;

    @Column(name = "validityDate")
    private LocalDate validityDate;

    @Column(name = "money")
    private double money;

    public Card(StatusCard status, User user, String number, LocalDate validityDate, double money) {
        this.status = status;
        this.user = user;
        this.number = number;
        this.validityDate = validityDate;
        this.money = money;
    }
}