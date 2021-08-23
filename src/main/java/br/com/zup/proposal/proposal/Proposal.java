package br.com.zup.proposal.proposal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Deprecated
    public Proposal() {
    }

    public Proposal(String email, String name, String address, BigDecimal salary, String document) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.document = document;
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
}
