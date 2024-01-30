##  Stream 정의
### 스트림(Stream) API 이란?
 - 스트림(Stream) API은 람다식(Lambda Expression)를 이용한 기술 중에 하나로 데이터 소스(컬렉션, 배열, 난수, 파일 등...)를 조작 및 가공, 변환하여 원하는 값으로 반환해주는
### 인터페이스를 의미합니다.
 - 해당 기능을 사용하기 위해서는 Java 1.8이상의 버전을 사용해야 합니다.
 - 해당 스트림 인터페이스는 import java.util.stream 에 포함되어 있습니다.

### 람다식 (Lambda Expression) 이란?
 - 함수를 하나의 식으로 표현한 함수형 인터페이스로 함수를 람다식으로 표현을 하면 메소드의 이름이 없기 때문에 익명 함수(Anonymous Function)의 한 종류라고도 합니다.

### 컬렉션 프레임워크 란?
 - 컬렉션 프레임워크는 프레임워크 내에 Collection(List, Set), Queue, Map의 인터페이스로 구성이 되어 있는 프레임워크를 의미합니다.
 - 해당 기능을 사용하기 위해서는 Java 1.2 이상의 버전을 사용해야 합니다.
 - 해당 컬렉션 프레임워크는 import java.util 내에 포함이 되어 있습니다.

### 컬렉션 인터페이스 내의 클래스 종류
 - List 인터페이스에는 컬렉션 클래스로 ArrayList, Vector, Stack, LinkedList가 있습니다.
 - Map 인터페이스에는 컬렉션 클래스로는 HashMap, TreeMap, LinkedHashMap가 있습니다.
 - Set 인터페이스에는 컬렉션 클래스로는 HashSet, TreeSet, LinkedHashSet 가 있습니다.

Stream 타입 별 객체의 종류
---------------------------------------------
|분류   |  타입	   |    Stream Type        |
|정수형 | byte      |	 Stream<Byte>      |
|정수형 | short     |	Stream<Short>      |
|정수형 |	int	   |     IntStream         |             
|정수형 |	long   |	LongStream         |
|문자형 |	char   |	Stream<Character>  |
|실수형 |	float  |	Stream<Float>      |
|실수형 |	dobule |	DoubleStream       |
|문자열 |	String |	Stream<String>     |
|논리형 |   boolean |	Stream<Boolean>    |
--------------------------------------------

### 기본 자료형(Primitive Data Type)
 - 데이터를 저장하기 위해 사용되는 자료형을 의미하며 해당 자료형 내에 "실제 값"을 가지는 것을 의미합니다.

### Stream의 특징
  1. Stream은 원본의 데이터를 변경하지 않는다. <br>
   Stream API는 원본 데이터를 조회하여 별도의 Stream 객체로 생성을 합니다.<br>
  그렇기에 배열의 정렬이나 필터링 작업을 하더라도 원본 데이터를 변경하지 않습니다.

  2. Stream은 재 사용이 불가능하여서 일회용으로 사용 됩니다. <br>
   Stream API는 이미 사용이 되어 닫혔다면 재 사용이 불가능하여
   java.lang.IllegalStateException 오류가 발생하며 새로운 Stream을 생성해주어야 합니다.

  3. Stream은 내부 반복으로 작업을 처리합니다. <br>
   Stream 내에서 내부적으로 반복문을 처리하기에 간결한 소스코드의 작성이 가능합니다.

  4. Stream의 과정 <br>
   Stream의 객체를 구성하고자 할떄에 "Stream 생성 -> 중간 연산 -> 최종 연산" 의 세 단계의 과정을 통하여서 처리가 이루어집니다. <br>
   해당 과정은 예시로 아래와 같이 표현이 됩니다. <br>
   // 객체의 Stream을 생성하고 중간 연산을 수행하고 최종 연산을 하여 결과값을 얻습니다. <br>
   객체.Stream생성().중간연산.최종연산()

  5. Stream의 과정 상세 
   1) Stream 생성 <br>
*  분류	 상세      분류
* Stream 생성	empty stream
* Stream 생성	Collection
* Stream 생성	Array
* Stream 생성	Stream.builder()
* Stream 생성	Stream.generate()
* Stream 생성	Stream.Iterator()
* Stream 생성	기본 타입 스트림
* Stream 생성	파일 스트림

  2) 중간 연산 <br>
   분류	                       상세 분류  <br>
Stream 필터	             filter(), distinct() <br>
Stream 변환	               map(), flatMap() <br>
Stream 제한	                 limit(), skip() <br>
Stream 정렬	                     sorted() <br>
Stream 연산 결과 확인	              peek()

  3) 최종 연산 <br>
    분류	     상세 분류 <br>
 요소의 출력	forEach() <br>
 요소의 검색	findFirst(), findAny() <br>
 요소의 검사	anyMatch(), allMatch(), noneMatch() <br>
 요소의 통계	count(), min(), max() <br>
 요소의 연산	sum(), average() <br>
 요소의 수집	collect()

 **Stream API 활용 : 스트림(Stream) 생성**
 1. Stream 생성 관련 요약 <br>
 분류	상세 분류	예제 <br>
Stream 생성	empty stream 생성	Stream.empty(); <br>
Stream 생성	Collection 생성	ArrayList.stream(); <br>
Stream 생성	Array 생성-1	Arrays.stream(Arr); <br>
Stream 생성	Array 생성-2	Stream.of("one", "two", "three") <br>
Stream 생성	Stream.builder() 생성	Stream.<String>builder().add("a").build(); <br>
Stream 생성	Stream.generate()생성	Stream.generate(() -> "a").limit(3); <br>
Stream 생성	Stream.Iterator() 생성	Stream.iterate(70, (n) -> n + 10).limit(3) <br>
Stream 생성	기본 타입 스트림 생성	IntStream intStream = IntStream.range(1, 5); <br>
LongStream longStream = LongStream.rangeClosed(1, 5); <br>
DoubleStream doubleStream = DoubleStream.iterate(100, (n) -> n + 10).limit(3); <br>
Stream 생성	파일 스트림 생성	Files.lines(Paths.get("file.txt"), Charset.forName("UTF-8")); <br>

2. 빈 스트림(Empty Stream) 생성 <br>
요소 값이 존재하지 않는 빈 스트림 객체를 생성합니다. <br>
Stream<Object> emptyStream = Stream.empty();

3. 컬렉션 스트림(Collection Stream) 생성 <br>
컬렉션 클래스의 요소 값으로 스트림 객체를 구성할때 사용합니다. <br>
컬렉션 값이 존재하는 스트림 객체를 생성합니다. <br>
List<String> strList = new ArrayList<>(Arrays.asList("a","b","c","d")); <br>
Stream<String> strStream = strList.stream();

4. 배열 스트림(Array Stream) <br>
 배열의 요소 값으로 스트림 객체를 구성할 때 사용합니다. <br>
 배열 값이 존재하는 스트림 객체를 생성합니다. <br>
 String[] strArr = {"a","b","C","D"}; <br>
 Stream<String> strStream2 = Arrays.stream(strArr); <br>
 Stream<String> strStream3 = Stream.of(strArr); <br>
 Stream<String> strStream4 = Stream.of("one","two","three"); 

5. 빌더 스트림 (Builder Stream) <br>
 빌더로 구성한 요소값들로 스트림 객체를 구성할때 사용합니다. <br>
  4) 빌더로 값을 구성하여서 스트림 객체를 생성합니다. <br>
 ```java
Stream<String> builderStream = Stream.<String>builder()  
                                     .add("a")
                                     .add("b")
                                     .add("c")
                                     .add("d")
                                     .build();
```
 6. Stream.generate() 
  - generate() 함수내에서 람다(Lambda)로 값을 지정하여 스트림을 구성할 때 사용합니다.
  - 해당 스트림은 제한을 두지 않으면 무한으로 생성되기에 최대 크기를 지정합니다.
  `Stream<String> generatedStream = Stream.generate(() -> "abcd").limit(3);`

 7. Stream.iterate()
 iterate() 함수내에서 람다(Lambda)로 값을 지정하여 스트림을 구성할 때 사용합니다. <br>
 `Stream<Integer> iteratedStream = Stream.iterate(70, (n) -> n + 10).limit(3);`

8. 기본 타입형 스트림
  기본타입(int.long,double)으로 값을 지정하여 스트림을 구성할 때 사용합니다.
```java
  IntStream intStream = IntStream.range(1, 5);
  LongStream longStream = LongStream.rangeClosed(1, 5);
  DoubleStream doublStream = DoubleStream.iterate(100, (n) -> n + 10).limit(3);
```
 9. 파일 타입형 스트림
  파일 타입(File)을 요소값으로 지정하여 스트림을 구성할때 사용합니다. <br>
 `Stream<String> fileToStrStream = Files.lines(Paths.get("file.txt"), Charset.forName("UTF-8"));`

---------------------------------------------------------------------------------------
1) Stream의 구성 과정 <br>
 Stream의 객체를 구성하고자 할 때 "Stream 생성 -> 중간 연산 -> 최종 연산"의 세 단계의 과정을 통하여서 Stream의 구성이 이루어집니다. 해당 글에서 과정은 "중간 연산"부분에 해당됩니다. <br>
 객체.Stream생성().중간연산.최종연산()

2) Stream 중간연산
 1. 요약 <br>
  분류	        상세 분류	                        설명 <br>
Stream 필터	filter(), distinct()	- filter()는 구성한 스트림 내에서 특정 조건에 맞는 결과값을 필터링 하기 위해 사용됩니다. <br>
                                     - distinct()는 구성한 스트림 내에서 중복을 제거한 결과값을 반환하기 위해 사용됩니다. <br>
Stream 변환	map(), flatMap()	    - map()은 구성한 스트림 내에서 요소들을 원하는 값으로 변환하여서 반환하기 위해 사용됩니다. <br>
                                     - flatMap()는 구성한 스트림 내에서 중복된 스트림을 평면화(flattern)하여 요소를 구성하여 반환하기 위해 사용됩니다. <br>
Stream 제한	limit(), skip()	        - limit()는 구성한 스트림 내에서 요소의 개수를 제한하여 반환해주기 위한 목적으로 사용됩니다. <br>
                                     - skip()는 구성한 스트림 내에서 시작부터 특정 n개의 요소를 제외하여 반환해주는 목적으로 사용합니다. <br>
Stream 정렬	sorted()	            - sorted()는 구성한 스트림 내에서 배열 혹은 리스트 그리고 Object의 요소에 대해서 오름/내림 차순을 수행하여 반환해주는 함수입니다. <br>
Stream 연산 결과 확인	peek()        	- peek()는 구성한 스트림 내에서 각각의 요소를 조회하는 연산자료 요소 자체를 추출하는것이 아닌 스트림의 요소를 조회하는 함수입니다. <br>

2. Stream 필터 : filter() <br>
해당 함수는 구성한 스트림 내에서 특정 조건에 맞는 결과값을 필터링하기 위해 사용됩니다. <br>
(값을 조작을 하기 위해서는 사용하지 않고 요소 내에서 조건부 검색을 위한 목적을 사용이 됩니다.) <br>

3. Stream 변환 : map() <br>
해당 함수는 구성한 스트림 내에서 요소들을 원하는 값으로 변환하여서 반환하기 위해 사용됩니다. <br>
요소 내에서 조건부 검색은 위한 목적으로 사용하는 filter와 다르게 요소를 조작하여서 반환을 해줍니다. <br>
메서드	타입 <br>
map()	Any <br>
mapToObj()	Object <br>
mapToLong()	Long <br>
mapToDouble()	Double <br>
