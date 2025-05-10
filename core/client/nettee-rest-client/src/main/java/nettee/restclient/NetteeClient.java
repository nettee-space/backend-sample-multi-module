package nettee.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nettee.client.propeties.ClientProperties;
import nettee.client.request.NetteeRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public final class NetteeClient {
    
    private final RestClient restClient;
    private final ClientProperties clientProperties;
    private final ObjectMapper objectMapper;
    
    public NetteeClient(RestClient restClient, ClientProperties clientProperties, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.clientProperties = clientProperties;
        this.objectMapper = objectMapper;
    }
    
    /**
     * 지정한 도메인의 경로에 대해 GET 요청을 전송하고, 결과를 지정된 타입으로 반환합니다.
     *
     * <p>예외가 발생할 경우 기본 예외를 발생시킵니다.</p>
     *
     * @param request NetteeRequest<T>
     * @param <T>     응답 타입 제네릭
     * @return 응답 객체 (responseType 타입)
     * @throws RuntimeException 4xx 또는 5xx 응답이 발생한 경우
     */
    public <T> T get(NetteeRequest<T> request) {
        return unwrapHandler(execute(restClient.get(), request), request);
    }
    
    /**
     * 지정한 도메인의 경로에 대해 GET 요청을 전송하고, 결과 제너릭 형태로 반홥 받습니다.
     *
     * <p>예외가 발생할 경우 기본 예외를 발생시킵니다.</p>
     *
     * @param request NetteeRequest<T>
     * @param <T>     응답 타입 제네릭
     * @return 응답 객체 (ParameterizedTypeReference 타입)
     * @throws RuntimeException 4xx 또는 5xx 응답이 발생한 경우
     */
    public <T> T getList(NetteeRequest<T> request) {
        return execute(restClient.get(), request).body(new ParameterizedTypeReference<>() {
        });
    }
    
    /**
     * 지정한 도메인의 경로에 대해 POST 요청을 전송하고, 결과를 지정된 타입으로 반환합니다.
     *
     * <p>예외가 발생할 경우 기본 예외를 발생시킵니다.</p>
     *
     * @param request NetteeRequest<T>
     * @param <T>     응답 타입 제네릭
     * @return 응답 객체 (responseType 타입)
     * @throws RuntimeException 4xx 또는 5xx 응답이 발생한 경우
     */
    public <T> T post(NetteeRequest<T> request) {
        return unwrapHandler(execute(restClient.post(), request), request);
    }
    
    /**
     * 지정한 도메인의 경로에 대해 PATCH 요청을 전송하고, 결과를 지정된 타입으로 반환합니다.
     *
     * <p>예외가 발생할 경우 기본 예외를 발생시킵니다.</p>
     *
     * @param request NetteeRequest<T>
     * @param <T>     응답 타입 제네릭
     * @return 응답 객체 (responseType 타입)
     * @throws RuntimeException 4xx 또는 5xx 응답이 발생한 경우
     */
    public <T> T patch(NetteeRequest<T> request) {
        return unwrapHandler(execute(restClient.patch(), request), request);
    }
    
    /**
     * 지정한 도메인의 경로에 대해 DELETE 요청을 전송하고, 결과를 지정된 타입으로 반환합니다.
     *
     * <p>예외가 발생할 경우 기본 예외를 발생시킵니다.</p>
     *
     * @param request NetteeRequest<Void>
     * @return 응답 객체 (ResponseEntity<Void>  타입)
     * @throws RuntimeException 4xx 또는 5xx 응답이 발생한 경우
     */
    public ResponseEntity<Void> delete(NetteeRequest<Void> request) {
        return execute(restClient.delete(), request).toBodilessEntity();
    }
    
    private RestClient.ResponseSpec execute(RestClient.RequestHeadersUriSpec<?> spec, NetteeRequest<?> request) {
        var responseSpec = spec.uri(buildUrl(request.domain(), request.path(), request.uriVariables()));
        
        responseSpec = request.body() == null ? responseSpec : ((RestClient.RequestBodySpec) responseSpec).contentType(APPLICATION_JSON).body(request.body());
        
        return responseSpec.retrieve().onStatus(HttpStatusCode::isError, errorHandler(request));
    }
    
    private RestClient.ResponseSpec.ErrorHandler errorHandler(NetteeRequest<?> request) {
        return (req, res) -> {
            throw new RuntimeException("API 요청 실패: " + res.getStatusCode());
        };
    }
    
    private <T> T unwrapHandler(RestClient.ResponseSpec spec, NetteeRequest<T> request) {
        ResponseEntity<String> entity = spec.toEntity(String.class);
        
        // 본문 읽기
        String jsonBody = entity.getBody();
        
        if (jsonBody != null) {
            try {
                // 래핑된 JSON 형태에 특정 필드 읽기
                JsonNode node = objectMapper.readTree(jsonBody).get(request.unwrapKey());
                
                if (node != null) {
                    // 특정 필드가 있다면 역직렬화로 반환
                    return objectMapper.treeToValue(node, request.responseType());
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        
        return spec.body(request.responseType());
    }
    
    private String buildUrl(String domain, String path, Object... uriVariables) {
        String baseUrl = clientProperties.baseUrl();
        
        if (domain != null && !domain.isEmpty() && clientProperties.url().get(domain) != null) {
            baseUrl = clientProperties.url().get(domain);
        }
        
        Object[] safeUriVariables = uriVariables != null ? uriVariables : new Object[0];
        
        return UriComponentsBuilder.fromUriString(baseUrl).pathSegment(path.split("/")).buildAndExpand(safeUriVariables).toUriString();
    }
}
