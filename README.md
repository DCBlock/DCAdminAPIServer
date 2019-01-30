# DCAdminAPIServer
관리자 백오피스 데이터 연동을 위한 Restful API Server

## 개발 환경
- Spring Boot 2.1.0 Release
- Openjdk 1.8

## Proxy 환경에서 X-Forward-For 설정 하기
Client -> L4(Proxy) -> Application과 같은 프록시 환경에서는 Application에 접속하는 Remote_host가 L4(Proxy) IP로 표시된다.
이 경우에 Remote_host의 IP를 확인해야 할 경우 Header의 X-Forward-For를 확인하면 된다.

> SpringBoot에서 X-Forward-For 설정하기 [[참고 링크]](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-security.html)

> application.properties는 다음 예제와 같이 항목을 추가
```
server.tomcat.remote-ip-header = x-forwarded-for
server.tomcat.protocol-header = x-forwarded-proto
```

## Eclipse / STS 환경 설정
- ~lombok 라이브러리를 사용하기 때문에 별도의 개발툴 환경 설정이 필요~
- Eclipse / STS: [바로가기](http://countryxide.tistory.com/16)
- IDEA: [바로가기](http://blog.woniper.net/229)

# License
GNU GENERAL PUBLIC LICENSE 3.0

## for Mybatis
- [mybatis](https://mvnrepository.com/artifact/org.mybatis/mybatis)
- [mybatis-spring](https://mvnrepository.com/artifact/org.mybatis/mybatis-spring)
- [mybatis-spring-boot-starter](https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter)
