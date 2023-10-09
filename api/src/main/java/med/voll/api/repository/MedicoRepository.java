package med.voll.api.repository;

import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findAllByAtivoTrueOrderByNome();

    Page<Medico> findAllByEspecialidadeIsContainingOrNomeContaining(String especialidade, String nome, Pageable paginacao);

    Page<Medico> findAllByCrmStartingWith(String string, Pageable paginacao);
}
