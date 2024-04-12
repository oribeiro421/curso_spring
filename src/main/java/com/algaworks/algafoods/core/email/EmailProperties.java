package com.algaworks.algafoods.core.email;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    private Implementacao impl = Implementacao.FAKE;

    private Sandbox sandbox = new Sandbox();

    @NotNull
    private String remetente;

    public enum Implementacao{
        SMTP, FAKE, SANDBOX
    }

    @Getter
    @Setter
    public class Sandbox{
        private String destinatario;
    }
}
