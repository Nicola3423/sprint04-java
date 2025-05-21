package br.com.fiap.sprint03.controller;

import br.com.fiap.sprint03.service.AiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/ai-chat")
    public String mostrarFormularioChat() {
        return "chat/formulario";
    }

    @PostMapping("/ai-chat")
    public String processarPergunta(
            @RequestParam String pergunta,
            Model model
    ) {
        String resposta = aiService.gerarResposta(pergunta);
        model.addAttribute("pergunta", pergunta);
        model.addAttribute("resposta", resposta);
        return "chat/resposta";
    }
}