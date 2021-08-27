package br.com.zup.proposal.card.block;

import javax.validation.constraints.NotBlank;

public class BlockRequest {
    @NotBlank
    private String sistemaResponsavel;

    public BlockRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
