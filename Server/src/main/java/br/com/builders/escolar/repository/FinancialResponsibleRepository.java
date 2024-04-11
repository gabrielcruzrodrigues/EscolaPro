package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.FinancialResponsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialResponsibleRepository extends JpaRepository<FinancialResponsible, Long> {

    List<FinancialResponsible> findAllByStudentId(Long studentId);
}
