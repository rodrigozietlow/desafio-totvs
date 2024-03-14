package com.rodrigozietlow.desafiototvs.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Phone JPA Repository
 * @author Rodrigo Zietlow
 * 
 */
interface PhoneRepository extends JpaRepository<Phone, Long> {

  /**
   * Check if exists a phone with the specified
   * number
   * @param String number to be searched for
   * @return Long found phone id or null
   */
  @Query("SELECT id FROM Phone p WHERE p.number = ?1")
  Long findByNumber(String number);
}
