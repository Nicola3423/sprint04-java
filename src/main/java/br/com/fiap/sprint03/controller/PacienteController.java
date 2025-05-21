package br.com.fiap.sprint03.controller;

import br.com.fiap.sprint03.model.DTO.PacienteDTO;
import br.com.fiap.sprint03.model.Medico;
import br.com.fiap.sprint03.model.Paciente;
import br.com.fiap.sprint03.service.MedicoService;
import br.com.fiap.sprint03.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listarPacientes(Model model) {
        List<PacienteDTO> pacientes = pacienteService.listarTodos();
        model.addAttribute("pacientes", pacientes);
        return "pacientes/lista";
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("pacienteDTO", new PacienteDTO());
        return "pacientes/form";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        PacienteDTO pacienteDTO = pacienteService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        model.addAttribute("pacienteDTO", pacienteDTO);
        return "pacientes/form";
    }

    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO) {
        if (pacienteDTO.getId() == null) {
            pacienteService.salvar(pacienteDTO);
        } else {
            pacienteService.atualizar(pacienteDTO);
        }
        return "redirect:/pacientes";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id) {
        pacienteService.excluir(id);
        return "redirect:/pacientes";
    }
}
