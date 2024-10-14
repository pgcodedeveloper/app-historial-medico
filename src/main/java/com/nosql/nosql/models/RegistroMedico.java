package com.nosql.nosql.models;

import com.nosql.nosql.enums.TipoRegistroMedico;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Registro_Medico")
public class RegistroMedico {
    
    @Id
    private String id;
    private String fecha; // Fecha del registro médico
    private TipoRegistroMedico tipo;  // Tipo: Consulta, Examen, Internación
    private String diagnostico;  // Diagnóstico
    private Medico medico;  // Información del médico (nombre y apellido)
    private String institucion;  // Institución donde se realizó el registro
    private String descripcion;  // Descripción opcional
    @DBRef
    private DatosPaciente paciente; //Cedula del paciente
    private List<String> medicaciones;  // Medicación opcional

    @CreatedDate
    private Date createdAt; // Campo para almacenar la fecha de creación

    public RegistroMedico(String fecha, TipoRegistroMedico tipo, String diagnostico, Medico medico, String institucion) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.diagnostico = diagnostico;
        this.medico = medico;
        this.institucion = institucion;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public TipoRegistroMedico getTipo() {
        return tipo;
    }

    public void setTipo(TipoRegistroMedico tipo) {
        this.tipo = tipo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getMedicaciones() {
        return medicaciones;
    }

    public void setMedicaciones(List<String> medicaciones) {
        this.medicaciones = medicaciones;
    }

    public DatosPaciente getPaciente() {
        return paciente;
    }

    public void setPaciente(DatosPaciente paciente) {
        this.paciente = paciente;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
