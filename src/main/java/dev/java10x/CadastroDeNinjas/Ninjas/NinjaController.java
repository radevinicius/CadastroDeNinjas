package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {
    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Boas Vindas !";
    }

    //ADICIONAR NINJA (CREATE)
    @PostMapping("/criar")
    public String criarNinja(){
        return "Ninja Criado";
    }

    //MOSTRAR NINJA POR ID (READ)

    @GetMapping("/todos")
        public String mostrarTodosOsNinjas(){
            return "Ninja mostrado";
    }

    //MOSTRAR TODOS OS NINJAS (READ)
    @GetMapping("/todosID")
    public String mostrarTodosOsNinjasPorId(){
        return "Ninjas mostrados por ID";
    }

    //ATUALIZAR NINJA (UPDATE)
    @PutMapping("/alterarID")
    public String alterarNinjaPorId(){
        return "Alterar ninja por ID";
    }

    //DELETAR NINJA (DELETE)
    @DeleteMapping("/deletar")
    public String deletarNinjaPorId(){
        return "Deletar ninja por ID";
    }

}
