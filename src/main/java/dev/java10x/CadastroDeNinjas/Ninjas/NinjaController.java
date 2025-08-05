package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")

public class NinjaController {
    public final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @Operation(summary = "Mensagem de boas vindas", description = "Essa roda da uma mensagem de boas vindas")
    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Boas Vindas !";
    }

    //ADICIONAR NINJA (CREATE)
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja, e adiciona no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO ninjaNovo = ninjaService.cadastrarNinjas(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + ninjaNovo.getNome()+ " o ID é: "+ninjaNovo.getId());

    }
    @Operation(summary = "Lista todos os ninjas", description = "Rota lista todos os ninjas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado ninjas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar ninjas")
    })
    //MOSTRAR NINJA POR ID (READ)
    @GetMapping("/todos")
        public ResponseEntity<List<NinjaDTO>> listarNinjas(){
            List<NinjaDTO> ninjaNovo = ninjaService.listarNinjas();
            return ResponseEntity.ok(ninjaNovo);
    }


    //MOSTRAR TODOS OS NINJAS (READ)
    @Operation(summary = "Lista o ninja pelo ID", description = "Rota lista ninja por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não encontrado")
    })
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
    @Operation(summary = "Altera ninja por ID", description = "Rota um ninja pelo seu IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possivel alterar")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> alterarNinjas(
            @Parameter(description = "Usuario manda o id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisição")
            @RequestBody NinjaDTO ninjaAtualizado){

       NinjaDTO ninjas = ninjaService.alterarNinjas(id, ninjaAtualizado);
       if (ninjas != null){
           return ResponseEntity.ok(ninjas);
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja nao encontrado");
       }
    }


    //DELETAR NINJA (DELETE)
    @Operation(summary = "Deleta Ninja por ID", description = "Rota deletar ninja por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
       if (ninjaService.listarNinjasPorId(id) != null){
           ninjaService.deletarNinjaPorId(id);
           return ResponseEntity.ok("O ninja: de id " + id +" foi deletado");
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado");
       }
    }

    @Operation(summary = "Deleta todos os ninjas", description = "Rota deleta todos os ninjas")
    @ApiResponse(responseCode = "200", description = "Ninjas deletados com sucesso!")
    @DeleteMapping("/deletar/todos")
    public void deletarTudo(){
        ninjaService.deletarTudo();
    }
}
