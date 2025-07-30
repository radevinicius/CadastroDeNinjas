package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO ninjaNovo = ninjaService.cadastrarNinjas(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + ninjaNovo.getNome()+ " o ID é: "+ninjaNovo.getId());

    }
    //MOSTRAR NINJA POR ID (READ)

    @GetMapping("/todos")
        public ResponseEntity<List<NinjaDTO>> listarNinjas(){
            List<NinjaDTO> ninjaNovo = ninjaService.listarNinjas();
            return ResponseEntity.ok(ninjaNovo);
    }

    //MOSTRAR TODOS OS NINJAS (READ)
    @GetMapping("/todosID/{id}")
    public ResponseEntity<?> mostrarNinjaPorId(@PathVariable Long id){
        if (ninjaService.listarNinjasPorId(id) != null){
           NinjaDTO ninjaID = ninjaService.listarNinjasPorId(id);
            return ResponseEntity.ok(ninjaID);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado");
        }
    }

    //ATUALIZAR NINJA (UPDATE)
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> alterarNinjas(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
       NinjaDTO ninjas = ninjaService.alterarNinjas(id, ninjaAtualizado);
       if (ninjas != null){
           return ResponseEntity.ok(ninjas);
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja nao encontrado");
       }
    }

    //DELETAR NINJA (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
       if (ninjaService.listarNinjasPorId(id) != null){
           ninjaService.deletarNinjaPorId(id);
           return ResponseEntity.ok("O ninja: de id " + id +" foi deletado");
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado");
       }
    }

    @DeleteMapping("/deletar/todos")
    public void deletarTudo(){
        ninjaService.deletarTudo();
    }
}
