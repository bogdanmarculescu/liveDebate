package com.live.debate.resolver.services;

import com.live.debate.resolver.dtos.CompletedRoundDTO;
import com.live.debate.resolver.model.ResolvedRound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SimplestResolverServiceImplementation implements ResolverService{
    @Override
    public ResolvedRound resolveRound(CompletedRoundDTO completedRound) {
        //return "Service basic test";
        return resolveRoundRandom(completedRound);
    }

    public ResolvedRound resolveRound(CompletedRoundDTO completedRound, double dieRoll){
        ResolvedRound round = new ResolvedRound(
                1L,
                completedRound.getId(),
                completedRound.getCandidateA(),
                completedRound.getCandidateB(),
                completedRound.getDescription(),
                "unresolved"
        );


        if(dieRoll < 0.5){
            round.outcome = completedRound.getCandidateA() + " won";
        }
        else {
            round.outcome = completedRound.getCandidateB() + " won";
        }
        return round;
    }

    public ResolvedRound resolveRoundRandom(CompletedRoundDTO completedRound){
        Random rand = new Random();
        double number = rand.nextDouble();

        return resolveRound(completedRound, number);
    }
}
