package com.live.debate.resolver.messaging;

import lombok.Value;

@Value
public class CompletedRoundEvent {

    Long id;

    String description;

    String candidateA;
    String candidateB;

    boolean duel;
    boolean complete;
}
