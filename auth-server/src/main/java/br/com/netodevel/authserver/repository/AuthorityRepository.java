package br.com.netodevel.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.netodevel.authserver.model.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, String>{
	
	Authority findByName(String name);
}