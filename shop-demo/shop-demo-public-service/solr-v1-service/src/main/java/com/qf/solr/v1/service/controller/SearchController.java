package com.qf.solr.v1.service.controller;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SearchController {

    @Autowired
    private SolrClient solrClient;

}
