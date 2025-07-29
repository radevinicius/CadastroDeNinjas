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
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninjaDTO){

        return ninjaService.cadastrarNinjas(ninjaDTO);
    }

    //MOSTRAR NINJA POR ID (READ)

    @GetMapping("/todos")
        public List<NinjaDTO> listarNinjas(){
            return ninjaService.listarNinjas();
    }

    //MOSTRAR TODOS OS NINJAS (READ)
    @GetMapping("/todosID/{id}")
    public NinjaDTO mostrarNinjaPorId(@PathVariable Long id){
        return ninjaService.listarNinjasPorId(id);
    }

    //ATUALIZAR NINJA (UPDATE)
    @PutMapping("/atualizar/{id}")
    public NinjaDTO alterarNinjas(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
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
