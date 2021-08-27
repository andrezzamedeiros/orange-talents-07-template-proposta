package br.com.zup.proposal.card.biometry;

import br.com.zup.proposal.card.Card;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Biometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String biometry;
    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    private Card card;

    @Deprecated
    public Biometry() {
    }

    public Biometry(String biometry) {
        this.biometry = biometry;
    }

    public Long getId() {
        return id;
    }

    public String getBiometry() {
        return biometry;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}

