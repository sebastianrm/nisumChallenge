package com.srm.nisumChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srm.nisumChallenge.dto.ParentUser;
import java.util.List;


/**
 * Nisum Challenge
 * @author Sebastian Retamal Morales
 * @since 2023-12-15
 * @version 0.0.1-SNAPSHOT
 */
@Repository
public interface UserRepository extends JpaRepository<ParentUser,String>{

	/**
	 * @param email
	 */	
	List<ParentUser> findByEmail(String email);
	

}
