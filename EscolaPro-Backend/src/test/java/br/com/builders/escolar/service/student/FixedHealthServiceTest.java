package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.FixedHealthNotFoundException;
import br.com.builders.escolar.exception.customized.StudentNotFoundException;
import br.com.builders.escolar.model.DTO.CreateFixedHealthDTO;
import br.com.builders.escolar.model.DTO.UpdateFixedHealthDTO;
import br.com.builders.escolar.model.student.FinancialResponsible;
import br.com.builders.escolar.model.student.FixedHealth;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FixedHealthRepository;
import br.com.builders.escolar.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FixedHealthServiceTest {

    public static final long ID = 1L;
    public static final List<String> ALLERGIES = List.of("Poeira");
    public static final String HEALTH_INSURANCE = "Plano A";
    public static final String BLOOD_GROUP = "O+";
    public static final String MEDICAL_CLINIC = "Clinica A";
    public static final Long QUANTITY_BROTHERS = 3L;
    public static final boolean TO_GO_OUT_AUTHORIZATION = true;
    public static final boolean ACTIVE = true;
    public static final LocalDateTime CREATED_AT = LocalDateTime.now();
    public static final Student STUDENT = new Student();
    @Mock
    private FixedHealthRepository fixedHealthRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private FixedHealthService fixedHealthService;

    private CreateFixedHealthDTO createFixedHealthDTO;
    private UpdateFixedHealthDTO updateFixedHealthDTO;
    private FixedHealth fixedHealth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startFixedHealth();
    }

    @Test
    @DisplayName("Must receive CreateFixedHealthDTO and create new fixedHealth with success")
    void createFixedHealthByStudent_withSuccess() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(new Student()));
        when(fixedHealthRepository.save(any(FixedHealth.class))).thenReturn(new FixedHealth());

        fixedHealthService.createFixedHealthByStudent(createFixedHealthDTO);

        verify(fixedHealthRepository).save(any(FixedHealth.class));
    }

    @Test
    @DisplayName("Must return StudentNotFoundException")
    void createFixedHealthByStudent_StudentNotFoundException() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        StudentNotFoundException response = assertThrows(StudentNotFoundException.class, () -> {
            fixedHealthService.createFixedHealthByStudent(createFixedHealthDTO);
        }, "Must capture StudentNotFoundException");

        assertEquals(StudentNotFoundException.class, response.getClass());
    }

    @Test
    @DisplayName("Must return a fixedHealth by id of student with success")
    void findFixedHealthById_withSuccess() {
        when(fixedHealthRepository.findById(anyLong())).thenReturn(Optional.of(fixedHealth));

        FixedHealth response = fixedHealthService.findFixedHealthById(1L);

        assertNotNull(response);
        assertTrue(response instanceof FixedHealth, "The response must be a fixedHealth Instance.");
        assertEquals(fixedHealth.getId(), response.getId(), "The id of response must be equals 1.");
        assertNotNull(response.getStudent(), "The student of response must not be null.");
    }

    @Test
    @DisplayName("Must return FixedHealthNotFoundException")
    void findFixedHealthById_findFixedHealthById_withSuccess() {
        when(fixedHealthRepository.findById(anyLong())).thenReturn(Optional.empty());

        FixedHealthNotFoundException response = assertThrows(FixedHealthNotFoundException.class, () -> {
            fixedHealthService.findFixedHealthById(1L);
        }, "Must capture FixedHealthNotFoundException");

        assertEquals(FixedHealthNotFoundException.class, response.getClass());
    }

//    @Test
//    void findAllFixedHealthByStudent() {
//    }

    @Test
    @DisplayName("Must receive a UpdateFixedHealthDTO and updated fixedHealth with success")
    void updateFixedHealth_withSuccess() {
        FixedHealth fixedHealth = new FixedHealth();
        when(fixedHealthRepository.findById(anyLong())).thenReturn(Optional.of(fixedHealth));

        fixedHealthService.updateFixedHealth(updateFixedHealthDTO);

        verify(fixedHealthRepository).save(any(FixedHealth.class));
        assertEquals(updateFixedHealthDTO.allergies(), fixedHealth.getAllergies());
        assertEquals(updateFixedHealthDTO.healthInsurance(), fixedHealth.getHealthInsurance());
        assertEquals(updateFixedHealthDTO.bloodGroup(), fixedHealth.getBloodGroup());
        assertEquals(updateFixedHealthDTO.medicalClinic(), fixedHealth.getMedicalClinic());
        assertEquals(updateFixedHealthDTO.quantityBrothers(), fixedHealth.getQuantityBrothers());
        assertEquals(updateFixedHealthDTO.toGoOutAuthorization(), fixedHealth.isToGoOutAuthorization());
    }

    @Test
    @DisplayName("Must return FixedHealthNotFoundException")
    void updateFixedHealth_FixedHealthNotFoundException() {
        when(fixedHealthRepository.findById(anyLong())).thenReturn(Optional.empty());

        FixedHealthNotFoundException response = assertThrows(FixedHealthNotFoundException.class, () -> {
            fixedHealthService.updateFixedHealth(updateFixedHealthDTO);
        }, "Must capture FixedHealthNotFoundException");

        assertEquals(FixedHealthNotFoundException.class, response.getClass());
    }

    void startFixedHealth() {
        this.fixedHealth = new FixedHealth(
                ID, ALLERGIES, HEALTH_INSURANCE, BLOOD_GROUP, MEDICAL_CLINIC, QUANTITY_BROTHERS, TO_GO_OUT_AUTHORIZATION, ACTIVE, CREATED_AT, STUDENT
        );

        this.createFixedHealthDTO = new CreateFixedHealthDTO(
                ALLERGIES, HEALTH_INSURANCE, BLOOD_GROUP, MEDICAL_CLINIC, QUANTITY_BROTHERS, TO_GO_OUT_AUTHORIZATION, ID
        );

        this.updateFixedHealthDTO = new UpdateFixedHealthDTO(
                ALLERGIES, HEALTH_INSURANCE, BLOOD_GROUP, MEDICAL_CLINIC, QUANTITY_BROTHERS, TO_GO_OUT_AUTHORIZATION, ACTIVE, CREATED_AT, ID
        );
    }
}