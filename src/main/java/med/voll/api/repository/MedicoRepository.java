package med.voll.api.repository;

import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrueOrderByNome(Pageable paginacao);

    Page<Medico> findAllByEspecialidadeIsContainingIgnoreCaseOrNomeContainingIgnoreCaseOrderByNome(String especialidade, String nome, Pageable paginacao);

    Page<Medico> findAllByCrmStartingWith(String string, Pageable paginacao);
}
