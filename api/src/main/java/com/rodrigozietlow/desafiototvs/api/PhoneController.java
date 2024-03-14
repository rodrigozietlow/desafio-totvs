package com.rodrigozietlow.desafiototvs.api;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/**
 * Phone Rest Controller
 * @author Rodrigo Zietlow
 * 
 */
@RestController
public class PhoneController {

  private final PhoneRepository repository;

  PhoneController(PhoneRepository repository) {
    this.repository = repository;
  }

  
  /** 
   * List all phones
   * @return List<Phone>
   */
  @GetMapping("/phone")
  List<Phone> all() {
    return this.repository.findAll();
  }

  /**
   * Create a new Phone
   * @param Phone phone to be created
   * @return Phone created phone (with id)
   */
  @PostMapping("/phone")
  Phone create(@RequestBody Phone phone) {

    PhoneValidator validator = new PhoneValidator(phone);

    try {
      validator.validateAll();
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(400), e.getMessage());
    }

    return this.repository.save(phone);
  }

  /**
   * Update phone
   * @param Phone phone to be updated
   * @return Phone updated phone
   */
  @PutMapping("/phone")
  Phone update(@RequestBody Phone phone) {

    PhoneValidator validator = new PhoneValidator(phone);

    try {
      validator.validateAll();
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatusCode.valueOf(400), e.getMessage());
    }

    return this.repository.save(phone);
  }

  /**
   * Delete existing phone
   * @param Long id of the phone to be deleted
   */
  @DeleteMapping("/phone/{id}")
  void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }

  /**
   * Validate if phone number is currently associated
   * with another client
   * @param String phone number to be validated
   * @return boolean is invalid
   */
  @GetMapping("/phone/validate/{number}")
  boolean validate(@PathVariable String number) {
    return repository.findByNumber(number) != null;
  }
}
