package com.gusalbukrk.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class PessoaJuridica extends User {
  private String cnpj;
}
