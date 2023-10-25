package com.live.debate.ongoing.messaging;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Value
public class CompletedRoundEvent {

    Long id;

    String description;

    String candidateA;
    String candidateB;

    boolean duel;
    boolean complete;
}
