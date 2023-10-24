package com.live.debate.round.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DebateRound {

    @Id
    @GeneratedValue
    Long id;

    String candidateA;
    String candidateB;

    String description;

    boolean complete;
}
