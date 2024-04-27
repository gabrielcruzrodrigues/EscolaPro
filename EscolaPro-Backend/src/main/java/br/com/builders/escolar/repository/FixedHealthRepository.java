package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.student.FixedHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FixedHealthRepository extends JpaRepository<FixedHealth, Long> {
    List<FixedHealth> findAllByStudentId(Long studentId);
}
