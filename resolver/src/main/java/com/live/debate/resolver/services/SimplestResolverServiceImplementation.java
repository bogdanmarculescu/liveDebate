package com.live.debate.resolver.services;

import com.live.debate.resolver.clients.CandidateServiceClient;
import com.live.debate.resolver.dtos.CandidateDTO;
import com.live.debate.resolver.dtos.CompletedRoundDTO;
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

        CandidateDTO candidateDTO = candidateServiceClient.getCandidate(2L);

        log.info("Got candidate: " + candidateDTO.getName());

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

        //TODO: call Candidates and get candidate info in here.
        // TODO: handle the case of missing/new candidates :)

        CandidateDTO candidateA = candidateServiceClient.getCandidate(completedRound.getCandidateA());
        log.info("Retrieved candidate A: " + candidateA.toString());
        CandidateDTO candidateB = candidateServiceClient.getCandidate(completedRound.getCandidateB());
        log.info("Retrieved candidate B: " + candidateB.toString());

        return resolveRound(completedRound, number);
    }
}
