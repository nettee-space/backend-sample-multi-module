- [**Sample Code Registry**](https://github.com/nettee-space/backend-sample-code-registry)  
  1. ⠀⠀ [**Layerd**](https://github.com/nettee-space/backend-sample-layered-simple-crud)  
  2. ⠀⠀ [**Hexagonal**](https://github.com/nettee-space/backend-sample-hexagonal-simple-crud)  
  3. ▶ ⠀**Multi-Module Project** (Here)

# Introduction.

이 샘플 프로젝트는 DDD 철학에 걸맞는 헥사고날 아키텍처 기반으로 멀티 모듈로 구현되었습니다.

```
me.nettee
├─.github
│  └─ISSUE_TEMPLATE
├─common
├─core
│  ├─exception-handler-core
│  └─jpa-core
├─monolith
│  └─main-runner
└─services
    └─board
        ├─api
        │  ├─domain
        │  ├─exception
        │  └─readmodel
        ├─application
        ├─driven
        │  └─rdb
        ├─driving
           └─web-mvc
```
# Prerequisites

- JDK 21  
  You can use OpenJDK e.g. Amazon Corretto 21
  
# How to run in a local environment
```
1. git clone git@github.com:nettee-space/backend-sample-multi-module.git
2. docker compose -f docker-compose-monolith.yml up -d
3. Please set your profile to 'Local'
4. MainApplication Run!
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
