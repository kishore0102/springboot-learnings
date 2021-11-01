package edu.deadshot.springbootwiremock.controllers;

import edu.deadshot.springbootwiremock.models.SearchResponse;
import edu.deadshot.springbootwiremock.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/quick/{key}")
    public SearchResponse getQuickSearchResults(@PathVariable(value = "key") String key) {
        System.out.println("/quick called = " + key);
        return searchService.getQuickSearchResults(key);
    }

}
