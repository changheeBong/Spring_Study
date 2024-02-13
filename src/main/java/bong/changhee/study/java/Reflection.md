## 리플렉션 (Reflection)
***
JVM은 클래스 정보를 클래스 로더를 통해 읽어와서 해당 정보를 JVM 메모리에 저장한다. <BR>
그렇게 저장된 클래스에 대한 정보가 마치 거울에 투영된 모습과 닮아있어, 리플렉션이라는 이름을 가지게 되었다. <BR>
리플렉션을 사용하면 생성자, 메소드, 필드 등 클래스에 대한 정보를 아주 자세히 알아낼 수 있다. <BR>
<BR>
대표적으로 여러 라이브러리, 프레임워크에서 사용되는 어노테이션이 리플렉션을 사용한 예시이다. <BR>
방금 말했듯 리플렉션을 사용하면 클래스와 메소드에 어떤 어노테이션이 붙어있는지 확인할 수 있다. <BR>
어노테이션은 그 자체로는 아무 역할도 하지 않는다. <BR>
리플렉션 덕분에 우리가 스프링에서 `@Component`, `@Bean` 과 같은 어노테이션을 프레임워크의 기능을 사용하기 위해 사용할 수 있는 것이다. <BR>
<BR>
또한, 인텔리제이와 같은 IDE에서 Getter, Setter를 자동으로 생성해주는 기능도 리플렉션을 사용하여 필드 정보를 가져와 구현한다고 한다. <BR>
이와 같이 리플렉션은 다양한 곳에서 무궁무진한 방법으로 사용될 수 있다. <BR><BR>

신기한것은 리플렉션을 사용하면 접근 제어자와 무관하게 클래스의 필드나 메소드도 가져와서 호출할 수 있다는 점이다.

### Class 클래스

리플렉션의 가장 핵심은 **Class** 클래스이다. **Class** 클래스는 java.lang 패키지에서 제공된다. <BR>
어떻게 특정 클래스의 **Class** 인스턴스를 흭득할 수 있을까 ?

### Class 객체 흭득 방법 
```java
Class<Member> aClass = Member.class; //(1)
Member member1 = new Member();
Class<? extends Member> bClass = member1.getClass(); //(2)

Class<?> aClass = class.forName("hudi.reflection.Member") ; // (3)
```
첫번째 방법으로는 클래스의 Class 프로퍼티를 통해 흭득 하는 방법이다. <BR>
두번째 방법으로는 인스턴스의 getClass() 메소드를 사용하는 것이다. <BR>
세번째 방법으로는 Class 클래스의 forName() 정적 메소드에 FQCN(Fully Qualified Class Name)를 전달하여 해당 경로와 대응하는 클래스에 대한
Class 클래스의 인스턴스를 얻는 방법이다.
이런 Class의 객체는 Class에 public 생성자가 존재하지 않아 우리가 직접 생성할 수 있는 방법은 없다. <BR>
대신 class의 객체는 JVM이 자동으로 생성해준다.

### getXXX() vs getDeclaredXXX()
***
Class 객체의 메소드 중 getFields(), getMethods(), getAnnotations() 와 같은 형태와
getDeclaredFields(), getDeclaredMethods(), getDeclaredAnnoations() 와 같은 형태로 메소드가 정의되어 있는 것을
확인할 수 있다. <BR>
이 메소드들은 클래스에 정의된 필드, 메소드, 어노테이션 목록을 가져오기 위해 사용된다. <BR><BR>






































