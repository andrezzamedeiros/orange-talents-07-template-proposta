package br.com.zup.proposal.card.notification;

import br.com.zup.proposal.card.Card;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TravelNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String destiny;
    @NotNull @Future
    private LocalDate endTravel;
    private LocalDateTime instant;
    private String clientIp;
    private String userAgent;

    @ManyToOne
    private Card card;

    @Deprecated
    public TravelNotification() {
    }

    public TravelNotification(Card card, String destiny, LocalDate endTravel, String ipClient, String userAgent) {
        this.destiny = destiny;
        this.endTravel = endTravel;
        this.instant = LocalDateTime.now();
        this.clientIp = ipClient;
        this.userAgent = userAgent;
        this.card = card;
    }

    public String getDestiny() {
        return destiny;
    }

    public LocalDate getEndTravel() {
        return endTravel;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
