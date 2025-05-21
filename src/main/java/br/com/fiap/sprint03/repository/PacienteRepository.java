package br.com.fiap.sprint03.repository;

import br.com.fiap.sprint03.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
