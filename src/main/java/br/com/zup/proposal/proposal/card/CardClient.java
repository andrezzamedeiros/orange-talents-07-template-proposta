package br.com.zup.proposal.proposal.card;

import br.com.zup.proposal.proposal.analisysRequester.Requester;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "card-resource", url = "http://localhost:8888")
public interface CardClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes", consumes = "application/json")
    CardResponse associateCard(CardRequest request);
}
