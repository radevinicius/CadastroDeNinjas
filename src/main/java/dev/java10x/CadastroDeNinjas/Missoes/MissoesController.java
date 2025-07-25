package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    //GET -- mandar uma requisição para mostrar missoes
    @GetMapping("/mostrar")
    public String mostrarMissao(){
        return "Missao mostrada";

    }
    //POST -- mandar uma requisição para cadastrar missoes
    @PostMapping("/criar")
    public String criarMissao(){
        return "Missao Criada";
    }
    //PUT -- mandar uma requisição para alterar missoes
    @PutMapping("/alterar")
        public String alterarMissao(){
            return "Missao alterada";

    }
    //DELETE -- mandar uma requisição para deletar missoes
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao alterada";

    }
}
