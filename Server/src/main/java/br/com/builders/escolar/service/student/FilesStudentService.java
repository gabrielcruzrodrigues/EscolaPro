package br.com.builders.escolar.service.student;

import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FilesStudentService {

    private final StudentRepository studentRepository;

    public void saveImage(MultipartFile file, Student student) {

    }
}
