package br.com.builders.escolar.repository;

import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.active = :active")
    List<Student> findAllActiveStudents(@Param("active") boolean active);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:partialName%")
    List<Student> searchStudentByName(@Param("partialName") String partialName);

    @Query("SELECT s FROM Student s WHERE s.situation = :situation")
    List<Student> listStudentBySituation(@Param("situation") SituationsStudentEnum situation);
}
