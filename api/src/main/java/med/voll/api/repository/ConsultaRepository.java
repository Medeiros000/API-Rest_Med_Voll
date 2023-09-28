package med.voll.api.repository;

import med.voll.api.model.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Page<Consulta> findAllByData(String data, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM consultas WHERE andamento LIKE '%' || :termo || '%'")
    Page<Consulta> findAllByAndamentoContainsIgnoreCase(@Param("termo") String termo, Pageable pageable);
}
