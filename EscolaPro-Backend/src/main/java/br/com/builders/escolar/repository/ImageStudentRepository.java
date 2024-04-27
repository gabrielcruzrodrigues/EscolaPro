package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.files.ImagesStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageStudentRepository extends JpaRepository<ImagesStudents, Long> {
}
