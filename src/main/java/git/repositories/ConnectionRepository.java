package git.repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import git.models.Application;
import git.models.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Integer>{
	
	Collection<Connection> findByNameContainingIgnoreCase(String name);
	Collection<Connection> findByProject(String project);
	Collection<Connection> findByOwner(String owner);
	Collection<Connection> findByApplication(Application a);
}
