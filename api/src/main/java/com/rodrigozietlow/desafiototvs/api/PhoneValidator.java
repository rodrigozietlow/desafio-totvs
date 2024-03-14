package com.rodrigozietlow.desafiototvs.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Phone Validator
 * @author Rodrigo Zietlow
 * 
 */
public class PhoneValidator {
  
  private Phone phone;

  public PhoneValidator(Phone phone) {
    this.phone = phone;
  }

  
  /** 
   * Checks if phone is a valid string
   * @return boolean valid
   */
  public boolean notEmpty() {
    return phone.number != null && !phone.number.isEmpty();
  }

  /**
   * Checks if phone is valid according to specified regex
   * @return boolean valid
   */
  public boolean validFormat() {
    Pattern pattern = Pattern.compile("^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$");
    Matcher matcher = pattern.matcher(phone.number);

    return matcher.find();
  }

  /**
   * Checks if phone is valid according to all written rules
   * throws exception when invalid
   * @return
   * @throws Exception
   */
  public boolean validateAll() throws Exception {
    if (!this.notEmpty()) {
      throw new Exception("Não pode ser vazio");
    }
    if (!this.validFormat()) {
      throw new Exception("Formato inválido");
    }
    
    return true;
  }
}
