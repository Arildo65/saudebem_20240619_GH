package br.edu.gov.fatec.saudebem.controllers;

import br.edu.gov.fatec.saudebem.dtos.PessoaRecordDto;
import br.edu.gov.fatec.saudebem.models.PessoaModel;
import br.edu.gov.fatec.saudebem.repositories.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;

    @PostMapping
    public ResponseEntity<PessoaModel> savePessoa(@RequestBody @Valid PessoaRecordDto pessoaRecordDto) {
        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaRecordDto, pessoaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoaModel));
    }

    @GetMapping
    public ResponseEntity<List<PessoaModel>> getAllPessoas(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOnePessoa(@PathVariable(value = "id") int id) {
        Optional<PessoaModel> pessoa0 = pessoaRepository.findById(id);
        if (pessoa0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
        }
        pessoa0.get().add(linkTo(methodOn(PessoaController.class).getAllPessoas()).withRel("Pessoas List"));
        return ResponseEntity.status(HttpStatus.OK).body(pessoa0.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePessoa(@PathVariable(value = "id") int id, @RequestBody @Valid PessoaRecordDto pessoaRecordDto) {
        Optional<PessoaModel> pessoa0 = pessoaRepository.findById(id);
        if (pessoa0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
        }
        var pessoaModel = pessoa0.get();
        BeanUtils.copyProperties(pessoaRecordDto, pessoaModel, "id");
        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.save(pessoaModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id") int id) {
        Optional<PessoaModel> pessoa0 = pessoaRepository.findById(id);
        if (pessoa0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
        }
        pessoaRepository.delete(pessoa0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deleted successfully.");
    }
}


