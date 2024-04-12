package com.algaworks.algafoods.domain.listener;

import com.algaworks.algafoods.domain.event.PedidoCanceladoEvent;
import com.algaworks.algafoods.domain.model.Pedido;
import com.algaworks.algafoods.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoCanceladoListener {

    @Autowired
    private EnvioEmailService emailService;

    @TransactionalEventListener
    public void aoCancelarPedido(PedidoCanceladoEvent event){
        Pedido pedido = event.getPedido();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido cancelado")
                .corpo("pedido-cancelado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        emailService.enviar(mensagem);
    }
}
