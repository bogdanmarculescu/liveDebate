package com.live.debate.resolver.services;

import com.live.debate.resolver.dtos.CompletedRoundDTO;
import com.live.debate.resolver.events.RoundCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class RoundCompleteEventHandler {

    private final ResolverService resolverService;

    @RabbitListener(queues = "${amqp.queue.rounds}")
    void handleCompletedRound(final RoundCompletedEvent roundCompletedEvent){
        log.info("Getting a round to solve: {}", roundCompletedEvent.getRoundId());

        CompletedRoundDTO round = new CompletedRoundDTO(
          roundCompletedEvent.getRoundId(),
          roundCompletedEvent.getCandidateA(),
          roundCompletedEvent.getCandidateB(),
          roundCompletedEvent.getDescription()
        );

        resolverService.resolveRound(round);
    }
}
