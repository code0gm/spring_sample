package com.skplatform.aipd.repository.maria;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.skplatform.aipd.entity.maria.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
