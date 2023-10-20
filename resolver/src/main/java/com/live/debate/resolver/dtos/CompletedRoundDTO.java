package com.live.debate.resolver.dtos;


import lombok.Value;

@Value
public class CompletedRoundDTO {
    Long id;
    String description;

    String candidateA;
    String candidateB;
}
