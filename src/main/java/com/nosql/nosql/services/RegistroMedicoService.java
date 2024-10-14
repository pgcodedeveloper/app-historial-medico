package com.nosql.nosql.services;

import com.nosql.nosql.enums.TipoRegistroMedico;
import com.nosql.nosql.models.DatosPaciente;
import com.nosql.nosql.models.RegistroMedico;
import com.nosql.nosql.repositories.PacienteRepository;
import com.nosql.nosql.repositories.RegistroMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroMedicoService {

    @Autowired
    private RegistroMedicoRepository registroMedicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void agregarRegistroMedico(RegistroMedico registroMedico) throws Exception {
        Optional<DatosPaciente> existePaciente = pacienteRepository.findById(registroMedico.getPaciente().getCI());
        if(existePaciente.isPresent()){
            registroMedicoRepository.save(registroMedico);
        }
        else{
            throw new Exception("No existe un paciente con la cédula aportada como parámetro");
        }
    }

    public Page<RegistroMedico> consultarHistorialMedico(String cedula, int pagina, int itemsPorPagina) throws Exception {
        Optional<DatosPaciente> existePaciente = pacienteRepository.findById(cedula);
        if(existePaciente.isPresent()){
            Pageable pageable = PageRequest.of(pagina - 1, itemsPorPagina, Sort.by(Sort.Direction.DESC, "createdAt"));
            return registroMedicoRepository.findByPacienteCI(existePaciente.get().getCI(), pageable);
        }
        else{
            throw new Exception("No existe un paciente con la cédula aportada como parámetro");
        }
    }

    public List<RegistroMedico> buscarRegistroPorCriterio(String tipo, String diagnostico, String medico, String institucion) {
        List<RegistroMedico> lista = registroMedicoRepository.findAll();

        if (tipo != null && !tipo.isEmpty()) {
            lista = lista.stream()
                    .filter(registro -> registro.getTipo().toString().equalsIgnoreCase(tipo))
                    .collect(Collectors.toList());
        }
        if (diagnostico != null && !diagnostico.isEmpty()) {
            lista = lista.stream()
                    .filter(registro -> registro.getDiagnostico().equalsIgnoreCase(diagnostico))
                    .collect(Collectors.toList());
        }
        if (medico != null && !medico.isEmpty()) {
            lista = lista.stream()
                    .filter(registro -> registro.getMedico().getNombre().equalsIgnoreCase(medico) ||
                            registro.getMedico().getApellido().equalsIgnoreCase(medico))
                    .collect(Collectors.toList());
        }
        if (institucion != null && !institucion.isEmpty()) {
            lista = lista.stream()
                    .filter(registro -> registro.getInstitucion().equalsIgnoreCase(institucion))
                    .collect(Collectors.toList());
        }

        return lista;
    }

}
