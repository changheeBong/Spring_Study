
### Local-Variable Syntax for Lambda Parameters
Java 10부터 지역변수에 var 키워드를 통해 타입추론을 할 수 있었다. 그런데 Lambda 표현식의 변수엔 사용 불가 했다.  <br>
하지만 이제는 Lambda expression 에서도 변수에 var 키워드를 사용 가능하다. <br>
 ```java
 ex) Stream.iterate(1, (var number) -> number + 1). limit(10).collect(Collectors.toList())
 ```
 만약 변수가 2개이상일 경우엔 혼합해서 사용하면 안된다. <br>
 ```java 
 BiConsumer<Integer, Integer> foo = (var b1, Integer b2) -> {} ;
 ``` 
 해당 코드는 올바르지 않다. <br>
 ### http client  
 java.net의 HttpClient가 java 11부터 정식으로 표준화 되었다. java9에서 인큐베이팅 된 후 두번의 업그레이드 후 출시 되었다. <br>
 기존의 HttpURLConnection들은 사용하기 어렵고 사용자 친화적이지 않다. <br>
 그래서 다른 써드 라이브러리들을 많이 사용해왔다. <br>
 그러나 이제는 조금 편리하고 사용자 친화적인 HttpClient를 사용하면된다.
```java
    public static void main(String[] args) throws Exception {
    var httpClient = HttpClient.newBuilder()
             .build();
     var httpRequest = HttpRequest.newBuilder()
             .GET()
             .uri(URI.create("https://httpstat.us/200"))
             .build();
     var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
     System.out.println(httpResponse.body());
     }
```
 ### Pattern Matching for instanceof
 java 14부터 진행되고 있는 instanceof Pattern Matching 이다.
```java
     if (records instanceof Records) {
         var r = (Records) records;
         // use r
     }
      이전에는 위와 같이 instanceof로 비교한 후 해당 타입으로 캐스팅을 했다. 어쩌면 불필요한 코드였을지도 모른다. 그러나 이제는 그럴 필요가 없다.
     if (records instanceof Records r) {
         // use r
     } else {
         // cant use r
     }
     이제는 해당 타입의 캐스팅하지 않고 바로 사용할 수 있다. 만약 해당 조건에 만족하지 않으면 사용할 수 없다.
     if (!(records instanceof Records r)) {
         // cant use r
     } else {
         // use r
     }
    
     해당 변수로 조건문을 추가 할 수도 있다.
    
     if (records instanceof Records r && r.name.length() > 10) {
       // use r
     }
```
 편리한 기능이 추가 되었다. <br>
 ### Switch Expressions and Pattern Matching
 java 12 부터 Switch Expressions을 사용할 수 있고 java17 부터 Pattern Matching을 사용할 수 있다. <br>
 이전에 switch문은 실수의 여지도 크며 불필요한 코드가 많았다.
```java
     public static void getTransportPrint(Transport transport){
         switch(transport){
             case BUS:
                 System.out.println("take a bus");
                 break;
             case TAXI:
                 System.out.println("take a taxi");
                 break;
             case SUBWAY:
                     System.out.println("take a subway");
                     break;
                 default:
                     System.out.println("walking");
                     break;
         }
     }
```
 break 키워드를 실수로 넣지 않으면 버그가 나오기 쉽다. <br>
 또한 break 키워드는 불필요한 코드일지도 모른다. <br>
 이제는 간단한 표현식으로 바꿀수도 있다.

```java
    public static void getTransportPrint(Transport transport) {
     switch (transport) {
         case BUS -> System.out.println("take a bus");
         case TAXI -> System.out.println("take a taxi");
         case SUBWAY -> System.out.println("take a subway");
         default -> System.out.println("walking");
     }
    }
```
 실수의 여지도 적으며 코드도 간결해졌다. 물론 case에 여러 조건도 가능하다.
```java
    switch (transport) {
     case BUS, TAXI -> System.out.println("take a bus and taxi");
     case SUBWAY -> System.out.println("take a subway");
     default -> System.out.println("walking");
}
```
 표현식이라 리턴도 받을 수 있다.
```java
    public static int getTransportNumber(Transport transport){
     return switch (transport){
         case BUS, TAXI -> 1;
         case SUBWAY -> 2;
     };
}
```
 추가적으로 yield 키워드도 추가되었다. case문에 추가적인 코드가 들어갈 때 사용하면 된다.
 ```java
    public static int getTransportOrdinal(Transport transport){
     return switch(transport){}
     case BUS -> 0;
     case TAXI ->{
         var ordinal = transport.ordinal();
         // bla bla
         yield 1200;
     }
     case SUBWAY ->{
         // bla bla
         yield 8000;
     }
   };
 }
```
 이전에는 타입체크를 할 경우 if문과 instanceof를 사용해 체크를 했다.
```java    
    public static double getTypeNumberSwitch(Object o){
     var result = 0d;
     if(o instanceof Number){
         var i = (Integer) o;
         result = i.doubleValue();
     }else if(o instanceof String){
         var s = (String) o;
         result = Double.parseDouble(s);
     }
     return result;
    }
```
 불필요한 코드들고 많고 result에 계속 어싸인을 하고있어 실수의 여지도 크다. 이제는 그럴 필요 없다.
```java
 public static double getTypeNumberSwitch(Object o){
     return switch(o){
         case Number i -> i.doubleValue();
         case String s -> Double.parseDouble(s);
         default -> 0;
     };
 }
 ```
 간단하면서 한눈에 알아 볼 수 있는 코드가 되었다.

 ### Text Blocks
 java 13부터 나온 기능이다. 다른언어는 진작에 있었긴 한데 그래도 나오니 한결 나은거 같다. <br>
 멀티 라인의 String을 손쉽게 작성할 수 있다. 이전에는 멀티라인의 string 컨트롤하기 힘들었다. <br>
 예를들어  json을 예쁘게 만들고 싶다면 아래와 같이 해야 했다.
```java
 final var text = """
 {
     "name" : "wonwoo",
     "address":"Carson,CA,90746"
 }
 """;
```