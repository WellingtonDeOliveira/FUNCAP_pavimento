package br.com.api.usuarios.repositorio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.usuarios.modelo.UsuarioModelo;

@Repository
public interface UsuarioRepositorio extends CrudRepository<UsuarioModelo, String>{
    
    @Query(value = "select u from UsuarioModelo u where upper(trim(u.nome)) like %?1%")
    List<UsuarioModelo> buscarPorNome(String nome);

    Optional<UsuarioModelo> findById(UUID idUser);

    UsuarioModelo findByEmail(String email);
}
