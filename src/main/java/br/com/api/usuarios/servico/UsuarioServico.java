package br.com.api.usuarios.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.usuarios.modelo.RespostaModelo;
import br.com.api.usuarios.modelo.UsuarioModelo;
import br.com.api.usuarios.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio ur;

    @Autowired
    private RespostaModelo rm;

  
    public UsuarioModelo execute(UsuarioModelo user) {
  
      UsuarioModelo existsUser = ur.findByEmail(user.getEmail());
  
      if (existsUser != null) {
        throw new Error("User already exists!");
      }
  
      UsuarioModelo createdUser = ur.save(user);
  
      return createdUser;
    }

    // CRIPTOGRAFIA DA SENHA
    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Método para listar todos os usuarios
    public Iterable<UsuarioModelo> listar(){
        return ur.findAll();
    }

    // Método para cadastrar ou alterar usuarios
    public ResponseEntity<?> cadastrarAlterar(UsuarioModelo um, String acao){

        if(um.getNome().equals("")){
            rm.setMensagem("O nome é obrigatótio!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else if(um.getTelefone().equals("")){
            rm.setMensagem("O telefone é obrigatótio!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else if(um.getEmail().equals("")){
            rm.setMensagem("O Email é obrigatótio!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else if(um.getSenha().equals("")){
            rm.setMensagem("A Senha é obrigatótio!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else{
            um.setSenha(passwordEncoder().encode(um.getSenha()));
            
            if(acao.equals("cadastrar")){
                return new ResponseEntity<UsuarioModelo>(ur.save(um), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<UsuarioModelo>(ur.save(um), HttpStatus.OK);
            }
        }

    }

    // Método para remover usuario
    public ResponseEntity<RespostaModelo> remover(String cpf){
        ur.deleteById(cpf);
        rm.setMensagem("Usuario removido co sucesso");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
    }
}
