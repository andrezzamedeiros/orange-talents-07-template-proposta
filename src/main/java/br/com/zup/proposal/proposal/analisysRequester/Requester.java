package br.com.zup.proposal.proposal.analisysRequester;

public class Requester {

    private String documento;
    private String nome;
    private String idProposta;

    public Requester(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
