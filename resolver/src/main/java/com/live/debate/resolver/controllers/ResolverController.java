package com.live.debate.resolver.controllers;


import com.live.debate.resolver.dtos.CompletedRoundDTO;
import com.live.debate.resolver.model.ResolvedRound;
import com.live.debate.resolver.services.SimplestResolverServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resolve")
@RequiredArgsConstructor
public class ResolverController {

    private final SimplestResolverServiceImplementation simplest;

    // POST Completed round -> resolved

    @PostMapping
    ResolvedRound resolveRound(
            @RequestBody CompletedRoundDTO completedRound){
        ResolvedRound result = simplest.resolveRound(completedRound);
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
