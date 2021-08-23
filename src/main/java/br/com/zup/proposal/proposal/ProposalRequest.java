package br.com.zup.proposal.proposal;

import br.com.zup.proposal.config.validators.Document;
import br.com.zup.proposal.proposal.analisysRequester.Requester;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProposalRequest {

    private @Email @NotBlank String email;
    private @NotBlank String name;
    private @NotBlank String address;
    private @Positive BigDecimal salary;
    private @NotBlank @Document
    String document;

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

    public Proposal toModel(){
        return new Proposal(email, name, address, salary, document);
    }

    public Requester toRequest(Long id){
        return new Requester(this.document,this.name, id.toString());
    }
}
