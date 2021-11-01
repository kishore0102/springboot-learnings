package edu.deadshot.springbootwiremock.services;

import edu.deadshot.springbootwiremock.models.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchService {

    @Value("${service.url}")
    private String url;

    @Autowired
    RestTemplate restTemplate;

    public SearchResponse getQuickSearchResults(String key) {
        System.out.println("url = " + url);
        return restTemplate.getForEntity(url + "/getAccounts", SearchResponse.class).getBody();
    }

}
