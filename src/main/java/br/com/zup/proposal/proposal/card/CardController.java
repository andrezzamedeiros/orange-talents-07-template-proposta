package br.com.zup.proposal.proposal.card;

import br.com.zup.proposal.proposal.card.biometry.Biometry;
import br.com.zup.proposal.proposal.card.biometry.BiometryRequest;
import br.com.zup.proposal.proposal.card.block.Block;
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

    public CardController(CardRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/{cardId}")
    @Transactional
    public ResponseEntity<?> postBiometries(
            @PathVariable String cardId,
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
    public ResponseEntity<?> blockCard(@PathVariable @NotNull String cardId, HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String ipClient = request.getRemoteAddr();

        Optional<Card> optionalCard = repository.findById(cardId);
        Card card = optionalCard.get();
        if (optionalCard.isPresent()) {
            if (card.getStatus()!= null && card.getStatus().equals(CardStatus.BLOCKED)) {
                return ResponseEntity.unprocessableEntity().build();
            }
            Block block = new Block(ipClient, userAgent);
            manager.persist(block);
            card.setBlock(block);
            card.setStatus(CardStatus.BLOCKED);
            repository.save(card);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


