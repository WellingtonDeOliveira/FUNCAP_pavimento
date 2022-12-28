package br.com.api.usuarios.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.usuarios.dto.UsuarioRoleDto;
import br.com.api.usuarios.modelo.RespostaModelo;
import br.com.api.usuarios.modelo.UsuarioModelo;
import br.com.api.usuarios.repositorio.UsuarioRepositorio;
import br.com.api.usuarios.servico.RolesServico;
import br.com.api.usuarios.servico.UsuarioServico;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioControle {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioServico us;

    @Autowired
    private RolesServico rs;

    @DeleteMapping("/remover/{cpf}")
    public ResponseEntity<RespostaModelo> excluir(@PathVariable String cpf){
        return us.remover(cpf);
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody UsuarioModelo um){
        return us.cadastrarAlterar(um, "alterar");
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioModelo um){
        return us.cadastrarAlterar(um, "cadastrar");
    }

    @PostMapping("/role")
    public UsuarioModelo role(@RequestBody UsuarioRoleDto urd){
        return rs.execute(urd);
    }

    @GetMapping("/listar")
    public Iterable<UsuarioModelo> listar(){
        return us.listar();
    }

    @GetMapping(value = "buscarPorNome")
    public ResponseEntity<List<UsuarioModelo>> buscarPorNome(@RequestParam(name = "nome") String nome){
        
        List<UsuarioModelo> usuarioModelo = usuarioRepositorio.buscarPorNome(nome.trim().toUpperCase());
    
        return new ResponseEntity<List<UsuarioModelo>>(usuarioModelo, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public String rota(){
        return "API de usuarios funcionando";
    }

}
