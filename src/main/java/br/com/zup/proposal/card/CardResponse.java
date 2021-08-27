package br.com.zup.proposal.card;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CardResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<String> bloqueios;
    private List<String>avisos;
    private List<String>carteiras;
    private List<Integer>parcelas;
    private BigDecimal limite;
    private String renegociacao;
    private VencimentoResponse vencimento;
    private String idProposta;


    public CardResponse(String id) {
        this.id = id;
    }

    public CardResponse(String id, LocalDateTime emitidoEm, String titular, List<String> bloqueios, List<String> avisos, List<String> carteiras,
                        List<Integer> parcelas, BigDecimal limite, String renegociacao, VencimentoResponse vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<String> getBloqueios() {
        return bloqueios;
    }

    public List<String> getAvisos() {
        return avisos;
    }

    public List<String> getCarteiras() {
        return carteiras;
    }

    public List<Integer> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public String getRenegociacao() {
        return renegociacao;
    }

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public Card toCard() {
        return new Card(this.id);
    }
}
