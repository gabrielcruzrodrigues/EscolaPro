package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.student.FixedHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedHealthRepository extends JpaRepository<FixedHealth, Long> {
}
