package br.com.zup.proposal.proposal.card.biometry;

import br.com.zup.proposal.config.validators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class BiometryRequest {

    @NotBlank
    @JsonProperty
    @UniqueValue(fieldName = "biometry", domainClass = Biometry.class)
    private String biometry;

    @JsonCreator
    public BiometryRequest ( @JsonProperty ( value = "biometry" ) @NotBlank String biometry ) {
        this.biometry = biometry;
    }

    public String getBiometry() {
        return biometry;
    }

    public Biometry toBiometry () {
        return new Biometry(this.biometry);
    }

    public boolean isBase64(String biometry) {
        java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();

        try {
            decoder.decode(String.valueOf(biometry));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
