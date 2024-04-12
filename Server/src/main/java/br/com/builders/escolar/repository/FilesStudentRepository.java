package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.files.BasicFilesStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilesStudentRepository extends JpaRepository<BasicFilesStudents, Long> {
    Optional<BasicFilesStudents> findByReference(String reference);
}
