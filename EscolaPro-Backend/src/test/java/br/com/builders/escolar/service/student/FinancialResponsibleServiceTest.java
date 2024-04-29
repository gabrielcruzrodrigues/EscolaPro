package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.FinancialResponsibleNotFoundException;
import br.com.builders.escolar.exception.customized.StudentNotFoundException;
import br.com.builders.escolar.model.DTO.CreateFinancialResponsibleDTO;
import br.com.builders.escolar.model.DTO.UpdateFinancialResponsibleDTO;
import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.FinancialResponsible;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FinancialResponsibleRepository;
import br.com.builders.escolar.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FinancialResponsibleServiceTest {
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
    private FinancialResponsibleRepository financialResponsibleRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private FinancialResponsibleService financialResponsibleService;

    private CreateFinancialResponsibleDTO createFinancialResponsibleDTO;
    private FinancialResponsible financialResponsible;
    private UpdateFinancialResponsibleDTO updateFinancialResponsibleDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startFinancialResponsible();
    }

    @Test
    @DisplayName("Must receive CreateFinancialResponsibleDTO and create financial responsible with success")
    void createFinancialResponsible_withSuccess() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(new Student()));
        when(financialResponsibleRepository.save(any(FinancialResponsible.class))).thenReturn(new FinancialResponsible());

        financialResponsibleService.createFinancialResponsible(createFinancialResponsibleDTO);

        verify(financialResponsibleRepository).save(any(FinancialResponsible.class));
    }

    @Test
    @DisplayName("Must return StudentNotFoundException")
    void createFinancialResponsible_StudentNotFoundException() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        StudentNotFoundException response = assertThrows(StudentNotFoundException.class, () -> {
            financialResponsibleService.createFinancialResponsible(createFinancialResponsibleDTO);
        }, "Must capture StudentNotFoundException");

        assertEquals(StudentNotFoundException.class, response.getClass());
    }

    @Test
    @DisplayName("Must return a Financial Responsible by id of student")
    void findFinancialResponsibleById_withSuccess() {
        when(financialResponsibleRepository.findById(anyLong())).thenReturn(Optional.of(financialResponsible));

        FinancialResponsible response = financialResponsibleService.findFinancialResponsibleById(1L);

        assertNotNull(response);
        assertTrue(response instanceof FinancialResponsible, "The response must be a FinancialResponsibleInstance.");
        assertEquals(1, response.getId(), "The id of response must be equals 1.");
        assertNotNull(response.getStudent(), "The student of response must not be null.");
    }

    @Test
    @DisplayName("Must return FinancialResponsibleNotFoundException")
    void findFinancialResponsibleById_FinancialResponsibleNotFoundException() {
        when(financialResponsibleRepository.findById(anyLong())).thenReturn(Optional.empty());

        FinancialResponsibleNotFoundException response = assertThrows(FinancialResponsibleNotFoundException.class, () -> {
            financialResponsibleService.findFinancialResponsibleById(1L);
        }, "Must capture FinancialResponsibleNotFoundException");

        assertEquals(FinancialResponsibleNotFoundException.class, response.getClass());
    }

    @Test
    @DisplayName("Must receive UpdateFinancialResponsibleDTO and updated financial responsible with success")
    void updateFinancialResponsible_withSuccess() {
        FinancialResponsible financialResponsible = new FinancialResponsible();
        when(financialResponsibleRepository.findById(anyLong())).thenReturn(Optional.of(financialResponsible));

        financialResponsibleService.updateFinancialResponsible(updateFinancialResponsibleDTO);

        verify(financialResponsibleRepository).save(any(FinancialResponsible.class));
        assertEquals(updateFinancialResponsibleDTO.name(), financialResponsible.getName());
        assertEquals(updateFinancialResponsibleDTO.email(), financialResponsible.getEmail());
        assertEquals(updateFinancialResponsibleDTO.dateOfBirth(), financialResponsible.getDateOfBirth());
        assertEquals(updateFinancialResponsibleDTO.address(), financialResponsible.getAddress());
        assertEquals(updateFinancialResponsibleDTO.workAddress(), financialResponsible.getWorkAddress());
        assertEquals(updateFinancialResponsibleDTO.occupation(), financialResponsible.getOccupation());
        assertEquals(updateFinancialResponsibleDTO.neighborhood(), financialResponsible.getNeighborhood());
        assertEquals(updateFinancialResponsibleDTO.numberHouse(), financialResponsible.getNumberHouse());
        assertEquals(updateFinancialResponsibleDTO.city(), financialResponsible.getCity());
        assertEquals(updateFinancialResponsibleDTO.phone(), financialResponsible.getPhone());
        assertEquals(updateFinancialResponsibleDTO.state(), financialResponsible.getState());
        assertEquals(updateFinancialResponsibleDTO.cep(), financialResponsible.getCep());
        assertEquals(updateFinancialResponsibleDTO.type(), financialResponsible.getType());
    }

    @Test
    @DisplayName("Must return FinancialResponsibleNotFoundException")
    void updateFinancialResponsible_FinancialResponsibleNotFoundException() {
        when(financialResponsibleRepository.findById(anyLong())).thenReturn(Optional.empty());

        FinancialResponsibleNotFoundException response = assertThrows(FinancialResponsibleNotFoundException.class, () -> {
            financialResponsibleService.updateFinancialResponsible(updateFinancialResponsibleDTO);
        }, "Must capture FinancialResponsibleNotFoundException");

        assertEquals(FinancialResponsibleNotFoundException.class, response.getClass());
    }

//    @Test
//    void disabledFinancialResponsible() {
//    }

    void startFinancialResponsible() {
        this.createFinancialResponsibleDTO = new CreateFinancialResponsibleDTO(
                NAME, EMAIL, DATE_OF_BIRTH, IDENTITY, CPF, ADDRESS, WORK_ADDRESS, OCCUPATION, NEIGHBORHOOD, NUMBER_HOUSE,
                CITY, NATIONALITY, NATURALNESS, COUNTRY, PHONE, STATE, CEP, TYPE, null, null, null, ID
        );

        this.financialResponsible = new FinancialResponsible(
                ID, NAME, IDENTITY, CPF, DATE_OF_BIRTH, NATIONALITY, NATURALNESS, EMAIL, CEP, ADDRESS, PHONE, NEIGHBORHOOD, NUMBER_HOUSE,
                CITY, STATE, COUNTRY, CREATED_AT, ACTIVE, WORK_ADDRESS, OCCUPATION, TYPE, null, null, null, new Student()
        );

        this.updateFinancialResponsibleDTO = new UpdateFinancialResponsibleDTO(
                NAME, EMAIL, DATE_OF_BIRTH, IDENTITY, CPF, ADDRESS, WORK_ADDRESS, OCCUPATION, NEIGHBORHOOD, NUMBER_HOUSE, CITY, PHONE,
                STATE, CEP, TYPE, null, null, null, null, ID
        );
    }
}