package com.live.debate.resolver.events;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class RoundCompletedEvent {
    long roundId;
    String candidateA;
    String candidateB;
    String description;
}