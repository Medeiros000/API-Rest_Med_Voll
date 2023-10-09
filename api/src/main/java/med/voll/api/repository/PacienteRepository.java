package med.voll.api.repository;

import med.voll.api.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query(nativeQuery = true,
            value = "SELECT * FROM vollmed_api.pacientes WHERE nome ILIKE '%' || (:nome) || '%'")
    Page<Paciente> findAllByNomeContainsIgnoreCase(@Param("nome") String nome, Pageable paginacao);

    Page<Paciente> findAllByCpfStartingWithIgnoreCase(String cpf, Pageable paginacao);
}
