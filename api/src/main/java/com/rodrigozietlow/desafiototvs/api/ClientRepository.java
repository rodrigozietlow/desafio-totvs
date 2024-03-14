package com.rodrigozietlow.desafiototvs.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Client JPA Repository
 * @author Rodrigo Zietlow
 * 
 */
interface ClientRepository extends JpaRepository<Client, Long> {

  /**
   * Check if exists a client with the specified
   * name
   * @param String name to be searched for
   * @return Long found client id or null
   */
  @Query("SELECT id FROM Client c WHERE c.name = ?1")
  Long findByName(String name);
}
