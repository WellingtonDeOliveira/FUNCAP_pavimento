package br.com.api.usuarios.modelo;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Data
public class RolesModelo {

  @Id
  @GeneratedValue
  private UUID id;
  private String name;

  public RolesModelo(UUID id) {
    this.id = id;
  }

}
