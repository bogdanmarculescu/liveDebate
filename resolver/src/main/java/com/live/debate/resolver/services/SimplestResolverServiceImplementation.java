package com.live.debate.resolver.services;

import com.live.debate.resolver.clients.CandidateServiceClient;
import com.live.debate.resolver.dtos.CandidateDTO;
import com.live.debate.resolver.dtos.CompletedRoundDTO;
import com.live.debate.resolver.model.CompletedRound;
import com.live.debate.resolver.model.ResolvedRound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.index.CandidateComponentsIndex;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimplestResolverServiceImplementation implements ResolverService{

    private final CandidateServiceClient candidateServiceClient;

    @Override
    public ResolvedRound resolveRound(CompletedRoundDTO completedRound) {
        //return "Service basic test";
        CompletedRound round = processRound(completedRound);
        return resolveRoundRandom(round);
    }

    public ResolvedRound resolveRound(CompletedRound completedRound, double dieRoll){
        ResolvedRound round = new ResolvedRound(
                1L,
                completedRound.getId(),
                completedRound.getCandidateA().getName(),
                completedRound.getCandidateB().getName(),
                completedRound.getDescription(),
                "unresolved"
        );

        double modifiedDieRoll = dieRoll
                + completedRound.getCandidateA().getModifier()
                + completedRound.getCandidateB().getModifier();

        if(modifiedDieRoll < 0.5){
            round.outcome = completedRound.getCandidateA().getName() + " won";
        }
        else {
            round.outcome = completedRound.getCandidateB().getName() + " won";
        }
        return round;
    }

    public ResolvedRound resolveRoundRandom(CompletedRound completedRound){
        Random rand = new Random();
        double number = rand.nextDouble()*10;

        return resolveRound(completedRound, number);
    }

    private CompletedRound processRound(CompletedRoundDTO completedRoundDTO){

        //TODO: call Candidates and get candidate info in here.
        // TODO: handle the case of missing/new candidates :)

        CandidateDTO candidateA = candidateServiceClient.getCandidate(completedRoundDTO.getCandidateA());
        log.info("Retrieved candidate A: " + candidateA.getName());
        CandidateDTO candidateB = candidateServiceClient.getCandidate(completedRoundDTO.getCandidateB());
        log.info("Retrieved candidate B: " + candidateB.getName());

        CompletedRound result = new CompletedRound(
                completedRoundDTO.getId(),
                completedRoundDTO.getDescription(),
                candidateA,
                candidateB
        );

        return result;
    }
}
