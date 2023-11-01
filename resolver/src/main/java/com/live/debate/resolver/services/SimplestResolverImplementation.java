package com.live.debate.resolver.services;

import com.live.debate.resolver.dtos.CompletedRoundDTO;
import com.live.debate.resolver.model.ResolvedRound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service("simplest")
@RequiredArgsConstructor
public class SimplestResolverImplementation implements ResolverService{

    @Override
    public ResolvedRound resolveRound(CompletedRoundDTO completedRound) {
        Random random = new Random();
        double modifiedDieRoll = random.nextDouble();

        log.info("Round resolved courtesy of the Simplest Resolver");

        return resolveRound(completedRound, modifiedDieRoll);
    }

    public ResolvedRound resolveRound(CompletedRoundDTO completedRound, double modifiedDieRoll) {

        //TODO: REMOVE hardcoding of id from Resolved Round
        ResolvedRound round = new ResolvedRound(
                1L,
                completedRound.getId(),
                completedRound.getCandidateA(),
                completedRound.getCandidateB(),
                completedRound.getDescription(),
                "unresolved"
        );


        if(modifiedDieRoll < 0.5){
            round.outcome = completedRound.getCandidateA() + " won";
        }
        else {
            round.outcome = completedRound.getCandidateB() + " won";
        }
        return round;
    }


}
