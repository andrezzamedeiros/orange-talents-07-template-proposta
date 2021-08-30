package br.com.zup.proposal.card.wallet;

import br.com.zup.proposal.card.Card;

import javax.persistence.*;

@Entity
public class DigitalWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String wallet;

    @ManyToOne
    private Card card;

    @Deprecated
    public DigitalWallet() {
    }

    public DigitalWallet(String email, String wallet, Card card) {
        this.email = email;
        this.wallet = wallet;
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
