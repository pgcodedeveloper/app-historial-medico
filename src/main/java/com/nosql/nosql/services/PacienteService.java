package com.nosql.nosql.services;

import com.nosql.nosql.models.DatosPaciente;
import com.nosql.nosql.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agregarPaciente(DatosPaciente datosPaciente) throws Exception {
        Optional<DatosPaciente> existePaciente = pacienteRepository.findById(datosPaciente.getCI());
        if(existePaciente.isPresent()){
            throw new Exception("El paciente ya existe");
        }
        pacienteRepository.save(datosPaciente);
    }
}
