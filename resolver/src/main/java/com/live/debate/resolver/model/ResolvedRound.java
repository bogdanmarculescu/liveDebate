package com.live.debate.resolver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResolvedRound {

    @Id
    @Generated
    Long id;

    Long sourceId;

    String candidateA;
    String candidateB;

    String description;

    public String outcome;
}
