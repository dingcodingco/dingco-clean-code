# dingco-clean-code

[딩코랩스] 개발자 취업 필수 개념 — **1주차 클린 코드** 강의의 예제 코드입니다.
의미 있는 이름 · 함수 · 예외 · 리팩토링 원칙을 실제 코드로 익혀봅니다.

## 환경
- JDK 21
- Gradle (Wrapper 포함) · JUnit 5

## 빌드 & 테스트
```bash
./gradlew clean build      # 컴파일 + 전체 테스트
./gradlew test             # 테스트만
```
> JDK 21 이 기본이 아니라면: `./gradlew build -Dorg.gradle.java.home=/path/to/jdk-21`

## 구성

| 패키지 | 주제 | 핵심 클래스 |
|--------|------|-------------|
| `s03_names` | 03. 의미 있는 이름 | `AdultFinder`, `BusRiding` |
| `s05_functions2` | 05. 함수 - 부수 효과 | `PureFunctionVsSideEffect` |
| `s06_exception` | 06. 예외 | `ExceptionBasics`, `NetflixVideo` |
| `s07_08_refactoring` | 07~08. 리팩토링 (만화방) | `ComicRentalStore` |
| `hw_bowling` | HW. 볼링 점수 | `BowlingScore` |
| `week01` | 02·04 IDE/리팩토링 시연 | `IntentionsClear`, `CommuteDemo`, `KimbobDemo` |

## 검증된 동작
- **만화방** `ComicRentalStore` — 의정부고 총액 245000원/43점, 의여고 145000원/52점
- **볼링 점수** `BowlingScore` — `"9-8P72S9P-9S-P9-SS8"` → 150, `"SSSSSSSSSSSS"` → 300
- **순수 함수 vs 부수 효과** — 순수 함수는 상태 불변, 부수 효과 함수는 상태 변경
- 8개 JUnit 테스트 전부 통과

> `CommuteDemo` / `KimbobDemo` 는 §04 Extract Method 시연용으로 가상 도메인 stub 을 사용합니다.

## 강의 화면
`images/` 폴더에 IntelliJ 실행·리팩토링 화면이 들어 있습니다.
