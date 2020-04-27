package com.skplatform.aipd.repository.primary;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.skplatform.aipd.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
