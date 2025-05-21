package br.com.fiap.sprint03.repository;

import br.com.fiap.sprint03.model.Medico;
import br.com.fiap.sprint03.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findAll();
}