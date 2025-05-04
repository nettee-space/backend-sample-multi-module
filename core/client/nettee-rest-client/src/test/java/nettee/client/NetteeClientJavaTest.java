package nettee.client;

import nettee.NetteeClient;
import nettee.config.RestClientConfig;
import nettee.student.entity.Student;
import nettee.student.persistence.StudentRepository;
import netttee.request.NetteeRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(RestClientConfig.class)
public class NetteeClientJavaTest {
    
    @Autowired
    private StudentRepository repository;
    
    @Nested
    @DisplayName("[HTTP GET 요청] 학생 조회")
    @Order(1)
    class GetStudent {
        
        @Test
        @DisplayName("학생 목록 조회 요청 할 때")
        void shouldReturnStudentList() {
            var result = NetteeClient.getList(NetteeRequest.<List<Student>>builder()
                    .domain("student")
                    .path("/api/v1/student")
                    .build());
            
            assertThat(result).hasSize(10);
        }
        
        @Test
        @DisplayName("학생 단건 조회 요청 할 때")
        void shouldReturnSingleStudent() {
            var result = NetteeClient.get(NetteeRequest.<Student>builder()
                    .domain("student")
                    .path("/api/v1/student/{id}")
                    .responseType(Student.class)
                    .uriVariables(new Object[]{5L})
                    .build());
            
            assertThat(result.id).isEqualTo(5L);
        }
    }
    
    @Nested
    @DisplayName("[HTTP POST 요청] 학생 추가")
    @Order(2)
    class PostStudent {
        
        @Test
        @DisplayName("학생 생성 요청 할 때")
        void shouldAddStudent() {
            var result = NetteeClient.post(NetteeRequest.<Student>builder()
                    .domain("student")
                    .path("/api/v1/student")
                    .responseType(Student.class)
                    .build());
            
            assertThat(result.id).isEqualTo(11L);
        }
    }
    
    @BeforeEach
    void setUp() {
        for (int i = 0; i < 10; i++) {
            repository.save(new Student());
        }
    }
    
    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }
}
