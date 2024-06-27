| 상위 패키지명 | 내용                                                    | 타입           |
|--------------|---------------------------------------------------------|----------------|
| src/activator | Application 기동/종료 등의 생성 주기 이벤트를 감지하고 처리하는 패키지 | -              |
| src/config    | Application에 필요한 설정 파일 패키지                         | -              |
| src/domain    | DB, 단일 테이블에 대한 CRUD만 수행하는 패키지 (JPA 사용)   | 단일 Function  |
| src/interfaces | Application Interface 수행하는 패키지                     | 단일 Function  |
| src/message   | 외부로부터 Interface를 수신하여 로직을 처리하는 클래스들의 패키지 (발행 시스템 별, 이벤트명) | Multi Function |
| src/query     | 다중 테이블에 대한 쿼리를 수행하는 클래스들의 패키지 (Mybatis 사용) | 단일 Function |
| src/util      | Application 전역으로 사용하는 Utility 성격 클래스들의 패키지 | -              |
