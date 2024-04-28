package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.ExceededTheNumberOfFamilyMembersException;
import br.com.builders.escolar.exception.customized.FamilyNotFoundException;
import br.com.builders.escolar.exception.customized.IntegrityDataException;
import br.com.builders.escolar.exception.customized.StudentNotFoundException;
import br.com.builders.escolar.model.DTO.CreateFamilyStudentDTO;
import br.com.builders.escolar.model.DTO.UpdateFamilyDTO;
import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FamilyRepository;
import br.com.builders.escolar.repository.StudentRepository;
import br.com.builders.escolar.service.files.FilesFamilyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FamilyServiceTest {
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
    public static final String WORK_ADDRESS = "Programador";
    public static final String OCCUPATION = "Student";
    public static final FamilyTypeEnum TYPE = FamilyTypeEnum.FATHER;
    public static final long ID = 1L;
    public static final LocalDateTime CREATED_AT = LocalDateTime.now();
    public static final boolean ACTIVE = true;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private FamilyRepository familyRepository;

    @InjectMocks
    private FamilyService familyService;

    private CreateFamilyStudentDTO createFamilyStudentDTO;
    private Family family;
    private UpdateFamilyDTO updateFamilyDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startFamily();
    }

    @Test
    @DisplayName("Must receive CreateFamilyStudentDTO and create a Family with success")
    void createFamilyByStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setFamily(new ArrayList<>(List.of(new Family())));

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(familyRepository.save(any(Family.class))).thenReturn(new Family());

        familyService.createFamilyByStudent(createFamilyStudentDTO);

        verify(familyRepository).save(any(Family.class));
    }

    @Test
    @DisplayName("Must return ExceededTheNumberOfFamilyMembersException")
    void createFamilyByStudent_ExceededTheNumberOfFamilyMembersException() {
        Student student = new Student();
        student.setId(1L);
        student.setFamily(new ArrayList<>(List.of(new Family(), new Family(), new Family())));

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        ExceededTheNumberOfFamilyMembersException response = assertThrows(ExceededTheNumberOfFamilyMembersException.class, () -> {
            familyService.createFamilyByStudent(createFamilyStudentDTO);
        }, "Must capture the ExceededTheNumberOfFamilyMembersException");

        assertEquals(ExceededTheNumberOfFamilyMembersException.class, response.getClass());
    }

    @Test
    @DisplayName("Must return StudentNotFoundException")
    void createFamilyByStudent_StudentNotFoundException() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        StudentNotFoundException response = assertThrows(StudentNotFoundException.class, () -> {
           familyService.createFamilyByStudent(createFamilyStudentDTO);
        }, "Must capture StudentNotFoundException");

        assertEquals(StudentNotFoundException.class, response.getClass());
    }

    @Test
    @DisplayName("Must return a family by id with success")
    void findFamilyById_withSuccess() {
        Student student = new Student();
        student.setId(1L);
        Family family = new Family();
        family.setId(1L);
        family.setStudent(student);

        when(familyRepository.findById(anyLong())).thenReturn(Optional.of(family));

        Family response = familyService.findFamilyById(1L);

        assertNotNull(response, "The response must not be null");
        assertTrue(response instanceof Family, "The response must be a instace of Family.");
        assertEquals(Family.class, response.getClass(), "The response class must be a Family class");
        assertEquals(family.getId(), response.getId(), "The response id must be equals 1L.");
        verify(familyRepository).findById(1L);
        assertEquals(response.getStudent(), response.getStudent(), "The student of Family must be a self in response.");
    }

    @Test
    @DisplayName("Must return FamilyNotFoundException")
    void findFamilyById_FamilyNotFoundException() {
        when(familyRepository.findById(anyLong())).thenReturn(Optional.empty());

        FamilyNotFoundException response = assertThrows(FamilyNotFoundException.class, () -> {
            familyService.findFamilyById(1L);
        }, "Must capture FamilyNotFoundException");

        assertEquals(FamilyNotFoundException.class, response.getClass());
    }

    @Test
    @DisplayName("Must return a list with all family of student with success")
    void findAllFamilyByStudent_withSuccess() {
        Student student = new Student();
        student.setId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(familyRepository.findAllByStudentId(1L)).thenReturn(List.of(new Family()));

        List<Family> response = familyService.findAllFamilyByStudent(1L);

        assertNotNull(response);
        assertTrue(response instanceof List, "The response must be a List.");
        assertTrue(response.get(0) instanceof Family, "The content in response must be a Family class.");
        assertEquals(response.size(), 1, "The size of response must be equals 1");
        verify(studentRepository).findById(1L);
        verify(familyRepository).findAllByStudentId(1L);
    }

    @Test
    @DisplayName("Must return StudentNotFoundException")
    void findAllFamilyByStudent_StudentNotFoundException() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        StudentNotFoundException response = assertThrows(StudentNotFoundException.class, () -> {
           familyService.findAllFamilyByStudent(1L);
        });

        assertEquals(StudentNotFoundException.class, response.getClass());
    }

    @Test
    @DisplayName("Must updated family with success")
    void updateFamily_withSuccess() {
        Family family = new Family();

        when(familyRepository.findById(anyLong())).thenReturn(Optional.of(family));

        familyService.updateFamily(updateFamilyDTO);

        verify(familyRepository).save(any(Family.class));
        assertEquals(updateFamilyDTO.name(), family.getName());
        assertEquals(updateFamilyDTO.email(), family.getEmail());
        assertEquals(updateFamilyDTO.dateOfBirth(), family.getDateOfBirth());
        assertEquals(updateFamilyDTO.address(), family.getAddress());
        assertEquals(updateFamilyDTO.workAddress(), family.getWorkAddress());
        assertEquals(updateFamilyDTO.occupation(), family.getOccupation());
        assertEquals(updateFamilyDTO.neighborhood(), family.getNeighborhood());
        assertEquals(updateFamilyDTO.numberHouse(), family.getNumberHouse());
        assertEquals(updateFamilyDTO.city(), family.getCity());
        assertEquals(updateFamilyDTO.phone(), family.getPhone());
        assertEquals(updateFamilyDTO.state(), family.getState());
        assertEquals(updateFamilyDTO.cep(), family.getCep());
        assertEquals(updateFamilyDTO.type(), family.getType());
    }

    @Test
    @DisplayName("Must return FamilyNotFoundException")
    void updateFamily_FamilyNotFoundException() {
        when(familyRepository.findById(anyLong())).thenReturn(Optional.empty());

        FamilyNotFoundException response = assertThrows(FamilyNotFoundException.class, () -> {
            familyService.updateFamily(updateFamilyDTO);
        }, "Must capture FamilyNotFoundException.");

        assertEquals(FamilyNotFoundException.class, response.getClass());
    }

    @Test
    @DisplayName("Must disabled family with success")
    void disabledFamily_withSuccess() {
        Family family = new Family();
        Student student = new Student();
        student.setFamily(new ArrayList<>(List.of(new Family(), new Family())));

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(new Student()));
        when(familyRepository.findById(anyLong())).thenReturn(Optional.of(new Family()));
        when(familyRepository.save(any(Family.class))).thenReturn(new Family());

        assertFalse(family.isActive(), "The family must be active=false after call method.");
    }

    @Test
    @DisplayName("Must return IntegrityDataException")
    void disabledFamily_IntegrityDataException() {
        Student student = new Student();
        student.setFamily(new ArrayList<>(List.of(new Family())));

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        IntegrityDataException response = assertThrows(IntegrityDataException.class, () -> {
            familyService.disabledFamily(1L, 1L);
        }, "Must capture IntegrityDataException.");

        assertEquals(IntegrityDataException.class, response.getClass());
    }

    void startFamily() {
        this.createFamilyStudentDTO = new CreateFamilyStudentDTO(
            NAME, EMAIL, IDENTITY, CPF, NATIONALITY, NATURALNESS, COUNTRY, DATE_OF_BIRTH, ADDRESS, WORK_ADDRESS, OCCUPATION,
                NEIGHBORHOOD, NUMBER_HOUSE, CITY, PHONE, STATE, CEP, TYPE, null, null, null, ID
        );

        this.family = new Family(ID, NAME, IDENTITY, CPF, DATE_OF_BIRTH, NATIONALITY, NATURALNESS, EMAIL,
                CEP, ADDRESS, PHONE, NEIGHBORHOOD, NUMBER_HOUSE, CITY, STATE, COUNTRY, CREATED_AT, ACTIVE, WORK_ADDRESS,
                OCCUPATION, TYPE, null, null, null, new Student()
        );

        this.updateFamilyDTO = new UpdateFamilyDTO(NAME, EMAIL, DATE_OF_BIRTH, ADDRESS, WORK_ADDRESS, OCCUPATION, NEIGHBORHOOD,
                NUMBER_HOUSE, CITY, PHONE, STATE, CEP, TYPE, ID);
    }
}