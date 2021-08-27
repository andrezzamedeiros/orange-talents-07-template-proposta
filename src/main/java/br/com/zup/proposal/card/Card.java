package br.com.zup.proposal.card;

import br.com.zup.proposal.card.biometry.Biometry;
import br.com.zup.proposal.card.block.Block;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Biometry> biometries = new ArrayList<>();

    private CardStatus status;

    @OneToOne
    private Block block;

    @Deprecated
    public Card() {
    }

    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
        this.status = CardStatus.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public CardStatus getStatus() {
        return status;
    }

    public Block getBlock() {
        return block;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void addBiometry(Biometry biometry) {
        biometry.setCard(this);
        biometries.add(biometry);
    }

    public void setBlock(@Nullable Block block) {
        this.block = block;
    }
    public void status() {
        this.status = CardStatus.ACTIVE;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }
}
