package git.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import git.models.Application;



public interface AppRepository extends JpaRepository<Application, Integer> {
	Collection<Application> findByNameContainingIgnoreCase (String name);
} 


