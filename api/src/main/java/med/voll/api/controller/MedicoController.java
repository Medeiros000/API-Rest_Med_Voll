package med.voll.api.controller;

import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.service.MedicoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping ("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        service.salvarMedico(medico);
        var uri= uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort ={"nome"}) Pageable paginacao){
        var page = service.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = service.buscarMedicoPorId(dados.id()).get();
        BeanUtils.copyProperties(dados, medico);
        service.salvarMedico(medico);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{termo}")
    public ResponseEntity<Object> buscar(@PathVariable String termo, Pageable paginacao){
        try {
            var crm = Long.parseLong(termo);
            var page = service.buscarMedicoPorCrm(crm, paginacao).map(DadosListagemMedico::new);
            System.out.println("Buscando por id: " + crm);
            return ResponseEntity.ok(page.stream().toList());
        } catch (NumberFormatException ignored) {
        }
        var page = service.procurarMedico(termo, paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page.stream().toList());
    }
}
