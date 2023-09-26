package med.voll.api.repository;

import med.voll.api.domain.paciente.DadosListagemPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    Page<Paciente> findAllByNomeContains(String nome, Pageable paginacao);

    Page<Paciente> findAllByCpfStartingWith(String cpf, Pageable paginacao);
}
