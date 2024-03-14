package com.rodrigozietlow.desafiototvs.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PhoneValidatorTests {
  Client phoneOwner;

  @BeforeEach
  public void init() {
    this.phoneOwner = new Client();
  }

  @Test()
  @DisplayName("Expect null or empty phone to error")
  public void phoneNull() {
    Phone p = new Phone(0L, null, phoneOwner);
    PhoneValidator valid = new PhoneValidator(p);

    Phone p2 = new Phone(0L, "", phoneOwner);
    PhoneValidator valid2 = new PhoneValidator(p2);

    assertFalse(valid.notEmpty());
    assertFalse(valid2.notEmpty());
  }

  @Test()
  @DisplayName("Expect not empty phone to pass")
  public void phoneNotEmpty() {
    Phone p = new Phone(0L, "anyphone", phoneOwner);
    PhoneValidator valid = new PhoneValidator(p);

    assertTrue(valid.notEmpty());
  }

  @Test()
  @DisplayName("Expect valid phone to pass")
  public void phoneValid() {
    Phone p = new Phone(0L, "(51) 99999-9999", phoneOwner);
    PhoneValidator valid = new PhoneValidator(p);

    assertTrue(valid.validFormat());
  }

  @Test()
  @DisplayName("Expect invalid phone to error")
  public void phoneInvalid() {
    Phone p = new Phone(0L, "(51) 9999-9999", phoneOwner);
    PhoneValidator valid = new PhoneValidator(p);
    Phone p2 = new Phone(0L, "51 999-9999", phoneOwner);
    PhoneValidator valid2 = new PhoneValidator(p2);
    Phone p3 = new Phone(0L, "9 9999-9999", phoneOwner);
    PhoneValidator valid3 = new PhoneValidator(p3);
    Phone p4 = new Phone(0L, "(00) 9 9999-9999", phoneOwner);
    PhoneValidator valid4 = new PhoneValidator(p4);

    assertFalse(valid.validFormat());
    assertFalse(valid2.validFormat());
    assertFalse(valid3.validFormat());
    assertFalse(valid4.validFormat());
  }
}
