package com.skplatform.aipd.entity.neo4j;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;

@NodeEntity(label="DRUG")
@Data
public class Drug {

	@Id @GeneratedValue private Long id;
	
	@Property("Name")
	private String name;

	@Property("HigestPhase")
	private String higestPhase;

	@Property("Organizations")
	private String organizations;

}
