package com.iditgraber.trc.facad;

import com.iditgraber.trc.repository.ClientsRepository;
import com.iditgraber.trc.dto.Client;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService {
    ClientsRepository clientsRepository;

    public ClientService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public Client getById(int clientId)  {
        return clientsRepository.findById(clientId).orElseThrow(()-> new EntityNotFoundException("client not found"));
    }

    public int addNewClient(int clientId) throws Exception {
        Optional<Client> client = clientsRepository.findById(clientId);
        if (!client.isPresent()) {
            Client clientEntity = new Client(clientId);
            clientsRepository.save(clientEntity);
        }
        return 0;
    }
}