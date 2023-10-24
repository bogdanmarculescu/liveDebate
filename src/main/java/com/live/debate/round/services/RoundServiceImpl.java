package com.live.debate.round.services;

import com.live.debate.round.messaging.RoundEventPublisher;
import com.live.debate.round.model.DebateRound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoundServiceImpl implements RoundService{

    private final RoundEventPublisher roundEventPublisher;
    @Override
    public String test(){
        //TODO: - when this is called, drop a message to the message queue
        DebateRound sendThis = new DebateRound(
                1L,
                "candidateA",
                "candidateB",
                "described here",
                true
        );

        roundEventPublisher.roundComplete(sendThis);

        return "Testing";
    }
}
