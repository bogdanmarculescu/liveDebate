package com.live.debate.round.controllers;

import com.live.debate.round.model.DebateRound;
import com.live.debate.round.model.DebateRoundRepository;
import com.live.debate.round.services.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/round")
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;

    @GetMapping
    String justTestin(){
        //DebateRound r = roundRepository.findById(1L).get();
        roundService.test();
        return "Testing";
    }
}
