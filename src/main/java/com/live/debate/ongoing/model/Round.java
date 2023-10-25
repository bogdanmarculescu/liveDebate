package com.live.debate.ongoing.model;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class Round {

    Long id;
    String description;

    HashMap<Long, String> candidates;

    String secretInfoNotToSend;

    public Round(){
        this.id = 42L;
        this.description = "do not use defaults";
        this.candidates = new HashMap<>();
        candidates.put(1L, "Tancred");
        candidates.put(2L, "Bohemond");
        this.secretInfoNotToSend = "do not send this as a message";
    }

    public boolean isDuel(){
        return candidates.size() == 2;
    }

    public boolean isComplete(){
        return true;
    }
}
