package br.com.fiap.sprint03.controller;

import br.com.fiap.sprint03.model.DTO.*;
import br.com.fiap.sprint03.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public String listarMedicos(Model model) {
        List<MedicoDTO> medicos = medicoService.listarTodos();
        model.addAttribute("medicos", medicos);
        return "medicos/listar";
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("medicoDTO", new MedicoDTO());
        return "medicos/form";
    }

    @PostMapping("/salvar")
    public String salvarMedico(@ModelAttribute("medicoDTO") MedicoDTO medicoDTO) {
        if (medicoDTO.getId() == null) {
            medicoService.salvar(medicoDTO);
        } else {
            medicoService.atualizar(medicoDTO);
        }
        return "redirect:/medicos";
    }

    @GetMapping("/editar/{id}")
    public String editarMedico(@PathVariable Long id, Model model) {
        MedicoDTO medicoDTO = medicoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        model.addAttribute("medicoDTO", medicoDTO);
        return "medicos/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirMedico(@PathVariable Long id) {
        medicoService.excluir(id);
        return "redirect:/medicos";
    }
}

