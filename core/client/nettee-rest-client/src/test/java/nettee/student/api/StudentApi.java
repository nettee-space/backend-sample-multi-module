package nettee.student.api;

import lombok.RequiredArgsConstructor;
import nettee.student.entity.Student;
import nettee.student.persistence.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentApi {
    
    private final StudentRepository repository;
    
    @GetMapping
    public List<Student> getStudentList(){
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable long id){
        return repository.findById(id).orElse(null);
    }
    
    @PostMapping
    public Student addStudent(){
        return repository.save(new Student());
    }
}
