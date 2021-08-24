package br.com.zup.proposal.proposal;

import br.com.zup.proposal.config.validators.Document;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProposalResponse {

    private String email;
    private String name;
    private String address;
    private BigDecimal salary;
    private String document;
    private ProposalStatus status;

    public ProposalResponse(Proposal proposal) {
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.address = proposal.getAddress();
        this.salary = proposal.getSalary();
        this.document = proposal.getAddress();
        this.status = proposal.getProposalStatus();
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

    public ProposalStatus getStatus() {
        return status;
    }
}
