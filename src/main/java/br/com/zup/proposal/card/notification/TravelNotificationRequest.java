package br.com.zup.proposal.card.notification;

import br.com.zup.proposal.card.Card;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TravelNotificationRequest {
    @NotBlank
    private String destiny;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate endTravel;

    @JsonCreator
    public TravelNotificationRequest(@JsonProperty("destiny") String destiny) {
        this.destiny = destiny;
    }

    public void setTravelEndTime(LocalDate endTravel) {
        this.endTravel = endTravel;
    }

    public LocalDate getEndTravel() {
        return endTravel;
    }

    public String getDestiny() {
        return destiny;
    }

    public TravelNotification toTravel(Card card, String ipClient, String userAgent) {
        return new TravelNotification(card, destiny, endTravel, ipClient, userAgent);
    }
}
