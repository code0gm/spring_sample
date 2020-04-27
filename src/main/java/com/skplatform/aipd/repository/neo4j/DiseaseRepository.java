
  package com.skplatform.aipd.repository.neo4j;
  
  import java.util.Collection;
import java.util.UUID; import
  org.springframework.data.jpa.repository.JpaRepository; import
  org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.skplatform.aipd.entity.neo4j.DiseaseEntity;
  
  public interface DiseaseRepository extends Neo4jRepository<DiseaseEntity,
  UUID> {
  }
 