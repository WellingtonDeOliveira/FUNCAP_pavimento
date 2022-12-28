package br.com.api.usuarios.modelo;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class UsuarioModelo {
    
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    @ManyToMany
    private List<RolesModelo> roles;
    private Boolean manter_logado;

}
