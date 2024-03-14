package com.rodrigozietlow.desafiototvs.api;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Phone Entity-Record
 * @author Rodrigo Zietlow
 * 
 */
@Entity
public class Phone {

  public @Id @GeneratedValue Long id;
  public String number;

  @JsonBackReference
  @ManyToOne
  public Client owner;

  public Phone() {}
  
  public Phone(Long id, String number, Client owner) {
    this.id = id;
    this.number = number;
    this.owner = owner;
  }
}
