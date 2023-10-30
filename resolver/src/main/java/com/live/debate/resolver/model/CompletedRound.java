package com.live.debate.resolver.model;

import com.live.debate.resolver.dtos.CandidateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CompletedRound {
    Long id;
    String description;

    CandidateDTO candidateA;

    CandidateDTO candidateB;
}
