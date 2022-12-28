package br.com.api.usuarios.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.usuarios.repositorio.UsuarioRepositorio;
import br.com.api.usuarios.dto.UsuarioRoleDto;
import br.com.api.usuarios.modelo.RolesModelo;
import br.com.api.usuarios.modelo.UsuarioModelo;

@Service
public class RolesServico {

  @Autowired
  UsuarioRepositorio ur;

  public UsuarioModelo execute(UsuarioRoleDto createUserRoleDTO) {

    Optional<UsuarioModelo> userExists = ur.findById(createUserRoleDTO.getIdUser());
    List<RolesModelo> roles = new ArrayList<>();

    if (Optional.empty() != null) {
      throw new Error("Usuario não pode ser vazio");
    }

    roles = createUserRoleDTO.getIdsRoles().stream().map(role -> {
      return new RolesModelo(role);
    }).collect(Collectors.toList());

    UsuarioModelo user = userExists.get();

    user.setRoles(roles);

    ur.save(user);

    return user;

  }
}
