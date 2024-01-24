
# 컨트롤러와 레스트 컨트롤러     
* Spring에서 컨트롤러를 지정해주기 위한 어노테이션은 @Controller와 @RestController가 있습니다.
* 전통적인 Spring MVC의 컨트롤러인 @Controller와 Restful웹 서비스의 컨트롤러인 @RestController의 주요한 차이점은
* HTTP ResponseBody가 생성되는 방식입니다. 이번에는 2가지 어노테이션의 차이와 사용법에 대해 알아보도록 하겠습니다.
*
### 1. @Controller 이해하기
*  [ Controller로 View 반환하기 ]
전통적인 Spring MVC의 컨트롤러인 @Controller는 주로 View를 반환하기 위해 사용합니다. 아래와 같은 과정을 통해
Spring MVC Container는 Client의 요청으로부터 View를 반환합니다.
1) Client는 URI형식으로 웹 서비스에 요청을 보낸다.
2) DispatcherServlet이 요청을 처리할 대상을 찾는다.
3) HandlerAdapter을 통해 요청을 Controller로 위임한다.
4) Controller는 요청을 처리한 후에 ViewName을 반환한다.
5) DispatcherServlet은 ViewResolver를 통해 ViewName에 해당하는 View를 찾아 사용자에게 반환한다.
     
Controller가 반환한 뷰의 이름으로부터 View를 렌더링하기 위해서는 ViewResolver가 사용되며, ViewResolver 설정에 맞게 View를 찾아 렌더링합니다.
     
*  [ Controller로 Data 반환하기 ]
하지만 Spring MVC의 컨트롤러를 사용하면서 Data를 반환해야 하는 경우도 있습니다. 컨트롤러에서는 데이터를 반환하기 위해
@ResponseBody 어노테이션을 활용해주어야 합니다. 이를 통해 Controller도 Json 형태로 데이터를 반환 할 수 있습니다.
1) Client는 URI 형식으로 웹서비스에 요청을 보낸다.
2) DispatcherServlet이 요청을 처리할 대상을 찾는다.
3) HandlerAdapter을 통해 요청을 Controller로 위임한다.
4) Controller는 요청을 처리한 후에 객체를 반환한다.
5) 반환되는 객체는 Json으로 Serialize되어 사용자에게 반환된다.
     
컨트롤러를 통해 객체를 반환할 때에는 일반적으로 ResponseEntity로 감싸서 반환을 합니다. 그리고 객체를 반환하기 위해서는
view Resolver 대신에 HttpMessageConverter가 동작합니다. HttpMessageConverter에는 여러 Converter가 등록되어 있꼬,
반환해야 하는 데이터에 따라 사용되는 Converter가 달라집니다. 단순 문자열인 경우에는
StringHttpMessageConverter가 사용되고, 객체인 경우에는 MappingJackson2HttpMessageConverter가 사용되며, 데이터 종류에 따라
서로 다른 MessageConverter가 작동하게 됩니다. Spring은 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을
조합해 적합한 HttpMessageConverter를 선택하여 이를 처리합니다. MessageConverter가 동작하는 시점은 HandlerAdapter와 Controller가
요청을 주고 받는 시점이다.
     
### 2. @RestController 이해하기
* [ RestController ]
@RestController는 @Controller에 @ResponseBody가 추가된 것입니다. 당연하게도 RestController의 주용도는 Json 형태로 객체 데이터를
반환하는 것입니다. 최근에 데이터를 응답으로 제공하는 REST API를 개발할 때 주로 사용하며 객체를 ResponseEntity로 감싸서 반환합니다.
이러한 이유로 동작 과정 역시 @Controller에 @ResponseBody를 붙인 것과 완벽히 동일합니다.
1) Client는 URI 형식으로 웹 서비스에 요청을 보낸다.
2) DispatcherServlet이 요청을 처리할 대상을 찾는다.
3) HandlerAdapter을 통해 요청을 Controller로 위임한다.
4) Controller는 요청을 처리한 후에 객체를 반환한다.
5) 반환되는 객체는 Json으로 Serialize되어 사용자에게 반환된다.
     
 

