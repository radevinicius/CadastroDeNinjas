package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {
    public NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Boas Vindas !";
    }

    //ADICIONAR NINJA (CREATE)
    @PostMapping("/criar")
    public NinjaModel criarNinja(@RequestBody NinjaModel ninjaModel){

        return ninjaService.cadastrarNinjas(ninjaModel);
    }

    //MOSTRAR NINJA POR ID (READ)

    @GetMapping("/todos")
        public List<NinjaModel> listarNinjas(){
            return ninjaService.listarNinjas();
    }

    //MOSTRAR TODOS OS NINJAS (READ)
    @GetMapping("/todosID/{id}")
    public NinjaModel mostrarNinjaPorId(@PathVariable Long id){
        return ninjaService.listarNinjasPorId(id);
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
