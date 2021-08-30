package br.com.zup.proposal.card.wallet;

import br.com.zup.proposal.card.Card;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class DigitalWalletRequest {
    @Email
    @NotBlank
    private String email;

    private String wallet;

    public DigitalWalletRequest(String email, String wallet) {
        this.email = email;
        this.wallet = wallet;
    }

    public DigitalWallet toDigitalWallet(Card card) {
        return new DigitalWallet(email, wallet, card);
    }

    public String getEmail() {
        return email;
    }

    public String getWallet() {
        return wallet;
    }
}
