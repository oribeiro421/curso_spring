package com.algaworks.algafoods.domain.listener;

import com.algaworks.algafoods.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafoods.domain.model.Pedido;
import com.algaworks.algafoods.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService emailService;

    @TransactionalEventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event){
        Pedido pedido = event.getPedido();

        var mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        emailService.enviar(mensagem);
    }
}
