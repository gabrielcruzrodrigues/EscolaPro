package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.active = :active")
    Page<Student> findAllStudents(@Param("active") boolean active, Pageable pageable);

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE %:partialName% AND s.active = true")
    List<Student> searchStudentByName(@Param("partialName") String partialName);

    @Query("SELECT s FROM Student s WHERE s.situation = :situation")
    List<Student> listStudentBySituation(@Param("situation") SituationsStudentEnum situation);
}
