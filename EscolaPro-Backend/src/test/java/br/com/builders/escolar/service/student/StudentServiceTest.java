package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.StudentNotFoundException;
import br.com.builders.escolar.model.DTO.StudentCreateDataDTO;
import br.com.builders.escolar.model.enums.SexEnum;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.FinancialResponsible;
import br.com.builders.escolar.model.student.FixedHealth;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class StudentServiceTest {

    public static final String NAME = "João da Silva";
    public static final String IDENTITY = "123456789";
    public static final String CPF = "98765432100";
    public static final LocalDate DATE_OF_BIRTH = LocalDate.of(2005, 5, 15);
    public static final String NATIONALITY = "Brasileira";
    public static final String NATURALNESS = "Rio de Janeiro";
    public static final String EMAIL = "joao.silva@gmail.com";
    public static final String CEP = "20031-170";
    public static final String ADDRESS = "Rua do Exemplo, 123";
    public static final String PHONE = "21987654321";
    public static final String NEIGHBORHOOD = "Centro";
    public static final String NUMBER_HOUSE = "123";
    public static final String CITY = "Rio de Janeiro";
    public static final String STATE = "RJ";
    public static final String COUNTRY = "Brasil";
    public static final SexEnum SEX = SexEnum.MASCULINE;
    public static final SituationsStudentEnum SITUATION = SituationsStudentEnum.MATRICULADO;
    public static final String EMAIL_PERSON_RESPONSIBLE = "responsavel.joao@gmail.com";
    public static final String RESPONSIBLE = "Maria da Silva";
    public static final LocalDateTime CREATED_AT = LocalDateTime.now();
    public static final boolean ACTIVE = true;
    public static final String FATHER = "João";
    public static final String MOTHER = "Maria";
    public static final Long ID = 1L;

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;

    private Student student;
    private StudentCreateDataDTO studentCreateDataDTO;
    private List<Student> studentList;
    private Page<Student> studentPage;
    private Optional<Student> studentOPT;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startStudent();
    }

    @Test
    @DisplayName("Must receive StudentCreateDataDTO, before create and return new student")
    void createStudentMATRICULADO() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student response = studentService.createStudentMATRICULADO(studentCreateDataDTO);

        assertNotNull(response);
        assertTrue(response instanceof Student);
        assertNull(response.getImageProfile(), "The image profile of student must be null");
        assertNull(response.getCpfFile(), "The cpf file of student must be null");
        assertNull(response.getRgFile(), "The rg file of student must be null");
        assertNull(response.getProofOfAddressFile(), "The proof of address file of student must be null");
        assertNotNull(response.getFamily(), "the Family of student must not be null");
        assertNotNull(response.getFixedHealth(), "the FixedHealth of student must not be null");
        assertNotNull(response.getFinancialResponsible(), "the Financial Responsible of student must not be null");
    }

//    @Test
//    void createStudentPENDENTE() {
//    }

    @Test
    @DisplayName("must return student by id with success")
    void findById() {
        Long studentId = 1L;
        when(studentRepository.findById(eq(1L))).thenReturn(this.studentOPT);

        Student response = studentService.findById(studentId);

        assertNotNull(response, "The student should not be null when found by ID.");
        assertTrue(response instanceof Student, "Response must be a Student instance");
        assertEquals(response.getId(), studentId, "The id of student in response must be equals to studentId(1L)");
        assertNotNull(response.getFamily(), "the Family of student must not be null");
        assertNotNull(response.getFixedHealth(), "the FixedHealth of student must not be null");
        assertNotNull(response.getFinancialResponsible(), "the Financial Responsible of student must not be null");
        verify(studentRepository).findById(studentId);
    }

    @Test
    @DisplayName("must return StudentNotFoundException")
    void findByIdWithOptionalEmpty() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        StudentNotFoundException response = assertThrows(StudentNotFoundException.class, () -> {
            studentService.findById(1L);
        });

        assertEquals("User not found.", response.getMessage(), "The message of response must be equal 'User not found'");
        assertEquals(StudentNotFoundException.class, response.getClass());
    }

    @Test
    @DisplayName("Must return all register by students")
    void findAll() {
        when(studentRepository.findAll()).thenReturn(studentList);

        List<Student> response = studentService.findAll();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertTrue(response.get(0) instanceof Student);
        assertNotNull(response.get(0).getFamily(), "the Family of student must not be null");
        assertNotNull(response.get(0).getFixedHealth(), "the FixedHealth of student must not be null");
        assertNotNull(response.get(0).getFinancialResponsible(), "the Financial Responsible of student must not be null");
    }

    @Test
    @DisplayName("Return page with all active students")
    void findAllStudents() {
        when(studentRepository.findAllStudents(anyBoolean(), any(Pageable.class))).thenReturn(studentPage);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());

        Page<Student> response = studentService.findAllStudents(true, pageable);

        assertNotNull(response);
        assertTrue(response instanceof Page, "The response must be a instance of Page");
        assertTrue(response.getContent().get(0) instanceof Student, "The content of response must be a instance of Student");
        assertEquals(response.getContent().size(), 1, "The size of content of response must be equals 1");
        assertNotNull(response.getContent().get(0).getFamily(), "the Family of student must not be null");
        assertNotNull(response.getContent().get(0).getFixedHealth(), "the FixedHealth of student must not be null");
        assertNotNull(response.getContent().get(0).getFinancialResponsible(), "the Financial Responsible of student must not be null");
        verify(studentRepository).findAllStudents(true, pageable);
    }

//    @Test
//    void listStudentsWithSituationMATRICULADO() {
//    }
//
//    @Test
//    void listStudentsWithSituationPENDENTE() {
//    }

    @Test
    @DisplayName("Must return Student by partial name")
    void searchStudentByName() {
        when(studentRepository.searchStudentByName(anyString())).thenReturn(studentList);

        List<Student> response = studentService.searchStudentByName("any");

        assertNotNull(response);
        assertTrue(response instanceof List, "The response must be a instance of List");
        assertTrue(response.get(0) instanceof Student, "The content of list must be a instance Student");
        assertEquals(response.size(), 2, "The size of List in response must be equals 2");
        assertNotNull(response.get(0).getFamily(), "the Family of student must not be null");
        assertNotNull(response.get(0).getFixedHealth(), "the FixedHealth of student must not be null");
        assertNotNull(response.get(0).getFinancialResponsible(), "the Financial Responsible of student must not be null");
        verify(studentRepository).searchStudentByName("any");
    }

    @Test
    void updateStudent() {
    }

    @Test
    @DisplayName("Must disabled student")
    void disableStudent() {
        Long studentId = 1L;
        Student mockStudent = new Student();
        mockStudent.setActive(true);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(mockStudent));

        studentService.disableStudent(studentId);

        assertFalse(mockStudent.isActive(), "The student field active must be false after call method.");
        verify(studentRepository).save(mockStudent);
    }

    @Test
    @DisplayName("Must delete student in database")
    void deleteStudent() {
        Long studentId = 1L;
        Student mockStudent = new Student();
        mockStudent.setId(studentId);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(mockStudent));

        studentService.deleteStudent(studentId);

        verify(studentRepository).delete(mockStudent);
    }

    @Test
    @DisplayName("Must update student for active as true and Situation as MATRICULADO")
    void completeRegistrationOfStudentPENDENTE() {
        Long studentId = 1L;
        Student mockStudent = new Student();
        mockStudent.setActive(false);
        mockStudent.setSituation(SituationsStudentEnum.PENDENTE);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(mockStudent));

        studentService.completeRegistrationOfStudentPENDENTE(studentId);

        assertTrue(mockStudent.isActive(), "Student must be active after completion of registration");
        assertEquals(SituationsStudentEnum.MATRICULADO, mockStudent.getSituation(), "Student situation must be updated to MATRICULADO");
    }

    private void startStudent() {

        //student

        student = new Student(ID, NAME, IDENTITY, CPF, DATE_OF_BIRTH, NATIONALITY, NATURALNESS, EMAIL,
                CEP, ADDRESS, PHONE, NEIGHBORHOOD, NUMBER_HOUSE, CITY, STATE, COUNTRY, CREATED_AT, ACTIVE,
                SEX, SITUATION, EMAIL_PERSON_RESPONSIBLE, RESPONSIBLE, FATHER, MOTHER, null, null,
                null, null, List.of(new Family()), new FixedHealth(), new FinancialResponsible());

        // student list
        studentList = List.of(student, student);

        // student page
        studentPage = new PageImpl<>(Collections.singletonList(student));

        //student OPT

        studentOPT = Optional.of(student);

        // Student Create DataDTO

        StudentCreateDataDTO studentDTO = new StudentCreateDataDTO(
                NAME,
                IDENTITY,
                CPF,
                DATE_OF_BIRTH,
                NATIONALITY,
                NATURALNESS,
                SEX,
                EMAIL,
                CEP,
                ADDRESS,
                PHONE,
                NEIGHBORHOOD,
                NUMBER_HOUSE,
                CITY,
                STATE,
                COUNTRY,
                EMAIL_PERSON_RESPONSIBLE,
                RESPONSIBLE,
                FATHER,
                MOTHER,
                null, // imageProfile
                null, // cpfFile
                null, // rgFile
                null, // proofOfAddress
                SITUATION
        );
        this.studentCreateDataDTO = studentDTO;
    }
}