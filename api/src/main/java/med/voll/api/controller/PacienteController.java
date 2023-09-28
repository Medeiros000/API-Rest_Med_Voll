package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import med.voll.api.dto.PacienteDto;
import med.voll.api.model.Paciente;
import med.voll.api.service.PacienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping ("/pacientes")
public class PacienteController {


    @Autowired
    private PacienteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid PacienteDto dados, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente();
        BeanUtils.copyProperties(dados, paciente);
        service.save(paciente);
        var uri= uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteResponse(paciente));
    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(sort ={"nome"}) Pageable paginacao){
        var page = service.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        var paciente = service.findById(dados.id());
        BeanUtils.copyProperties(dados, paciente);
        service.save(paciente);
        return ResponseEntity.ok(new PacienteResponse(paciente));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{termo}")
    public ResponseEntity<Object> detalhar(@PathVariable String termo, Pageable paginacao){
        try { // Tenta converter para Long para buscar por CPF
            Long id = Long.valueOf(termo);
            var page = service.findAllByCpfStartingWith(id.toString(), paginacao).map(DadosListagemPaciente::new);
            page.stream().toList().forEach(System.out::println);
            return ResponseEntity.ok(page.stream().toList());
        } catch (NumberFormatException e) { // Lida com exceção de conversão para Long e busca por nome
            var page = service.findAllByNomeContains(termo, paginacao).map(DadosListagemPaciente::new);
            page.stream().toList().forEach(System.out::println);
            return ResponseEntity.ok(page.stream().toList());
        }
    }
}
