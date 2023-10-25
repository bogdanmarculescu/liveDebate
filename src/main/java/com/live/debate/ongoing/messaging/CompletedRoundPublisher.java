package com.live.debate.ongoing.messaging;

import com.live.debate.ongoing.model.Round;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompletedRoundPublisher {

    private final AmqpTemplate amqpTemplate;

    private final String roundExchange;

    public CompletedRoundPublisher(
            AmqpTemplate template,
            @Value("${amqp.exchange.round}") final String roundExchange
    ){
        this.amqpTemplate = template;
        this.roundExchange = roundExchange;
    }

    public void sendMessage(Round roundToBeSent){
        CompletedRoundEvent event = buildEvent(roundToBeSent);

        // exchange, rounting key, object

        // complete = all candidates have picked something; incomplete = some missing actions
        // duel - 2 player round; multi - multiplayer round
        // round.complete.duel

        String rountingKey = "round." +
                (event.isComplete() ? "complete" : "incomplete")
                + "." + (event.isDuel() ? "duel" : "multi");

        amqpTemplate.convertAndSend(
                roundExchange,
                rountingKey,
                event
        );
    }

    private CompletedRoundEvent buildEvent(Round roundToBeSent) {
        // property check

        CompletedRoundEvent c = new CompletedRoundEvent(
                roundToBeSent.getId(),
                roundToBeSent.getDescription(),
                roundToBeSent.getCandidates().get(1L),
                roundToBeSent.getCandidates().get(2L),
                roundToBeSent.isDuel(),
                roundToBeSent.isComplete()
        );
        return c;
    }

}
