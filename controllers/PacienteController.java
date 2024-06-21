package br.edu.gov.fatec.saudebem.controllers;

import br.edu.gov.fatec.saudebem.dtos.PacienteRecordDto;
import br.edu.gov.fatec.saudebem.models.PacienteModel;
import br.edu.gov.fatec.saudebem.repositories.PacienteRepository;
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
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<PacienteModel> savePaciente(@RequestBody @Valid PacienteRecordDto pacienteRecordDto) {
        PacienteModel pacienteModel = new PacienteModel();
        BeanUtils.copyProperties(pacienteRecordDto, pacienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteRepository.save(pacienteModel));
    }

    @GetMapping
    public ResponseEntity<List<PacienteModel>> getAllPacientes(){
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOnePaciente(@PathVariable(value = "id") int id) {
        Optional<PacienteModel> paciente0 = pacienteRepository.findById(id);
        if (paciente0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente not found.");
        }
        paciente0.get().add(linkTo(methodOn(PacienteController.class).getAllPacientes()).withRel("Pacientes List"));
        return ResponseEntity.status(HttpStatus.OK).body(paciente0.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePaciente(@PathVariable(value = "id") int id, @RequestBody @Valid PacienteRecordDto pacienteRecordDto) {
        Optional<PacienteModel> paciente0 = pacienteRepository.findById(id);
        if (paciente0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente not found.");
        }
        var paciente = paciente0.get();
        BeanUtils.copyProperties(pacienteRecordDto, paciente, "id");
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.save(paciente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePaciente(@PathVariable(value = "id") int id) {
        Optional<PacienteModel> paciente0 = pacienteRepository.findById(id);
        if (paciente0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente not found.");
        }
        pacienteRepository.delete(paciente0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Paciente deleted successfully.");
    }

}

