package br.com.zup.proposal.card;

import br.com.zup.proposal.card.block.Block;
import br.com.zup.proposal.card.block.BlockRequest;
import br.com.zup.proposal.card.notification.TravelNotificationRequest;
import br.com.zup.proposal.card.wallet.DigitalWalletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "card-resource", url = "http://localhost:8888")
public interface CardClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes", consumes = "application/json")
    CardResponse associateCard(CardRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/bloqueios", consumes = "application/json")
    void blockCard(@PathVariable("id") String id, @RequestBody BlockRequest blockRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/avisos", consumes = "application/json")
    void sentNotification(@PathVariable("id") String id, @RequestBody TravelNotificationRequest travelNotificationRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/carteiras", consumes = "application/json")
    void wallets(@PathVariable String id, @RequestBody DigitalWalletRequest request);

}
