package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.files.BasicFilesFinancialResponsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilesFinancialResponsibleRepository extends JpaRepository<BasicFilesFinancialResponsible, Long> {
    Optional<BasicFilesFinancialResponsible> findByReference(String reference);
}
