package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.FinancialResponsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialResponsibleRepository extends JpaRepository<FinancialResponsible, Long> {

    List<FinancialResponsible> findAllByStudentId(Long studentId);

}
