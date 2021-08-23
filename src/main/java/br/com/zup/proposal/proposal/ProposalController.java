package br.com.zup.proposal.proposal;


import br.com.zup.proposal.proposal.analisysRequester.RequesterClient;
import br.com.zup.proposal.proposal.analisysRequester.RequesterResource;
import br.com.zup.proposal.proposal.analisysRequester.RequesterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ProposalController {

    @Autowired
    ProposalRepository proposalRepository;
    @Autowired
    RequesterResource requesterResource;

    @PostMapping("/proposal")
    public ResponseEntity<?> createProposal(@RequestBody @Valid ProposalRequest proposalRequest, UriComponentsBuilder builder) throws Exception {

        Proposal proposal = proposalRequest.toModel();
        if(!proposalRepository.findByDocument(proposal.getDocument()).isPresent()){
            proposalRepository.save(proposal);
            RequesterResponse requesterResponse = requesterResource.checkRequest(proposal);
            proposal.setProposalStatus(requesterResponse.getResultadoSolicitacao());
            proposalRepository.save(proposal);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        URI uri = builder.path("/proposal/{id}").buildAndExpand(proposal.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/proposal")
    public ResponseEntity<?> getAll(){
        List<Proposal> proposals = proposalRepository.findAll();
        return ResponseEntity.ok(proposals);
    }
}
