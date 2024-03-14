package com.rodrigozietlow.desafiototvs.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Client Rest Controller
 * @author Rodrigo Zietlow
 * 
 */

@RestController
public class ClientController {

  private final ClientRepository repository;

  ClientController(ClientRepository repository) {
    this.repository = repository;
  }

  
  
  /** 
   * List all clients
   * @return List<Client>
   */
  @GetMapping("/client") 
  List<Client> all() {
    return repository.findAll();
  }

  
  /** 
   * Get one client by ID 
   * @param Long id
   * @return Client
   */
  @GetMapping("/client/{id}")
  Client one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new Error("404"));
  }

  
  /** 
   * Create a new client
   * @param Client client to be created
   * @return Client created client (with id)
   */
  @PostMapping("/client") 
  Client create(@RequestBody Client client) {
    return repository.save(client);
  }
  
  /**
   * Update a client
   * @param Client client to be updated
   * @return Client updated client
   */
  @PutMapping("/client") 
  Client update(@RequestBody Client client) {
    return repository.save(client);
  }

  /**
   * Delete existing client
   * @param Long id to be deleted
   */
  @DeleteMapping("/client/{id}") 
  void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }

  /**
   * Validate if the client name is currently
   * taken or free to use
   * @param String name
   * @return boolean currently taken
   */
  @GetMapping("/client/validate/{name}")
  boolean validate(@PathVariable String name) {
    return repository.findByName(name) != null;
  }
}
