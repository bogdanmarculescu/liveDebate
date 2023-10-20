package com.live.debate.resolver.services;

public interface ResolverService {

    // just turn a completed round into a resolved round
    String resolveRound(String completedRound);
}
