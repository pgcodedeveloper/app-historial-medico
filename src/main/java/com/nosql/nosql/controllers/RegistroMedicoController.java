package com.nosql.nosql.controllers;

import com.nosql.nosql.models.RegistroMedico;
import com.nosql.nosql.services.RegistroMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registro-medico")
public class RegistroMedicoController {

    @Autowired
    private RegistroMedicoService registroMedicoService;

    @PostMapping("/agregar-registro-medico")
    public ResponseEntity<?> nuevoRegistro(@RequestBody RegistroMedico registroMedico){
        if(registroMedico.getFecha() != null && registroMedico.getTipo() != null && registroMedico.getDiagnostico() != null && registroMedico.getMedico() != null && registroMedico.getInstitucion() != null){
            try {
                registroMedicoService.agregarRegistroMedico(registroMedico);
                return ResponseEntity.ok("Registro medico agregado correctamente");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(e.getMessage());
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faltan datos");
        }
    }

    @GetMapping("/consultar-historial-medico")
    public ResponseEntity<?> consultarHistorial(@RequestParam String cedula, @RequestParam(defaultValue = "10") int itemsPorPagina,@RequestParam(defaultValue = "1") int pagina){
        try {
            Page<RegistroMedico> historial = registroMedicoService.consultarHistorialMedico(cedula, pagina, itemsPorPagina);
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/buscar-historial-por-criterio")
    public ResponseEntity<?> buscarPorCriterio(@RequestParam(value = "tipo", required = false) String tipo,
                                               @RequestParam(value = "diagnostico", required = false) String diagnostico,
                                               @RequestParam(value = "medico", required = false) String medico,
                                               @RequestParam(value = "institucion", required = false) String institucion){
        try {
            return ResponseEntity.ok(registroMedicoService.buscarRegistroPorCriterio(tipo,diagnostico,medico,institucion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
