package com.live.debate.resolver.controllers;


import com.live.debate.resolver.dtos.CompletedRoundDTO;
import com.live.debate.resolver.model.ResolvedRound;
import com.live.debate.resolver.services.ResolverService;
import com.live.debate.resolver.services.SemiRandomResolverServiceImplementation;
import com.live.debate.resolver.services.SimplestResolverImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/resolve")
@RequiredArgsConstructor
public class ResolverController {

    @Qualifier("simplest")
    private final SimplestResolverImplementation simplest;
    @Qualifier("randomish")
    private final SemiRandomResolverServiceImplementation randomish;

    // POST Completed round -> resolved

    @PostMapping
    ResolvedRound resolveRound(
            @RequestBody CompletedRoundDTO completedRound){

        Random random = new Random();
        double dieRoll = random.nextDouble();
        ResolvedRound result = null;

        if(dieRoll < 0.5){
            result = simplest.resolveRound(completedRound);
        }
        else {
            result = randomish.resolveRound(completedRound);
        }

        return result;
    }
    // GET for testing

    @GetMapping
    ResolvedRound justTestin(){
        //return "Yup, it's a test";
        CompletedRoundDTO testInput = new CompletedRoundDTO(
                1L,
                "Simplest Test round",
                "Andronikos",
                "Alexios"
        );

        ResolvedRound result = simplest.resolveRound(testInput);

        return result;
    }
}
