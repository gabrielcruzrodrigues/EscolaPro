package br.com.builders.escolar.service.student;

import br.com.builders.escolar.model.DTO.StudentCreateDataDTO;
import br.com.builders.escolar.model.enums.SexEnum;
import br.com.builders.escolar.model.enums.ShiftEnum;
import br.com.builders.escolar.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    public static final String NAME = "rafael rodrigues";
    public static final String IDENTITY = "0000000000";
    public static final String CPF = "00000000000";
    public static final String BRASILEIRA = "Brasileira";
    public static final String NATURALNESS = "Bahia";
    public static final SexEnum SEX = SexEnum.MASCULINE;
    public static final String EMAIL = "gabriel@gmail.com";
    public static final String CEP = "00000000";
    public static final String ADDRESS = "rua a";
    public static final String PHONE = "73988484841";
    public static final String NEIGHBORHOOD = "mandacaru";
    public static final String CITY = "jequié";
    public static final String STATE = "Bahia";
    public static final String COUNTRY = "Brasil";
    public static final String EMAIL_PERSON_RESPONSIBLE = "responsavel@gmail.com";
    public static final String RESPONSIBLE = "responsável";
    public static final String FATHER = "pai";
    public static final String MOTHER = "mae";
    public static final MultipartFile MULTIPART_FILE = null;
    public static final List<ShiftEnum> SHIFTS = List.of(ShiftEnum.MATUTINO, ShiftEnum.VESPERTINO);
    public static final LocalDate DATE_OF_BIRTH = LocalDate.now(ZoneId.of("2002-01-02"));

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private FilesStudentService filesStudentService;
    @Mock
    private FamilyService familyService;
    @Mock
    private FixedHealthService fixedHealthService;

    private StudentCreateDataDTO studentCreateDataDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.initialSettings();
    }

    public void initialSettings() {
        this.studentCreateDataDTO = new StudentCreateDataDTO(
                NAME,
                IDENTITY,
                CPF,
                DATE_OF_BIRTH,
                BRASILEIRA,
                NATURALNESS,
                SEX,
                EMAIL,
                CEP,
                ADDRESS,
                PHONE,
                NEIGHBORHOOD,
                CITY,
                STATE,
                COUNTRY,
                EMAIL_PERSON_RESPONSIBLE,
                RESPONSIBLE,
                FATHER,
                MOTHER,
                SHIFTS,
                MULTIPART_FILE
        );
    }

    @Test
    @DisplayName("when to call createStudentMATRICULADO should save student with situation MATRICULADO")
    void createStudentMATRICULADO() {
//        when(studentRepository.save(eq(any()))).then();
    }

    @Test
    void createStudentPENDENTE() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllActiveStudents() {
    }

    @Test
    void listStudentsWithSituationMATRICULADO() {
    }

    @Test
    void listStudentsWithSituationPENDENTE() {
    }

    @Test
    void searchStudentByName() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void disableStudent() {
    }

    @Test
    void completeRegistrationOfTheStudentPENDENTE() {
    }
}