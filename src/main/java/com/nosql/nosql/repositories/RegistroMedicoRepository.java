package com.nosql.nosql.repositories;

import com.nosql.nosql.models.RegistroMedico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroMedicoRepository extends MongoRepository<RegistroMedico, String> {
    Page<RegistroMedico> findByPacienteCI(String CI, Pageable pageable);
}
