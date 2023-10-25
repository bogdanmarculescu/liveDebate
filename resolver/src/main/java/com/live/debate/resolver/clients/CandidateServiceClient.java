package com.live.debate.resolver.clients;

import com.live.debate.resolver.dtos.CandidateDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CandidateServiceClient {

    private final RestTemplate restTemplate;
    private final String candidateServiceUrl;

    public CandidateServiceClient(
            RestTemplateBuilder builder,
            @Value("http://localhost:8081") final String candidateServiceUrl
    ){
        this.restTemplate = builder.build();
        this.candidateServiceUrl=candidateServiceUrl;
    }

    public CandidateDTO getCandidate(Long id){
        //TODO: redo the candidate call with parameters other than url

        String callUrl = candidateServiceUrl + "/candidates" + "?id=" + id;

        ResponseEntity<CandidateDTO> result = restTemplate.getForEntity(callUrl, CandidateDTO.class);

        return result.getBody();
    }


}
