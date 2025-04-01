- [**Sample Code Registry**](https://github.com/nettee-space/backend-sample-code-registry)  
  1. ⠀⠀ [**Layerd**](https://github.com/nettee-space/backend-sample-layered-simple-crud)  
  2. ⠀⠀ [**Hexagonal**](https://github.com/nettee-space/backend-sample-hexagonal-simple-crud)  
  3. ▶ ⠀**Multi-Module Project** (Here)

# Introduction.

이 샘플 프로젝트는 DDD 철학에 걸맞는 헥사고날 아키텍처 기반으로 멀티 모듈로 구현되었습니다.  

\*폴더 및 모듈 네이밍과 구조는 수정되고 있습니다.

```
root
├── .github
│   ├─ ISSUE_TEMPLATE
│   │   ├─ 01. task-list.yml
│   │   ├─ 02. sub-issue.yml
│   │   └─ config.yml
│   └─ ISSUE_TEMPLATE
├── common [:common]
├── core
│   ├── nettee-exception-handler-webmvc [:exception-handler-webmvc] 🏗
│   └── nettee-jpa-core [:jpa-core] 🏗
├── monolith
│   └── main-runner [:main-runner]
└── services
    └── board [:board]
        ├── api [:board:api]
        │  ├── domain [:board:api:domain]
        │  ├── exception [:board:api:exception]
        │  └── readmodel [:board:api:readmodel]
        ├── application  [:board:application]
        ├── driven
        │  └── rdb [:board:rdb-adapter]
        └── driving
            └── web-mvc [:board:webmvc-adapter]
```

## Inter-Module Dependencies

```mermaid
%%{init: {'look': 'handDrawn'}}%%
flowchart TB
    monolith/main-runner --> service-module
    
    subgraph service-module
        direction LR

        board -. includes .-> board-rdb-adapter -. includes .-> board-application
        board -. optional .-> board-application
        board -. includes .-> board-web-adapter -. includes .-> board-application
        
        board-application -. includes .-> api-module

        subgraph api-module
            direction LR
            board-api -. includes .-> board-domain & board-readmodel & board-exception
        end
    end

    service-module -.-> core-modules
    core-modules -.-> common
```

<details>
  <summary>설명 보기</summary>

  - 모든 서브프로젝트에 `:common` 모듈을 의존시킵니다.
  - `:board:api` 모듈은 다음 목록을 통합합니다. 그 외 추가 기능을 제공하지 않습니다.
    - `:board:api:domain`: 도메인 모델을 제공합니다.
    - `:board:api:exception`: 도메인 관련 예외를 제공합니다.
    - `:board:api:readmodel`: 도메인 관련 조회 모델을 제공합니다.
  - `:board:application` 모듈은 헥사고날 아키텍처의 각 방향 포트 인터페이스를 제공합니다.
    - `:board:api` 모듈을 통합합니다.
  - `:board:rdb-adapter` 및 `:board:webmvc-adapter`는 각 포트 인터페이스를 구현하거나 사용하는 어댑터를 제공합니다.
    - `:board:application` 모듈을 통합합니다.
  - 위 보드 관련 구현 소스 및 리소스를 모두 통합하여 `:board` 모듈을 완성합니다.
  - `:board` 모듈을 `:main-runner` 모듈이 통합하고 실행합니다.
  - 각 코어 모듈은 알맞은 모듈에서 취사선택하여 사용합니다.

</details>

# Prerequisites

- **JDK 21**  
  You can use OpenJDK e.g. Amazon Corretto 21
- **Docker**  
  - 도커 버전이 최신 버전이 아니라면, <ins>도커 컴포즈</ins> 또한 필요할 수 있습니다.
  - 윈도우 10 사용자 중 일부는 도커가 동작하지 않을 수 있습니다. (세부 버전이 오래된 버전일 때)
  
# How to Run in a Local Environment

```
1. git clone git@github.com:nettee-space/backend-sample-multi-module.git
2. ./compose-monolith up -d
  (권한 필요 시 chmod +x compose-monolith)
3. Please include 'local' profile to your active profiles.
4. Run MainApplication!
```

# Branch Rule 

개발자들은 다음과 같은 Branch Rule을 꼭 숙지하고 준수해 주시기 바랍니다. (간소화된 브랜치 운영)

- **main 브랜치는 읽기 전용 입니다.**
  - main 브랜치는 관리자([`@merge-simpson`](https://github.com/merge-simpson), [`@silberbullet`](https://github.com/silberbullet))만 force push가 가능합니다.
- **feature 브랜치**: 모든 변경 사항은 <ins>feature 브랜치</ins>를 생성 후, main 브랜치로 병합해야 합니다.
  - `feature/기능명` 양식으로 명명하며, 영문 소문자, 숫자 및 하이픈(케밥 케이스)를 사용합니다. (추가적인 슬래시를 사용하지 않습니다.)
    
    ```mermaid
      gitGraph
      commit
      commit
      branch feature/board-example
      branch feature/board-something
      checkout feature/board-example
      commit
      checkout feature/board-something
      commit
      commit
      checkout feature/board-example
      commit
      checkout main
      merge feature/board-example
      checkout main
      merge feature/board-something
      commit
    ```
  
- **주요 브랜치에 병합 전 Pull Request(PR)는 필수입니다.**
  - Pull Request를 생성할 때, 최소 2명의 reviewer를 지정해야 합니다.
  - 관리자([@merge-simpson](https://github.com/merge-simpson), [@silberbullet](https://github.com/silberbullet))는 리뷰 없이 병합이 가능합니다.
  - **코드에 대한 모든 논의(conversations)가 해결(resolved)되지 않은 상태에서는 Pull Request를 병합할 수 없습니다.**
    <details>
    <summary>conversations 예시 보기</summary>
    
    1. @silberbullet 님이 pull request 생성 후, reviewer를 @merge-simpson 에게 신청하였습니다.  
    2. @merge-simpson 님은 코드 수정을 위해 comment를 남겼습니다.  
    3. @silberbullet 님은 해당 코드를 수정하여 push 후 @merge-simpson 님이 남긴 comment에 수정사항을 적어 놓았습니다.  
    4. @merge-simpson "Resolve conversation" 버튼을 클릭하여 피드백이 해결되었음을 표시합니다.  
    5. 비로소 @silberbullet 님은 코드 병합이 가능합니다.  
    
    </details>

# Commit Message

커밋 메시지의 제1 규칙은 '알아볼 수 있는 메시지 전달'입니다.  
보편적인 앵귤러 커밋 메시지 컨벤션을 따르면서, 각 포맷의 바운더리와 표현 수준은 팀에 맞게 차근차근 조정해 가면 좋겠습니다.

## Basic Commit Message Format

커밋 메시지의 첫 단어는 작업의 목적을 명확히 하기 위해 커밋 타입으로 시작합니다.  

> **type**(scope): subject in lowercase  

아래의 타입을 실습으로 사용해 보시면 좋습니다.

- **feat**: 새로운 기능 추가
- **fix**: 버그 수정
- **docs**: 문서 생성 및 수정 (README.md 등)
- **refactor**: 코드 리팩토링 (기능 변화 없음: 성능 개선, 패키지 이동, 파일·식별자 수정 등)
- **test**: 테스트 코드 추가 또는 수정
- **chore**: 코드의 구조나 동작에 영향을 주지 않는 기타 작업
- **build**: 빌드 관련 작업, 패키지 매니저 설정 등

# Contact.

- [:octocat: Merge Simpson](https://github.com/merge-simpson)
- [:octocat: Silberbullet](https://github.com/silberbullet) (No silver bullet)
