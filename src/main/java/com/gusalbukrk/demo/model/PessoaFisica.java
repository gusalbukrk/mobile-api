package com.gusalbukrk.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class PessoaFisica extends User {
  private String cpf;
}
