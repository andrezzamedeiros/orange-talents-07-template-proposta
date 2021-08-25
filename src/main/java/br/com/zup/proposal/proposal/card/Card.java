package br.com.zup.proposal.proposal.card;

import br.com.zup.proposal.proposal.card.biometry.Biometry;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Card {

    @Id
    private String id;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Biometry> biometries = new ArrayList<>();

    @Deprecated
    public Card() {
    }

    public Card(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addBiometry(Biometry biometry) {
        biometry.setCard(this);
        biometries.add(biometry);
    }
}

