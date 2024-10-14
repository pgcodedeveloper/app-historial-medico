package com.nosql.nosql.controllers;

import com.nosql.nosql.models.DatosPaciente;
import com.nosql.nosql.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/nuevo-paciente")
    public ResponseEntity<?> agregarPaciente(@RequestBody DatosPaciente datosPaciente) {
        System.out.println(datosPaciente.toString());
        try {
            pacienteService.agregarPaciente(datosPaciente);
            return ResponseEntity.ok("Paciente registrado correctamente");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
