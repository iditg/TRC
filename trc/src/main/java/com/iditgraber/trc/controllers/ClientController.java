package com.iditgraber.trc.controllers;

import com.iditgraber.trc.facad.ClientService;
import com.iditgraber.trc.dto.Client;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringEscapeUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.cluster.node.tasks.list.ListTasksRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.tasks.ListTasksResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.tasks.TaskId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
public class ClientController {
    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping(value = "/{clientId}", produces = APPLICATION_JSON_VALUE)
    public Client getClientById(@PathVariable("clientId") Integer clientId) throws Exception {
        return clientService.getById(clientId);
    }

}
