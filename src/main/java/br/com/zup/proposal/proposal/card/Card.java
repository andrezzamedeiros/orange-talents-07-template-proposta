package br.com.zup.proposal.proposal.card;

import br.com.zup.proposal.proposal.card.biometry.Biometry;
import br.com.zup.proposal.proposal.card.block.Block;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Biometry> biometries = new ArrayList<>();

    private CardStatus status;
    
    @OneToOne
    private Block block;

    @Deprecated
    public Card() {
    }

    public Card(String id) {
        this.id = id;
        this.status = CardStatus.ACTIVE;
    }

    public String getId() {
        return id;
    }

    public CardStatus getStatus() {
        return status;
    }

    public Block getBlock() {
        return block;
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

