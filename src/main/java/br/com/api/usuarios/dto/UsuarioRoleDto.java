package br.com.api.usuarios.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UsuarioRoleDto {

  private UUID idUser;

  private List<UUID> idsRoles;

}
