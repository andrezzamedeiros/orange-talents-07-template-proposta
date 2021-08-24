package br.com.zup.proposal.proposal;

import br.com.zup.proposal.proposal.card.Card;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Locale;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @Email @NotBlank String email;
    private @NotBlank String name;
    private @NotBlank String address;
    private @Positive BigDecimal salary;
    private @NotBlank String document;
    private ProposalStatus proposalStatus;
    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "card_id", unique = true )
    private Card card;

    @Deprecated
    public Proposal() {
    }

    public Proposal(String email, String name, String address, BigDecimal salary, String document) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.document = document;
    }

    public Proposal(String email, String name, String address, BigDecimal salary, String document, ProposalStatus proposalStatus) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.document = document;
        this.proposalStatus = proposalStatus;
    }

    public Proposal(String email, String name, String address, BigDecimal salary, String document, ProposalStatus proposalStatus, Card card) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.document = document;
        this.proposalStatus = proposalStatus;
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getDocument() {
        return document;
    }

    public Card getCard() {
        return card;
    }

    public ProposalStatus getProposalStatus() {
        return proposalStatus;
    }

    public void setProposalStatus(ProposalStatus proposalStatus) {
        this.proposalStatus = proposalStatus;
    }



    public void setProposalStatus(String resultadoSolicitacao) {
        if (resultadoSolicitacao.equalsIgnoreCase("COM_RESTRICAO ")) {
            this.proposalStatus = ProposalStatus.valueOf("NAO_ELEGIVEL");
        } else {
            this.proposalStatus = ProposalStatus.valueOf("ELEGIVEL");
        }
    }

    public void setCard ( Card card ) {
        this.card = card;
    }
}
