package com.rodrigozietlow.desafiototvs.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


/**
 * Client Entity-Record
 * @author Rodrigo Zietlow
 * 
 */
@Entity
public class Client {

  public @Id @GeneratedValue Long id;
  public String name;
  public String address;
  public String district;
  
  @JsonManagedReference
  @OneToMany(mappedBy="owner")
  public List<Phone> phones;

  public Client() {}
  public Client(Long id,String name, String address, String district, List<Phone> phones) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.district = district;
    this.phones = phones;
  }

}
