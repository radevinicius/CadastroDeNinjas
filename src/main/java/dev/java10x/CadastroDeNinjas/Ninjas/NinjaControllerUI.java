package dev.java10x.CadastroDeNinjas.Ninjas;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {
    public final NinjaService ninjaService;

    public NinjaControllerUI(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/todos")
    public String listarNinjas(Model model){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id){
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/todos";
    }
    @GetMapping("/detalhesNinja/{id}")
    public String mostrarNinjasPorId(@PathVariable Long id, Model model){
        NinjaDTO ninjas = ninjaService.listarNinjasPorId(id);
        if (ninjas != null) {
            model.addAttribute("ninja", ninjas);

            return "detalhesNinja";
        } else {
            return "Id nao encontrado";
        }
    }
    @GetMapping("/cadastrar")
    public String criarNinja(Model model){
        model.addAttribute("ninja", new NinjaDTO());
        return "cadastrar";

    }


    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.cadastrarNinjas(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/ui/todos";
    }

    @GetMapping("/editar/{id}")
    public String editarNinja(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "atualizar"; // nome da página HTML de atualização
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "redirect:/ninjas/ui/todos";
        }
    }

    @PostMapping("/atualizar")
    public String atualizarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes, Long id) {
        ninjaService.alterarNinjas(id, ninja); // Você precisa implementar esse método no serviço
        redirectAttributes.addFlashAttribute("mensagem", "Ninja atualizado com sucesso!");
        return "redirect:/ninjas/ui/todos";
    }
}

