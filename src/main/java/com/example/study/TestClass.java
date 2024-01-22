package com.example.study;

public class TestClass {
    /**
     *  스프링 부트 구조
     *     서론
     *     객체지향적인 폴더 구조를 설계하기 위한 관련 개념들을 정리하는 내용입니다.
     *     패키지 구조에는 정답은 없으며 필자는 유지보수와 인수인계라는 키워드를 중심으로 코드를 모르는 사람이 보아도 쉽게 이해할 수 있는 패키지 구조를 설계하는 것이 목표이다.
     *     모르는 사람이 보아도 쉽게 이해할 수 있는 패키지구조를 설계하는 것이 목표이다.
     *     MVC 패턴
     *     패키지 구조를 알기 위해서는 기본적으로 MVC 패턴을 이해해야한다.
     *     MVC는 프로젝트를 구성할 때 Model,View,Controller의 역할로 구분하는 패턴입니다.
     *
     *     Model
     *     애플리케이션의 정보, 데이터를 나타냅니다. 데이터베이스, 처음의 정의하는 상수, 초기화값 변수 등을 의미합니다.
     *     추가로 이러한 데이터를 피싱하거나 데이터베이스와의 통신 또한 책임지는 컴포넌트(비지니스 로직)입니다.
     *     모델의 규칙
     *      1. 사용자가 편집하길 원하는 모든 데이터를 가지고 있어야 한다.
     *      2. 뷰나 컨트롤러에 대해서 어떤 정보도 알지 말아야 한다.
     *      3. 변경이 일어나면, 변경 통지에 대한 처리방법을 구현해야만 한다.
     *
     *      View
     *      사용자가 보는 화면, 즉 input 텍스트, 체크박스 항복 등과 같은 사용자 인터페이스 요소를 의미한다.
     *      데이터를 기반으로 사용자들이 볼 수 있는 화면이다.
     *      1)뷰의 규칙
     *       1. 모델이 가지고 있는 정보를 따로 저장해서는 안된다.
     *       2. 모델이나 컨트롤러와 같이 다른 구성요소를 몰라야 한다.
     *       3. 변경이 일어나면 변경 통지에 대한 처리방법을 구현해야만 한다.
     *      Controller
     *      Model과 View의 중간다리 역할을 한다.
     *      즉, 사용자가 데이터를 클릭하고, 수정하는 것에 대한 "이벤트"들을 처리하는 부분을 의미한다.
     *      1) 컨트롤러의 규칙
     *       1. 모델이나 뷰에 대해서 알고 있어야 한다.
     *       2. 모델이나 뷰의 변경을 모니터링 해야 한다.
     *
     *     Spring
     *     용어 정리
     *     DAO(Data Access Object)는 데이터 베이스에 접근하기 위한 객체이다. DataBase에 접근한기 위한 로직 & 비즈니스 로직을 분리하기 위해 사용
     *     DTO(Data Transfer Object)는 계층 간 데이터 교환을 하기 위해 사용하는 객체로, DTO는 로직을 가지지 않는 순수한 데이터 객체(getter & setter 만 가진 클래스) 이다.
     *     VO(Value Object)는 값 오브젝트로써 값을 위해 쓰입니다. read-Only 특징(사용하는 도중에 변경 불가능하고, 오직 읽기만 가능) 을 가지고 있다.
     *     DTO와 유사하지만 VO는 getter만 가진 클래스
     *
     *     Spring web Layer
     *     스프링에는 5가지 요소가 존재한다.
     *     1. Web Layer
     *      - 컨트롤러(@Controller)가 대표적이고, 이외에도 필터(@filter), 인터셉터, 컨트롤러 어드바이스 등이 포함된다.
     *      - 외부 요청과 응답에 대한 전반적인 영역을 의미한다.
     *     2. Service Layer
     *      - 말 그대로 서비스(@Service)이다.
     *      - 일반적으로 컨트롤러와 저장소(Repository, Dao)의 중간에 위치
     *      - 트랜잭션(@Transactional)과 도메인 간의 연산 순서를 보장해 준다.
     *
     *     3. Repository Layer
     *      - DB와 같은 데이터 저장소에 접근하는 영역
     *      - JPA를 사용한다면 @Repository를 생각하면 된다.
     *      - DAO라고도 생각한다.
     *
     *     4. DTOs
     *      - DTO(Data Transfer Object)는 계층 간의 데이터 교환을 위한 객체를 이야기 한다.
     *
     *     5. Domain Model
     *      - 개발 대상, 즉 도메인을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화 한 것을 도메인 모델이라고 한다.
     *      - 비즈니스 로직 처리
     *      - JPA를 사용한다면, @Entity가 사용되는 영역 또한 도메인 영역
     *
     *     간단하게 동작과정을 설명하고 넘어가자면
     *      1. Client(View)에서 DTO를 통해 요청을 보낸다.
     *      2. DTO를 통해 Controller에 도착하면 Controller에서는 Service(비즈니스 로직)을 호출한다. 이때 역시 DTO를 통해 전달
     *      - ex : 어떤 Service(비즈니스 로직)으로 갈지 조합한다는 느낌?
     *      3. Service로 오면 도착한 Service의 비즈니스 로직을 처리한다.
     *      4. Service는 비즈니스 로직을 실행하는 도중 DB에 접근해야 하는 경우 Repository로 이동하여 접근을 한다.
     *      5. Repository는 DB에 접근하는 소스코드이 존재하여 DB에 접근하고 쿼리문을 통한 트랜잭션 실행할 경우 Domain을 호출한다,
     *      6. Domain은 DB 테이블과 직접 매핑되는 비즈니스 로직들이 존재하는 것으로 JPA를 사용하면 @Entity가 선언된다.
     *
     *     Spring MVC
     *     MVC가 조금 더 포괄적인 개념이라 생각한다.
     *     고로 MVC안에 Spring web Layer를 넣어보면
     *     Model : Service, DTO, Repository, Domain
     *     View : Front - end
     *     Controller : Controller
     *
     *     계층형 구조, 도메인형 구조
     *     기본적인 틀은 레이어 계층형과 도메인형으로 구성되어 있따고 생각합니다.
     *     계층형 구조는 각 계층을 대표하는 디렉터리를 기준으로 코드를 구성하는 것이다.
     *      - 장점 : 프로젝트의 이해도가 낮아도 전체적인 구조 파악을 빠르게 할 수 있따.
     *      - 단점 : 디렉터리 안에 클래스들이 너무 모인다,
     *
     *     도메인 디렉터리를 기준으로 코드를 구성하는 것이다.
     *      - 장점 : 도메인의 관련 코드를 응집할 수 있따.
     *      - 단점 : 프로젝트의 이해도가 낮을 경우 전체적인 구조를 파악하기 어렵다.
     *
     *     계층형 Directory 구조
     *     src/main/java
     *     자바파일들을 담는다.
     *      - web(controller)
     *      - service
     *      - db
     *        1) repository(dao)
     *        2) entity
     *      - global(공통적으로 사용되는 것들)
     *        1) auth
     *        2) exception
     *      - model(dto)
     *      - config
     *
     *     src/main/resources
     *     리소스 파일들을 담는다.
     *      - static
     *       1) js, css, img
     *      - templates
     *       1) thymeleaf
     *      - application.properties
     *      - O O O.properties
     *
     *     src/test/java
     *     테스트에 필요한 Junit 테스트 케이스
     *
     *     src/test/resources
     *     테스트에 필요한 설정파일
     *
     *     도메인형 Directory 구조
     *     src/main/java
     *     자바파일들을 담는다.
     *     -
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */

}
