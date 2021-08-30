package br.com.zup.proposal.card.wallet;

import br.com.zup.proposal.card.Card;

import javax.persistence.*;
import java.util.Objects;

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

    public boolean returnWallet(TypeWallet typeWallet){
        return this.wallet.equals(typeWallet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalWallet that = (DigitalWallet) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(wallet, that.wallet) && Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, wallet, card);
    }
}
