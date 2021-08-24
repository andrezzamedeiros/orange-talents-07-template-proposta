package br.com.zup.proposal.proposal.card;

import br.com.zup.proposal.proposal.Proposal;
import br.com.zup.proposal.proposal.ProposalRepository;
import br.com.zup.proposal.proposal.ProposalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @EnableScheduling
public class CardResource {

    @Autowired
    private final ProposalRepository repository;
    @Autowired
    private final CardClient cardClient;

    public CardResource(ProposalRepository repository, CardClient cardClient) {
        this.repository = repository;
        this.cardClient = cardClient;
    }

    @Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
    public void AssociateCard() {

        List<Proposal> avaliablesCard = repository.findAllByCardIdIsNullProposalStatusEquals(ProposalStatus.ELEGIVEL);
        if (!avaliablesCard.isEmpty()) {
            avaliablesCard.forEach(proposal -> {
                CardRequest
                        request =
                        new CardRequest(proposal.getDocument(), proposal.getName(), proposal.getId().toString());
                CardResponse associatedCard = cardClient.associateCard(request);
                Card card = associatedCard.toCard();
                proposal.setCard(card);
            });
            repository.saveAll(avaliablesCard);
        }
    }
}

