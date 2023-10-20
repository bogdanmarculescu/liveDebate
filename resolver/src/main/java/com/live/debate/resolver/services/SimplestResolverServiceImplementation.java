package com.live.debate.resolver.services;

import com.live.debate.resolver.dtos.CompletedRoundDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SimplestResolverServiceImplementation implements ResolverService{
    @Override
    public String resolveRound(CompletedRoundDTO completedRound) {
        //return "Service basic test";
        return resolveRoundRandom(completedRound);
    }

    public String resolveRound(CompletedRoundDTO completedRound, double dieRoll){
        if(dieRoll < 0.5){
            return completedRound.getCandidateA() + " won";
        }
        else return completedRound.getCandidateB() + " won";
    }

    public String resolveRoundRandom(CompletedRoundDTO completedRound){
        Random rand = new Random();
        double number = rand.nextDouble();

        return resolveRound(completedRound, number);
    }
}
