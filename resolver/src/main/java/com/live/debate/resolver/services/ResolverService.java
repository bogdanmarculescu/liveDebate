package com.live.debate.resolver.services;

import com.live.debate.resolver.dtos.CompletedRoundDTO;
import com.live.debate.resolver.model.ResolvedRound;

public interface ResolverService {

    // just turn a completed round into a resolved round
    ResolvedRound resolveRound(CompletedRoundDTO completedRound);
}
