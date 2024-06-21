package br.edu.gov.fatec.saudebem.controllers;

import br.edu.gov.fatec.saudebem.dtos.MedicoRecordDto;
import br.edu.gov.fatec.saudebem.models.MedicoModel;
import br.edu.gov.fatec.saudebem.repositories.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/medicos")
public class MedicoController {


    @Autowired
    MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<MedicoModel> saveMedico(@RequestBody @Valid MedicoRecordDto medicoRecordDto) {
        var medicoModel = new MedicoModel();
        BeanUtils.copyProperties(medicoRecordDto, medicoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoRepository.save(medicoModel));
    }

    @GetMapping
    public ResponseEntity<List<MedicoModel>> getAllMedicos(){
        return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.findAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<Object> getOneMedico(@PathVariable(value = "id") int id) {
        Optional<MedicoModel> medico0 = medicoRepository.findById(id);
        if (medico0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico not found.");
        }
        medico0.get().add(linkTo(methodOn(MedicoController.class).getAllMedicos()).withRel("Medicos List"));
        return ResponseEntity.status(HttpStatus.OK).body(medico0.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateMedico(@PathVariable(value = "id") int id, @RequestBody @Valid MedicoRecordDto medicoRecordDto) {
        Optional<MedicoModel> medicoOptional = medicoRepository.findById(id);
        if (medicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico not found.");
        }

        MedicoModel medicoModel = medicoOptional.get();
        medicoModel.setNome(medicoRecordDto.nome());
        medicoModel.setCpf(medicoRecordDto.cpf());
        medicoModel.setEndereco(medicoRecordDto.endereco());
        medicoModel.setIdade(medicoRecordDto.idade());
        medicoModel.setTelefone(medicoRecordDto.telefone());
        medicoModel.setCrm(medicoRecordDto.crm());
        medicoModel.setEspecialidade(medicoRecordDto.especialidade());

        medicoRepository.save(medicoModel);

        return ResponseEntity.ok(medicoModel);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteMedico(@PathVariable(value = "id") int id) {
        Optional<MedicoModel> medico0 = medicoRepository.findById(id);
        if (medico0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico not found.");
        }
        medicoRepository.delete(medico0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Medico deleted successfully.");
    }

}
