package com.skplatform.aipd.repository.neo4j;

import java.util.UUID;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.skplatform.aipd.entity.neo4j.Drug;

public interface DrugRepository extends Neo4jRepository<Drug, UUID> {
	
}
