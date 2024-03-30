package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.student.RequestModules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestModulesRepository extends JpaRepository<RequestModules, Long> {
}
