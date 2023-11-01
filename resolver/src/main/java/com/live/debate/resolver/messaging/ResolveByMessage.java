package com.live.debate.resolver.messaging;

import com.live.debate.resolver.dtos.CompletedRoundDTO;
import com.live.debate.resolver.model.CompletedRound;
import com.live.debate.resolver.model.ResolvedRound;
import com.live.debate.resolver.services.ResolverService;
import com.live.debate.resolver.services.SemiRandomResolverServiceImplementation;
import com.live.debate.resolver.services.SimplestResolverImplementation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResolveByMessage {

    @Qualifier("randomish")
    private final SemiRandomResolverServiceImplementation resolverService;
    @Qualifier("simplest")
    private final SimplestResolverImplementation simplest;


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

    @RabbitListener(queues = "${amqp.queue.round}")
    public void resolveRound(CompletedRoundDTO completedRound){
        Random random = new Random();
        double dieRoll = random.nextDouble();
        ResolvedRound result = null;

        if(dieRoll < 0.5){
            result = simplest.resolveRound(completedRound);
        }
        else {
            result = resolverService.resolveRound(completedRound);
        }

        log.warn("You shouldn't be doing this, but:\n " +
                "Die roll: " + dieRoll +
                "\nResult: " + result);
    }

}
