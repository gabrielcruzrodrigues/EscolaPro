package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.files.FilesStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesStudentRepository extends JpaRepository<FilesStudent, Long> {
}
