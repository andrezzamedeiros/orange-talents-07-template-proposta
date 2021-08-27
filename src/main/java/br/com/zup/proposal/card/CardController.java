package br.com.zup.proposal.card;

import br.com.zup.proposal.card.biometry.Biometry;
import br.com.zup.proposal.card.biometry.BiometryRequest;
import br.com.zup.proposal.card.block.Block;
import br.com.zup.proposal.card.block.BlockRequest;
import br.com.zup.proposal.card.notification.TravelNotification;
import br.com.zup.proposal.card.notification.TravelNotificationRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private final CardRepository repository;
    @PersistenceContext
    EntityManager manager;
    @Autowired
    CardClient cardClient;

    public CardController(CardRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/{cardId}")
    @Transactional
    public ResponseEntity<?> postBiometries(
            @PathVariable Long cardId,
            @RequestBody @Valid BiometryRequest biometryRequest,
            UriComponentsBuilder builder) {

        if (!biometryRequest.isBase64(biometryRequest.getBiometry())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de biometria inválido, a biometria deve ser enviada em base64!");
        }

        Optional<Card> hasCard = repository.findById(cardId);
        if (hasCard.isPresent()) {
            Card card = hasCard.get();
            Biometry biometry = biometryRequest.toBiometry();
            card.addBiometry(biometry);
            repository.save(card);

            URI uri = builder.path("/{id}").build(biometry.getId());
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{cardId}/bloqueio")
    @Transactional
    public ResponseEntity<?> blockCard(@PathVariable Long cardId, HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String ipClient = request.getRemoteAddr();

        Optional<Card> optionalCard = repository.findById(cardId);
        Card card = optionalCard.get();
        if (optionalCard.isPresent()) {
            try {
                cardClient.blockCard(card.getCardNumber(), new BlockRequest("propostas"));
                Block block = new Block(ipClient, userAgent);
                manager.persist(block);
                card.setBlock(block);
                card.setStatus(CardStatus.BLOCKED);
                repository.save(card);
                return ResponseEntity.ok().build();
            } catch (FeignException e) {
                if (e.status() == 400) {
                    return ResponseEntity.badRequest().build();
                }
                if (e.status() == 422) {
                    return ResponseEntity.unprocessableEntity().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{cardId}/notification")
    @Transactional
    public ResponseEntity<?> travelNotice(@PathVariable Long cardId, @RequestBody @Valid TravelNotificationRequest request, HttpServletRequest httpServletRequest) {

        String userAgent = httpServletRequest.getHeader("User-Agent");
        String ipClient = httpServletRequest.getRemoteAddr();

        Optional<Card> optionalCard = repository.findById(cardId);
        Card card = optionalCard.get();
        if (!optionalCard.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            try {
                cardClient.sentNotification(card.getCardNumber(), request);
                TravelNotification travelNotification = request.toTravel(card, ipClient, userAgent);
                card.addTravelNotification(travelNotification);
                repository.save(card);
                return ResponseEntity.ok().build();
            } catch (FeignException e) {
                e.printStackTrace();
                return ResponseEntity.unprocessableEntity().build();
            }
        }
    }
}



