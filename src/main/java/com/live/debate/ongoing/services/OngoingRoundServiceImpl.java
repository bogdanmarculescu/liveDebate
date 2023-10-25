package com.live.debate.ongoing.services;

import com.live.debate.ongoing.messaging.CompletedRoundEvent;
import com.live.debate.ongoing.messaging.CompletedRoundPublisher;
import com.live.debate.ongoing.model.Round;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OngoingRoundServiceImpl implements OngoingRoundService{

    // Some external component to handle message sending
    public final CompletedRoundPublisher completedRoundPublisher;

    @Override
    public String test() {
        return "Service Test";
    }

    @Override
    public String completeRound() {

        // TODO: CompletedRoundEvent is created

        Round round = new Round();

        // TODO: get the message sent!

        completedRoundPublisher.sendMessage(round);

        return "Nothing so far";
    }

    @Override
    public String completeRound(Round round) {
        completedRoundPublisher.sendMessage(round);
        return "Round Sent: " + round.getId();
    }
}
