package com.live.debate.ongoing.controllers;


import com.live.debate.ongoing.services.OngoingRoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ongoing")
@RequiredArgsConstructor
public class OngoingRoundController {

    private final OngoingRoundService ongoingRoundService;

    @GetMapping("/test")
    String solveRound(){
        return ongoingRoundService.completeRound();
    }

}
