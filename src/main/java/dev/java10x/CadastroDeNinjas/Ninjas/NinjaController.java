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
    @PutMapping("/atualizar/{id}")
    public NinjaModel alterarNinjas(@PathVariable Long id, @RequestBody NinjaModel ninjaAtualizado){
       return ninjaService.alterarNinjas(id, ninjaAtualizado);

    }

    //DELETAR NINJA (DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorId(@PathVariable Long id){
        ninjaService.deletarNinjaPorId(id);
    }
    @DeleteMapping("/deletar/todos")
    public void deletarTudo(){
        ninjaService.deletarTudo();
    }

}
