package com.live.debate.resolver.messaging;

import com.live.debate.resolver.dtos.CompletedRoundDTO;
import com.live.debate.resolver.model.ResolvedRound;
import com.live.debate.resolver.services.ResolverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResolveByMessage {

    private final ResolverService resolverService;

    @RabbitListener(queues = "${amqp.queue.round}")
    public void resolve(
        final CompletedRoundEvent roundEvent
    ){
        CompletedRoundDTO completedRoundDTO = new CompletedRoundDTO(
                roundEvent.getId(),
                roundEvent.getDescription(),
                roundEvent.getCandidateA(),
                roundEvent.getCandidateB()
        );

        try{
            ResolvedRound result = resolverService.resolveRound(completedRoundDTO);
            log.info("Solved a round! ");
            log.info("Outcome:" + result.outcome);
            log.info("candidates: " + result.getCandidateA() + " vs " + result.getCandidateB());
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Well something went wrong");
        }

        //return "Round Solved (one way or another)";
    }


}
