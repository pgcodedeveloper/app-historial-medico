package com.nosql.nosql.repositories;

import com.nosql.nosql.models.DatosPaciente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends MongoRepository<DatosPaciente, String>{
    
}
