package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.student.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
}
