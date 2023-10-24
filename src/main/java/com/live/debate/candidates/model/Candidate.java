package com.live.debate.candidates.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@NoArgsConstructor
@Getter
public class Candidate {

    @Id
    @GeneratedValue
    Long id;

    String name;

    String description;

    private String privateInfo;
}
