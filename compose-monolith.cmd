@echo off
setlocal

rem 현재 디렉토리에서 파일 존재 여부 확인
if exist "%cd%\docker-compose-monolith.yml" (
    set "compose_file=docker-compose-monolith.yml"
) else if exist "%cd%\docker-compose-monolith.yaml" (
    set "compose_file=docker-compose-monolith.yaml"
) else (
    echo Error: 이 명령은 프로젝트 루트에서만 실행할 수 있습니다.
    echo docker-compose-monolith.yml 또는 docker-compose-monolith.yaml 파일이 존재해야 합니다.
    exit /b 1
)

rem 전달받은 모든 인자를 docker compose 명령에 전달
docker compose -f %compose_file% %*