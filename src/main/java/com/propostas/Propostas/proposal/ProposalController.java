package com.propostas.Propostas.proposal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class ProposalController {

    @Autowired
    ProposalRepository proposalRepository;

    @PostMapping("/proposal")
    public ResponseEntity<?> createProposal(@RequestBody @Valid ProposalRequest request, UriComponentsBuilder builder){

        Proposal proposal = request.toModel();
        if(!proposalRepository.findByDocument(proposal.getDocument()).isPresent()){
            proposalRepository.save(proposal);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }


        URI uri = builder.path("/proposal/{id}").buildAndExpand(proposal.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
