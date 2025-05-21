package br.com.fiap.sprint03.service;

import br.com.fiap.sprint03.model.DTO.PacienteDTO;
import br.com.fiap.sprint03.model.EmailMessage;
import br.com.fiap.sprint03.model.Medico;
import br.com.fiap.sprint03.model.Paciente;
import br.com.fiap.sprint03.repository.MedicoRepository;
import br.com.fiap.sprint03.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    private final MedicoRepository medicoRepository;
    private final EmailProducer emailProducer;

    public PacienteService(MedicoRepository medicoRepository, EmailProducer emailProducer) {
        this.medicoRepository = medicoRepository;
        this.emailProducer = emailProducer;
    }

    private PacienteDTO toDTO(Paciente paciente) {
        return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getDataDeNascimento());
    }

    private Paciente toEntity(PacienteDTO dto) {
        return new Paciente(dto.getId(), dto.getNome(), dto.getTelefone(), dto.getEmail(), dto.getDataDeNascimento());
    }

    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PacienteDTO salvar(PacienteDTO pacienteDTO) {
        Paciente paciente = toEntity(pacienteDTO);

        notifyMedicos(paciente);
        return toDTO(pacienteRepository.save(paciente));
    }

    public Optional<PacienteDTO> buscarPorId(Long id) {
        return pacienteRepository.findById(id).map(this::toDTO);
    }

    public void excluir(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado para exclusão");
        }
        pacienteRepository.deleteById(id);
    }

    public PacienteDTO atualizar(PacienteDTO pacienteDTO) {
        if (pacienteDTO.getId() == null || !pacienteRepository.existsById(pacienteDTO.getId())) {
            throw new RuntimeException("Paciente não encontrado para atualização");
        }
        Paciente paciente = toEntity(pacienteDTO);
        return toDTO(pacienteRepository.save(paciente));
    }

    private void notifyMedicos(Paciente paciente) {
        Iterable<Medico> medicos = medicoRepository.findAll();

        medicos.forEach(medico -> {
            EmailMessage message = new EmailMessage();
            message.setTo(medico.getEmail());
            message.setSubject("Novo Paciente Cadastrado");
            message.setBody("O paciente " + paciente.getNome() + " foi cadastrado no sistema.");

            emailProducer.sendEmailNotification(message);
        });
    }
}
