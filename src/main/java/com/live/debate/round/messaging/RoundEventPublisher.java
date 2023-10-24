package com.live.debate.round.messaging;

import com.live.debate.round.model.DebateRound;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RoundEventPublisher {

    public final AmqpTemplate amqpTemplate;

    private final String roundTopicExchange;

    public RoundEventPublisher(final AmqpTemplate amqpTemplate,
        @Value("${amqp.exchange.rounds}") final String roundTopicExchange
    ){
        this.amqpTemplate = amqpTemplate;
        this.roundTopicExchange = roundTopicExchange;
    }

    public void roundComplete(final DebateRound round){
        // build message
        RoundCompletedEvent event = buildEvent(round);
        // sort out the rounting key
        String routingKey = "round." + (round.isComplete() ?
                "complete" : "incomplete");
        //send message
        amqpTemplate.convertAndSend(roundTopicExchange,
                routingKey,
                event);
    }

    private RoundCompletedEvent buildEvent(final DebateRound round) {
        return new RoundCompletedEvent(
          round.getId(),
          round.getCandidateA(),
          round.getCandidateB(),
          round.getDescription()
        );
    }

}
