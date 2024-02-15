## toString
***
### toString 메서드의 역할
***
toString() 메서드는 모든 클래스가 상속받는 Object 클래스의 메서드 중 하나로 정의는 다음과 같다. 
* 객체를 문자열로 표현하는 역할
* 주로 객체의 내용을 읽기 쉬운 형태로 출력하거나 로깅, 디버깅 등에 활용
Person 클래스의 기본 예시로 메서드가 정의하는 바가 이루어지는지 코드로 살펴보자
```java
public class person{
    private String name;
    private int age;
    
    // ... 생성자, getter, setter 등의 코드 ...
}

// ..생략
Person person = new Person("cobi", 20);
System.out.println(person);
System.out.println(person.toString()); //toString() 생략 가능
```
> 위 코드의 출력 결과
```java
Main.Person@6a5fc7f7
Main.Person@6a5fc7f7

// 클래스 이름 @16진수로_표시된_해시코드
```
toString() 메서드의 기본 구현은 **해당 객체의 클래스명과 해시 코드를 문자열로 반환하는 것**이다. <br>
하지만 이 기본 동작을 그대로 사용한다면, 객체의 실제 내용을 파악하기 어렵게 만들 것이다.

### toString() 항상 재정의하라.
```java
public class Person {
    private String name;
    private int age;

    // ... 생성자, getter, setter 등의 코드 ...

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
```
```java
Person person = new Person("cobi", 20);
System.out.println(person); // 출력: Person{name='cobi', age=20}
```
위 코드와 같이 개발자는 필요에 따라 이 메서드를 오버라이딩하여 의미 있는 문자열 형태로 객체 정보를 반환하도록 만들 수 있다.

toString()을 재정의 하는 이유를 정리하자면 다음과 같다. <br>
`이팩티브 자바 3판`
* toString 이 잘 구현된 클래스는 사용하기 편하고, 디버깅이 쉽다.
* 객체를 출력하기만 하면, 객체가 가지고있는 모든 정보를 확인할 수 있다.
* 우리가 직접 호출하지 않아도, 로깅을 하거나 에러메세지를 출력할 때에도 유용하게 사용할 수 있다.

### 추가 toString() 파헤치기
***
나는 toString()을 사용할 때 다음과 같은 피드백을 받았다. <br>
##### 비즈니스 로직과 UI 로직은 분리되어야 한다.
> 현재 객체의 상태를 보기 위한 로그메시지 성격이 강하다면 toString()을 통해 구현하고, 
> View에서 사용할 데이터라면 getter 메서드를 통해 데이터를 전달한다.

해당 피드백은 상태를 보기위한 로그와 view에서 사용할 데이터는 서로 다른 목적을 가지고 있다는 것이다.

두가지 예시로 자동차를 나타내는 Car 클래스를 코드로 풀어보려고 한다. <br>
주어진 Car클래스와 요구사항은 다음과 같다.

Car 클래스
* 자동차 이름을 나타내는 name(String)
* 자동차의 위치를 나타내느 location (int)
##### 기능 요구사항 (라운드 별 자동차 위치를 알려주세요 !)
> ex) cobi : --

해당 기능 요구사항을 구현하기위해 toString()과 getter 메서드를 통해 필요한 데이터 전달로 view에서 구현해야할때 어떤 것으로 구현해야 할까?

#### custom toString() 활용 vs getter 메서드를 통한 데이터 전달
***
```java
public class Car {
    private final String name;
    private int location;

    public Car(String name) {
        this.name = name;
        this.location = 0;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name=" + name +
                ", location=" + location +
                '}';
    }
}
```
위와 같이 toString() 메서드를 오버라이딩하면, 해당 클래스의 객체를 출력할 때 간편하게 현재 상태를 확인할 수 있다.

여기서 추가로 toString()을 요구사항에 맞게 커스텀 한다면 (view 로직 포함) 다음과 같이 표현할 수 있을 것이다.
```java
    @Override
    public String toString() {
        return name + " : " + convertToSeparator();
    }
    
    private String convertToSeparator() {
        return "-".repeat(position);
    }
```
커스텀하여 반환하게 한다면 view의 역할도 줄일 수 있으니 좋지 안흥ㄹ까 ?

해당 방식으로 구현을 하게된다면, 로그를 위한 상태확인이 아니라, 화면 로직을 포함하게 되며 (단일 책임 원칙 위배) <br>
toString의 역할에서도 벗어난다.

toString() 목적은 간결하면서 이해하기 쉬운 형태의 정보를 반환해야만 하기 때문이다.

그렇기 때문에, View에서 사용할 데이터라면 getter 메서드를 통해 데이터를 전달해야만 한다는 사실을 알게되었다.

비즈니스 로직과 UI 로직을 한 클래스가 담당하지 않도록 주의해야 할 것이다.

#### 정리
***
toString() 메서드는 디버깅이나 로깅을 할 때 객체의 내용을 쉽게 파악하기 위해 사용된다. <br>
객체의 상태를 문자열로 표현함으로써 프로그래머들이 코드를 분석하고 디버깅하는데 도움을 주는 역할을 할 것이다.





























