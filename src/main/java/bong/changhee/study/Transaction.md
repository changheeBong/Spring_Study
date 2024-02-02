
1. Transaction(트랜잭션)이란 ? <br>
 [ Transaction의 탄생 배경 ] <br>
 보다 복잡한 프로그램을 개발하다 보면 쿼리 한 줄로 해결할 수 없는 로직을 처리해야하는 경우가 많습니다.  <br>
여러 개의 쿼리가 처리되는 상황에서 문제가 생겨버린다면 시스템에 큰 결함을 남기게 됩니다. <br>
예를 들어 쇼핑몰 서비스를 구현한다고 하면 아래와 같은 로직은 한줄로 처리한느 것이 불가능합니다. <br>
 잔여금액확인 -> 선택상품구매 -> 잔여금액감소 <br>
 먼저 쇼핑몰에서 상품을 구매할 때 회원의 잔여 금액이 충분한지 확인하고 잔여 금액이 상품 가격보다 높을 때 구매 로직으로 넘어가야 합니다. <br>
그리고 상품의 재고가 있는지 확인 후에 회원의 잔여 금액을 상품가격만큼 감소시키고 로직을 종료해야 합니다. <br>
 그런데 선택상품구매 단계에서 Exception()이 발생하여 상품이 없음에도 불구하고 있다고 판단하였거나 잔여 금액이 감소하는 찰나에 서버의 전원이 나가서 상품을 구매했는데도 회원의 잔여 금액이 감소하지 않을 수 있습니다. <br>
이러한 상황은 곧바로 엄청난 비용 손실을 유발하는데, 이러한 문제를 해결하기 위해 Transaction 기술이 탄생하게 되었습니다.

 [ Transaction의 기본 방법 ] <br>
 Transaction은 2개 이상의 쿼리를 하나의 커넥션으로 묶어 DB에 전송하고, 이 과정에서 에러가 발생할 경우 자동으로 모든 과정을 원래대로
 되돌려 놓습니다.<br>
 이러한 과정을 구현하기 위해 Transaction은 하나 이상의 쿼리를 처리할 때 동일한 Connection 객체를 공유하도록 합니다.

 2. Spring에서 Transaction의 사용법 <br>
 [ Spring 의 Transaction ] <br>
 Spring은 코드 기반의 트랜잭션(Programmatic Transaction) 처리 뿐만 아니라 선언적 트랜잭션 (Declarative Transaction)을 지원하고 있습니다. <br>
 Spring이 제공하는 트랜잭션 템플릿 클래스를 이용하거나 설정파일, 어노테이션을 이요해서 트랜잭션의 범위 및 규칙을 정의할 수 있습니다. <br>
 Spring에서는 주로 선언적 트랜잭션을 이용하는데, <tx:advice>태그 또는 @Transactional 어노테이션을 이용하는데, 쿼리문을 처리하는 과정에서 에러가
 났을 경우 자동으로 Rollback 처리를 해줍니다.

 [ Spring @Transactional ] <br>
 일반적으로 Spring에서는 Service Layer에서 @Transactional을 추가하여 Transaction 처리를 합니다. <br>
 아래의 예씨는 상점과 관련된 Service 부분이고, 데이터의 조회만 일어나는 select 메소드에서는 @Transactional을 활용하고 있지 않지만,
 값을 추가하거나 변경 또는 삭제하는 insert, update, delete 메소드에는 @Transactional을 추가하여 트랜잭션을 설정해 두었습니다.

 ------------------------------------
 1. Transaction(트랜잭션)에 대한 이해 <br>
 [ 트랜잭션(Transaction)의 필요성 ] <br>
 만약 데이터베이스의 데이터를 수정하는 도중에 예외가 발생된다면 어떻게 해야 할까? DB의 데이터들은 수정이 되기 전의 상태로 다시 되돌아가져야 하고,
 다시 수정 작업이 진행되어야 할 것이다. <br>
 이렇듯 여러 작업을 진행하다가 문제가 생겼을 경우 이전 상태로 롤백하기 위해 사용되는 것이 트랜잭션(Transaction) 이다. <br>
 트랜잭션은 더 이상 쪼갤 수 없는 최소 작업 단위를 의미한다. 그래서 트랜잭션은 commit으로 성공 하거나 rollback으로 실패 이후 취소되어야 한다. <br>
 하지만 모든 트랜잭션이 동일한 것은 아니고 속성에 따라 동작 방식을 다르게 해줄 수 있다. <br>
 예를 들어 1개의 새로운 데이터를 추가하는 와중에 에러가 발생하면 해당 추가 작업은 없었떤 것처럼 되돌려진다. 하지만 만약 여러 개의 작업에
 대해 롤백을 하려면 어떻게 해야 할까? 여러 개의 작업을 1개의 트랜잭션으로 관리해야 할 것이다. <br>
 위에서 설명한 것과 마찬가지로 트랜잭션의 마무리 작업으로는 크게 2가지가 있다. 
  - 트랜잭션 커밋: 작업이 마무리 됨
  - 트랜잭션 롤백: 작업을 취소하고 이전의 상태로 돌림
 만약 여러 작업이 모두 마무리 되었따면 트랜잭션 커밋을 통해 작업이 마무리되었음을 알려주어 반영해야 하며, 만약 문제가 생겼다면 작업 취소를 위해
 트랜잭션 롤백 처리를 해주어야 한다.

 2. Spring이 제공하는 Transaction(트랜잭션) 핵심 기술 <br>
 Spring은 트랜잭션과 관려노딘 3가지 핵심 기술을 제공하고 있다. <br>
그 3가지 핵심 기술은 다음과 같다. 
 * 트랜잭션(Transaction) 동기화
 * 트랜잭션(Transaction) 추상화
 * AOP를 이용한 트랜잭션(Transaction) 분리

 [ 1. 트랜잭션(Transaction) 동기화 ] <br>
 JDBC를 이용하는 개발자가 직접 여러 개의 작업을 하나의 트랜잭션으로 관리하려면 Connection 객체를 공유하는 등 상당히 불필요한 작업들이 많이 생길 것 이다. <br>
 Spring은 이러한 문제를 해결하고자 트랜잭션 동기화(Transaction Synchronization) 기술을 제공하고 있다. 트랜잭션 동기화는 트랜잭션을
 시작하기 위한 Connection 객체를 특별한 저장소에 보관해두고 필요할 때 꺼내쓸 수 있도록 하는 기술이다. <br>
 트랜잭션 동기화 저장소는 작업 쓰레드마다 Connection 객체를 독립적으로 관리하기 때문에, 멀티쓰레드 환경에서도 충돌이 발생할 여지가 없다. <br>
 그래서 다음과 같이 트랜잭션 동기화를 적용하게 된다. <br>
```java
 // 동기화 시작
 TransactionSynchronizeManager.initSynchronization();
 Connection c = DataSourceUtils.getConnection(dataSource);

 // 작업 진행

 // 동기화 종료
 DataSourceUtils.releaseConnection(c, dataSource);
 TransactionSynchronizeManager.unbindResource(dataSource);
 TransactionSynchronizeManager.clearSynchronization();
 ```
 하지만 개발자가 JDBC가 아닌 Hibernate와 같은 기술을 쓴다면 위의 JDBC 종속적인 트랜잭션 동기화 코드들은 문제를 유발하게 된다. <br>
 대표적으로 Hibernate에서는 Connection이 아닌 Session이라는 객체를 사용하기 때문이다. <br>
 이러한 기술 종속적인 문제를 해결하기 위해 Spring은 트랜잭션 관리 부분을 추상화한 기술을 제공하고 있다. 

 [ 2. 트랜잭션(Transaction) 추상화 ] <br>
 Spring은 트랜잭션 기술의 공통점을 담은 트랜잭션 추상화 기술을 제공하고 있다. 이를 이용함으로써 애플리케이션에 각 기술마다(JDBC, JPA, Hibernate 등)
 종속적인 코드를 이용하지 않고도 일관되게 트랜잭션을 처리할 수 있도록 해주고 있다. <br>
 Spring이 제공하는 트랜잭션 경계 설정을 위한 추상 인터페이스는 PlatformTransactionManager 이다. 예를 들어 만약 JDBC의 로컬 트랜잭션을 이용한다면
 DataSourceTxManager를 이요하면 된다. <br>
 이제 우리는 사용하는 기술과 무관하게 PlatformTransactionManager를 통해 다음의 코드와 같이 트랜잭션을 공유하고, 커밋하고, 롤백할 수 있게 되었다. <br>
```java 
public Object invoke(MethodInovation) throws Throwable{
     TransactionStatus status = this .transactionManager.getTrasnsaction(new DefaultTransactionDefinition());

     try{
         Object ret = invoation.proceed();
         this.transactionManager.commit(status);
         return ret;
     }catch(Exception e){
         this.transactionManager.rollback(status);
         throw e;
     }
 }
 ```
하지만 위와 같은 트랜잭션 관리 코드들이 비지니스 로직 코드와 결합되어 2가지 책임을 갖고 있다, <br>
Spring에서는 AOP를 이용해 이러한 트랜잭션 부분을 핵심 비지니스 로직과 분리하였다. 

[ 3. AOP를 이용한 트랜잭션(Transaction) 분리 ] <br>
```java
public void addUsers(List<User> userList){
   TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

   try{
       for(User user: userList){
           if(isEmailNotDuplicated(user.getEmail())){
               userRepository.save(user);
           }
       }
       this.transactionManager.commit(status);
   }catch(Exception e){
       this.transactionManager.rollback(status);
       throw e
   }
}
```
위의 코드는 여러 책임을 가질 뿐만 아니라 서로 성격도 다르고 주고받는 것도 없으므로 분리하는 것이 적합하다. <br>
하지만 위의 코드를 어떻게 분리할 것인지에 대한 고민을 해야 한다.<br>
흔히 떠올릴 수 있는 방법으로는 내부 메소드로 추출하거나 DI로 합성을 이용해 해결하거나 상속을 이용할 수 있을 것이다. <br>
하지만 위의 어떠한 방법을 이용하여도 트랜잭션을 담당하는 기술 코드를 완전히 분리시키는 것이 불가능 하였다. 그래서 Spring에서는
마치 트랜잭션 코드와 같은 부가 기능 코드가 존재하지 않는 것 처럼 보이기 위해 해당 로직을 클래스 밖으로 빼내서 별도의 모듈로 만드는
AOP(Aspect Oriented Programming, 관점 지향 프로그래밍)를 고안 및 적용하게 되었고, 이를 적용한 트랜잭션 어노테이션(@Transactional)을 지원하게 되었다.
이를 적용하면 위와 같은 코드를 핵심 비지니스 로직만 다음과 같이 남길 수 있다.
```java
@Service
@RequiredArgsConstructor
@Transactional
public class UserService{
    private final UserRepository userRepository;

    public void addUsers(List<User> userList){
    for(User user : userList){
        if(isEmailNotDuplicated(user.getEmail())){
        userRepository.save(user);
        }
      }
    }
}
```
[ Spring 트랜잭션의 세부 설정 ] <br>
Spring의 DefaultTransactionDefinition이 구현하고 있는 TransactionDefinition 인터페이스는 트랜잭션의 동작 방식에 영향을 줄 수 있는
네 가지 속성을 정의하고 있다. <br>
해당 4가지 속성은 트랜잭션을 세부적으로 이용할 수 있개 도와주며, @Transactional 어노테이션에도 공통적으로 적용할 수 있다.
- 트랜잭션 전파
- 격리수준
- 제한 시간
- 읽기 전용
#### 트랜잭션 전파
 트랜잭션 전파란 트랜잭션의 경계에서 이미 진행중인 트랜잭션이 있거나 없을 때 어떻게 동작할 것인가를 결정하는 방식을 의미한다. <br>
 예를 들어 어떤 A작업에 대한 트랜잭션이 진행중이고 B작업이 시작될 때 B작업에 대한 트랜잭션을 어떻게 처리할까에 대한 부분이다.

 1. A의 트랜잭션에 참여(PROPAGATION_REQUIRED) <br>
 B의 코드는 새로운 트랜잭션을 만들지 않고 A에서 진행중인 트랜잭션에 참여할 수 있다. 이 경우 B의 작업이 마무리되고 나서
 A의 작업(2)을 처리할 때 예외가 발생하면 A와 B의 작업이 모두 취소된다. <br>
왜냐하면 A와 B의 트랜잭션이 하나로 묶여 있기 때문이다.

 2. 독립적인 트랜잭션 생성(PROPAGATION_REQUIRES_NEW) <br>
 반대로 B의 트랜잭션은 A의 트랜잭션과 무관하게 만들 수 있다. <br>
이 경우 B의 트랜잭션 경계를 빠져나오는 순간 B의 트랜잭션은
 독자적으로 커밋 또는 롤백되고, 이것은 A에 어떠한 영향도 주지 않는다. <br>
즉, 이후 A가 (2)번 작업을 하면서 예외가 발생해 롤백되어도 B의 작업에는 영향을 주지 못한다.

 3. 트랜잭션 없이 동작(PROPAGATION_NOT_SUPPORTED) <br>
 B의 작업에 대해 트랜잭션을 걸지 않을 수 있다. <br>
만약 B의 작업이 단순 데이터 조회라면 굳이 트랜잭션이 필요 없을 것이다. <br>
 이렇듯 이미 진행중인 트랜잭션이 어떻게 영향을 미칠 수 있는가에 대한 부분이 트랜잭션 전파 속성이다. <br>
트랜잭션을 시작하는 것처럼 보이는 getTransaction()코드가 begin이 아닌 get으로 이름이 지어진 이유도 여기에 있다. <br>
해당 트랜잭션은 다른 트랜잭션에 묶여있을 수 있기 때문이다. <br>
위에서 설명한 대표적인 처리 방법 외에도 다양한 처리 방법을 지원하고 있다.

### 격리 수준
 모든 DB 트랜잭션은 격리수준을 가지고 있어야 한다. <br>
 서버에서는 여러 개의 트랜잭션이 동시에 진행될 수 있는데, 모든 트랜잭션을 독립적으로 만들고 순차 진행 한다면 안전하겠지만 성능이 크게 떨어질 수 밖에 없다. <br>
 따라서 적절하게 격리 수준을 조정해서 가능한 많은 트랜잭션을 동시에 진행시키면서 문제가 발생하지 않도록 제어해야 한다. <br>
 이는 JDBC 드라이버나 DataSource 등에서 재설정할 수 있고, 트랜잭션 단위로 격리 수준을 조정할 수도 있다.

 DefaultTransactionDefinition에 설정된 격리수준은 ISOLATION_DEFAULT로 DataSource에 정의된 격리 수준을 따른다는 것이다. <br>
 기본적으로는 DB나 DataSource에 설정된 기본 격리 수준을 따르는 것이 좋지만, 특별한 작업을 수행하는 메소드라면 독자적으로 지정해줄 필요가 있다.

 ### 제한시간
 트랜잭션을 수행하는 제한시간을 설정할 수 있다. <br>
 제한시간의 설정은 트랜잭션을 직접 시작하는 PROPAGATION_REQUIRED나 PROPAGATION_REQUIRES_NEW의 경우에 사용해야만 의미가 있다.

 ### 읽기전용
읽기전용으로 설정해두면 트랜잭션 내에서 데이터를 조작하는 시도를 막아줄 수 있을 뿐만 아니라 데이터 액세스 기술에 따라 성능이 향상될 수 있다.
 ***
 1. Spring 트랜잭션의 세부 설정(전파 속성, 격리 수준, 읽기전용, 롤백/커밋 예외 등) <Br>
 [ 전파 속성(Propagation) ] <Br>
 Spring이 제공하는 선언적 트랜잭션(트랜잭션 어노테이션, @Transactional)의 장점 중 하나는 여러 트랜잭션 적용 범위를
 묶어서 커다란 하나의 트랜잭션 경계를 만들 수 있다는 점이다. <Br>
우리는 Spring이 트랜잭션을 어떻게 진행시킬지 결정하도록 전파 속성을 전달해야 하는데, 이를 통해 새로운 트랜잭션을 시작할지 또는 기존의 트랜잭션에 참여할지 등을 결정하게 된다. <Br>
 Spring이 지원하는 전파 속성은 다음의 7가지가 있다. <Br>
* REQUIRED 
* SUPPORTS
* MANDATORY
* REQUIRES_NEW
* NOT_SUPPORTED
* NEVER
* NESTED

 1) REQUIRED <Br>
*  디폴트 속성으로써 모든 트랜잭션 매니저가 지원함 
*  이미 시작된 트랜잭션이 있으면 참여하고 없으면 새로 시작 
*  하나의 트랜잭션이 시작된 후 다른 트랜잭션 경계가 설정된 메소드를 호출하면 같은 트랜잭션으로 묶임
* REQUIRED는 기본 속성으로써 모든 트랜잭션 매니저가 지원하며, 대부분의 경우 이 속성이면 충분하다. 
* REQUIRED는 미리 시작된 트랜잭션이 있으면 참여하고 없으면 새로 시작한다. 
* 가장 간단하고 자연스러운 트랜잭션 전파 방식이지만 매우 강력하며 유용하다.

2) SUPPORTS
* 이미 시작된 트랜잭션이 있으면 참여하고, 그렇지 않으면 트랜잭션 없이 진행함 <br>
* 트랜잭션이 없어도 해당 경계 안에서 Connection 객체나 하이버네이트의 Session 등은 공유 할 수 있음

* SUPPORTS는 이미 시작 트랜잭션이 있으면 참여하고, 그렇지 않으면 트랜잭션 없이 진행하게 만든다. <br>
* 트랜잭션이 없기는 하지만 해당 경계 안에서 Connection 객체나 하이버네이트의 Session 등은 공유를 할 수 있다.

3. MANDATORY
 - 이미 시작된 트랜잭션이 있으면 참여하고, 없으면 새로 시작하는 대신 없으면 예외를 발생시킴
 - MANDATORY는 혼자서 독립적으로 트랜잭션을 진행하면 안되는 겨웅에 사용

* MANDATORY 역시 이미 시작된 트랜잭션이 있으면 참여한다. <br>
하지만 트랜잭션이 시작된 것이 없으면 새로 시작하는 대신 예외를 발생시킨다. <br>
즉, MANDATORY는 혼자서 독립적으로 트랜잭션을 진행하면 안되는 경우에 사용할 수 있다.

4. REQUIRES_NEW
 - 항상 새로운 트랜잭션을 시작해야 하는 경우에 사용
 - 이미 진행중인 트랜잭션이 있으면 이를 보류시키고 새로운 트랜잭션을 만들어 시작함

 * REQUIRES_NEW는 항상 새로운 트랜잭션을 시작해야 하는 경우에 사용할수 있다. 
만약 이미 시작된 트랜잭션이 있으면 트랜잭션을 잠시 보류시키고 새로운 트랜잭션을 만들어 시작하게 된다. <br>
만약 JTA 트랜잭션 매니저를 사용한다면 서버의 트랜잭션 매니저에 트랜잭션 보류가 가능하도록 설정되어 있어야 한다.

 5. NOT_SUPPORTED
  - 이미 진행중인 트랜잭션이 있으면 이를 보류시키고, 트랜잭션을 사용하지 않도록 함
  - NOT_SUPPORTED는 이미 진행중인 트랜잭션이 있으면 이를 보류시키고, 트랜잭션을 사용하지 않도록 한다.

 6. NEVER
  - 이미 진행중인 트랜잭션이 있으면 예외를 발생시키며, 트랜잭션을 사용하지 않도록 강제함
   - NEVER는 이미 진행중인 트랜잭션이 있으면 예외를 발생시키며, 트랜잭션을 사용하지 않도록 강제한다.

 7. NESTED
  - 이미 진행중인 트랜잭션이 있으면 중첩(자식) 트랜잭션을 시작함
  - 중첩 트랜잭션은 트랜잭션 안에 다시 트랜잭션을 만드는 것으로, 독립적인 트랜잭션을 만드는 REQUIRES_NEW와는 다름
  - NESTED 에 의한 중첩 트랜잭션은 먼저 시작된 부모 트랜잭션의 커밋과 롤백에는 영향을 받지만, 자신의 커밋과 롤백은
  부모 트랜잭션에게 영향을 주지 않음

  NESTED는 이미 진행중인 트랜잭션이 있으면 중첩 트랜잭션을 시작한다. <br>
  중첩 트랜잭션은 트랜잭션 안에 다시 트랜잭션을 만드는 것으로, 독립적인 트랜잭션을 만드는 REQUIRES_NEW와는 다르다. <br>
  NESTED에 의한 중첩 트랜잭션은 먼저 시작된 부모 트랜잭션의 커밋과 롤백에는 영향을 받지만, 
  자신의 커밋과 롤백은 부모 트랜잭션에게 영향을 주지 않는다. <br>
  예를 들어 어떤 중요한 작업을 진행하면서 작업 로그를 DB에 저장해야 한다고 해보자. <br>
  그런데 로그를 저장하는 작업은 실패를 하더라도 메인 작업의 트랜잭션까지는 롤백하지 말아야 하는 경우가 있다. <br>
  왜냐하면 힘들게 처리한 중요한 작업을 로그를 남기지 못해서 모두 실패로 만들 수 없기 때문이다. <br>
  반면에 핵심 작업에서 예외가 발생한다면 이때는 저장된 로그도 제거해야 한다. <br>
  이렇듯 부모의 트랜잭션은 자식의 작업에 영향을 줘야하지만 자식의 트랜잭션은 부모에 영향을 주지 않아야 할 때
  NESTED 전파 속성을 이용할 수 있다. <br>
  그리고 이러한 중첩 트랜잭션은 JDBC 3.0 스펙의 저장포인트 (SavePoint)를 지원하는 드라이버와 DataSourceTransactionManager를
  이용할 경우에 적용할 수 있다. <br>
  또는 중첩 트랜잭션을 지원하는 드라이버와 DataSourceTransactionManager를 이용할 경우에 적용할 수 있다. <br>
  또는 중첩 트랜잭션을 지원하는 일부 WAS의 JTA트랜잭션 매니저를 이요할 때도 적용할 수 있다. <br>
  즉 NESTED는 모든 트랜잭션 매니저에 적용 가능하지 않으므로 사용하는 트랜잭션 매니저와 드라이버 또는 WAS등을 확인해야 한다.

  선언적 트랜잭션에서는 @TRANSACTIONAL 어노테이션의 propagation 엘리먼트로 원하는 전파 속성을 지정할 수 있으며, 기본은
  required로 설정되어 있다. <br>
  또한 Spring은 6가지 전파 속성 방법을 지원하고 있지만 해당 속성을 지원하지 않는 트랜잭션 매니저와 데이터 액세스 기술이 있을 수 있다. <br>
  그러므로 변경이 필요한 경우 별도의 설정이 필요한지 확인을 해봐야 한다.
