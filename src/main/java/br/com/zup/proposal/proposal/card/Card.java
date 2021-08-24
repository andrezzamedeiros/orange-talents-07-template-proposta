package br.com.zup.proposal.proposal.card;

import javax.persistence.*;

@Entity
public class Card {

    @Id
    private String id;
    private String cardNumber;

    public Card() {
    }
    public Card(String id) {
        this.id = id;
    }
}
