package com.live.debate.resolver.services;

import com.live.debate.resolver.dtos.CompletedRoundDTO;

public interface ResolverService {

    // just turn a completed round into a resolved round
    String resolveRound(CompletedRoundDTO completedRound);
}
