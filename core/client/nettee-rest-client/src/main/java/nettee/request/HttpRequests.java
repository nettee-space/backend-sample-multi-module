package nettee.request;

import lombok.Builder;
import nettee.common.CustomException;

import java.util.Objects;

public final class HttpRequests {
    /**
     * HTTP GET 요청 레코드
     *
     * @param domain          요청할 도메인 키 (예: "board", "auth")
     * @param path            요청할 경로 (예: "comments/{id}")
     * @param responseType    응답을 매핑할 타입 클래스
     * @param customException 상태코드가 에러일 경우 던질 사용자 정의 예외 (null 허용)
     * @param uriVariables    URI 경로 변수 (예: {id}에 들어갈 값들)
     * @param <T>             응답 타입 제네릭
     */
    @Builder
    public record GetRequest<T>(
            String domain,
            String path,
            Class<T> responseType,
            CustomException customException,
            Object... uriVariables
    ) {
        public GetRequest {
            Objects.requireNonNull(domain, "domain is required");
            Objects.requireNonNull(path, "path is required");
            
            domain = domain.strip();
            path = path.strip();
        }
    }
    
    /**
     * HTTP POST 요청 레코드
     *
     * @param domain          요청할 도메인 키 (예: "board", "auth")
     * @param path            요청할 경로 (예: "comments/{id}")
     * @param responseType    응답을 매핑할 타입 클래스
     * @param customException 상태코드가 에러일 경우 던질 사용자 정의 예외 (null 허용)
     * @param uriVariables    URI 경로 변수 (예: {id}에 들어갈 값들)
     * @param <T>             응답 타입 제네릭
     */
    @Builder
    public record PostRequest<T>(
            String domain,
            String path,
            Class<T> responseType,
            CustomException customException,
            Object... uriVariables
    ) {
        public PostRequest {
            Objects.requireNonNull(domain, "domain is required");
            Objects.requireNonNull(path, "path is required");
            
            domain = domain.strip();
            path = path.strip();
        }
    }
    
    /**
     * HTTP PATCH 요청 레코드
     *
     * @param domain          요청할 도메인 키 (예: "board", "auth")
     * @param path            요청할 경로 (예: "comments/{id}")
     * @param responseType    응답을 매핑할 타입 클래스
     * @param customException 상태코드가 에러일 경우 던질 사용자 정의 예외 (null 허용)
     * @param uriVariables    URI 경로 변수 (예: {id}에 들어갈 값들)
     * @param <T>             응답 타입 제네릭
     */
    public record PatchRequest<T>(
            String domain,
            String path,
            Class<T> responseType,
            CustomException customException,
            Object... uriVariables
    ) {
        public PatchRequest {
            Objects.requireNonNull(domain, "domain is required");
            Objects.requireNonNull(path, "path is required");
            
            domain = domain.strip();
            path = path.strip();
        }
    }
    
    /**
     * HTTP DELETE 요청 레코드
     *
     * @param domain          요청할 도메인 키 (예: "board", "auth")
     * @param path            요청할 경로 (예: "comments/{id}")
     * @param customException 상태코드가 에러일 경우 던질 사용자 정의 예외 (null 허용)
     * @param uriVariables    URI 경로 변수 (예: {id}에 들어갈 값들)
     */
    @Builder
    public record DeleteRequest(
            String domain,
            String path,
            CustomException customException,
            Object... uriVariables
    ) {
        public DeleteRequest {
            Objects.requireNonNull(domain, "domain is required");
            Objects.requireNonNull(path, "path is required");
            
            domain = domain.strip();
            path = path.strip();
        }
    }
}
