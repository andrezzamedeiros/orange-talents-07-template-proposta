package br.com.zup.proposal.card.block;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime blockInstant;
    private String clientIp;
    private String UserAgent;

    @Deprecated
    public Block() {
    }

    public Block(String clientIp, String userAgent) {
        this.blockInstant = LocalDateTime.now();
        this.clientIp = clientIp;
        UserAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBlockInstant() {
        return blockInstant;
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getUserAgent() {
        return UserAgent;
    }

}
