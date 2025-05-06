package netttee.client.request;

import lombok.Builder;
import nettee.common.CustomException;

import java.util.Objects;

/**
 * HTTP 공통 요청 레코드
 *
 * @param domain          요청할 도메인 키 (예: "board", "auth")
 * @param path            요청할 경로 (예: "comments/{id}")
 * @param responseType    응답을 매핑할 타입 클래스
 * @param customException 상태코드가 에러일 경우 던질 사용자 정의 예외 (null 허용)
 * @param uriVariables    URI 경로 변수 (예: {id}에 들어갈 값들)
 * @param <T>             응답 타입 제네릭
 */
@Builder
public record NetteeRequest<T>(
        String domain,
        String path,
        Class<T> responseType,
        CustomException customException,
        Object... uriVariables
) {
    public NetteeRequest {
        Objects.requireNonNull(domain, "domain is required");
        Objects.requireNonNull(path, "path is required");
        
        domain = domain.strip();
        path = path.strip();
    }
}
