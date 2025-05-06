package nettee.restclient;

import nettee.common.CustomException;
import netttee.client.propeties.ClientProperties;
import netttee.client.request.NetteeRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

public final class NetteeClient {
    
    private static RestClient restClient;
    private static ClientProperties clientProperties;
    
    private NetteeClient() {
    }
    
    public static void init(RestClient restClient, ClientProperties clientProperties) {
        NetteeClient.restClient = restClient;
        NetteeClient.clientProperties = clientProperties;
    }
    
    /**
     * 지정한 도메인의 경로에 대해 GET 요청을 전송하고, 결과를 지정된 타입으로 반환합니다.
     *
     * <p>예외가 발생할 경우 전달된 CustomException을 던지며, 전달되지 않은 경우 기본 예외를 발생시킵니다.</p>
     *
     * @param request NetteeRequest<T>
     * @param <T>     응답 타입 제네릭
     * @return 응답 객체 (responseType 타입)
     * @throws CustomException 4xx 또는 5xx 응답이 발생한 경우
     */
    public static <T> T get(NetteeRequest<T> request) {
        return execute(restClient.get(), request)
                .body(request.responseType());
    }
    
    /**
     * 지정한 도메인의 경로에 대해 GET 요청을 전송하고, 결과 제너릭 형태로 반홥 받습니다.
     *
     * <p>예외가 발생할 경우 전달된 CustomException을 던지며, 전달되지 않은 경우 기본 예외를 발생시킵니다.</p>
     *
     * @param request NetteeRequest<T>
     * @param <T>     응답 타입 제네릭
     * @return 응답 객체 (ParameterizedTypeReference 타입)
     * @throws CustomException 4xx 또는 5xx 응답이 발생한 경우
     */
    public static <T> T getList(NetteeRequest<T> request) {
        return execute(restClient.get(), request)
                .body(new ParameterizedTypeReference<>() {});
    }
    
    /**
     * 지정한 도메인의 경로에 대해 POST 요청을 전송하고, 결과를 지정된 타입으로 반환합니다.
     *
     * <p>예외가 발생할 경우 전달된 CustomException을 던지며, 전달되지 않은 경우 기본 예외를 발생시킵니다.</p>
     *
     * @param request NetteeRequest<T>
     * @param <T>     응답 타입 제네릭
     * @return 응답 객체 (responseType 타입)
     * @throws CustomException 4xx 또는 5xx 응답이 발생한 경우
     */
    public static <T> T post(NetteeRequest<T> request) {
        return execute(restClient.post(), request)
                .body(request.responseType());
    }
    
    /**
     * 지정한 도메인의 경로에 대해 PATCH 요청을 전송하고, 결과를 지정된 타입으로 반환합니다.
     *
     * <p>예외가 발생할 경우 전달된 CustomException을 던지며, 전달되지 않은 경우 기본 예외를 발생시킵니다.</p>
     *
     * @param request NetteeRequest<T>
     * @param <T>     응답 타입 제네릭
     * @return 응답 객체 (responseType 타입)
     * @throws CustomException 4xx 또는 5xx 응답이 발생한 경우
     */
    public static <T> T patch(NetteeRequest<T> request) {
        return execute(restClient.patch(), request)
                .body(request.responseType());
    }
    
    /**
     * 지정한 도메인의 경로에 대해 DELETE 요청을 전송하고, 결과를 지정된 타입으로 반환합니다.
     *
     * <p>예외가 발생할 경우 전달된 CustomException을 던지며, 전달되지 않은 경우 기본 예외를 발생시킵니다.</p>
     *
     * @param request NetteeRequest<Void>
     * @return 응답 객체 (ResponseEntity<Void>  타입)
     * @throws CustomException 4xx 또는 5xx 응답이 발생한 경우
     */
    public static ResponseEntity<Void> delete(NetteeRequest<Void> request) {
        return execute(restClient.delete(), request)
                .toBodilessEntity();
    }
    
    private static RestClient.ResponseSpec execute(RestClient.RequestHeadersUriSpec<?> spec, NetteeRequest<?> request) {
        return spec.uri(buildUrl(request.domain(), request.path(), request.uriVariables()))
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    if (request.customException() != null) {
                        throw request.customException();
                    } else {
                        // TODO CustomException DefaultErrorCod 추가
                        throw new RuntimeException("API 요청 실패: " + res.getStatusCode());
                    }
                });
    }
    
    private static String buildUrl(String domain, String path, Object... uriVariables) {
        String baseUrl = clientProperties.baseUrl();
        
        if (domain != null && !domain.isEmpty()) {
            baseUrl = clientProperties.url().get(domain);
        }
        
        Object[] safeUriVariables = uriVariables != null ? uriVariables : new Object[0];
        
        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .pathSegment(path.split("/"))
                .buildAndExpand(safeUriVariables)
                .toUriString();
    }
}
