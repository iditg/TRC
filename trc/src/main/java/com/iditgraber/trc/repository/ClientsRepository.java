package com.iditgraber.trc.repository;

import com.iditgraber.trc.dto.Client;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer>{
    Optional<Client> findById(int clientId);
    List<Client> findAll();
}

