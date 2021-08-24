package br.com.zup.proposal.proposal.analisysRequester;

import br.com.zup.proposal.proposal.Proposal;
import br.com.zup.proposal.proposal.ProposalController;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequesterResource {

    @Autowired
    private final RequesterClient requesterClient;

    public RequesterResource(RequesterClient requesterClient) {
        this.requesterClient = requesterClient;
    }

    private final Logger logger = LoggerFactory.getLogger(ProposalController.class);

    public RequesterResponse checkRequest(Proposal proposal) throws Exception {
        try {
            Requester
                    analysisRequest =
                    new Requester(proposal.getDocument(), proposal.getName(), proposal.getId().toString());
            return requesterClient.checkRequest(analysisRequest).getBody();
        } catch (FeignException.UnprocessableEntity e) {
            return new RequesterResponse(proposal.getDocument(), proposal.getName(), "COM_RESTRICAO", proposal.getId().toString());
        }
    }
}
