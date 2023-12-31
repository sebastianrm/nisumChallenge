package com.srm.nisumChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srm.nisumChallenge.dto.entities.LogUserEntity;

/**
 * Nisum Challenge
 * @author Sebastian Retamal Morales
 * @since 2023-12-15
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public interface LogUserRepository extends JpaRepository<LogUserEntity,String> {

}
