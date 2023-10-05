# section 2

### 강한 결합과 느슨한 결합

GameRunner 클래스와 MarioGame 클래스가 강하게 결합되어 있어 GameRunner 를 통해 SuperContraGame 을 실행하려면 코드를 변경해야 한다. 

(생성자와 필드 변경)

코드를 변경하여 SuperContraGame 을 실행할 수 있지만, 여전히 GameRunner 클래스와 특정 클래스가 강하게 결합되어 있어 MarioGame 을 사용하려면 다시 코드를 변경해야 한다.

결합 : 어떤 것을 변경할 때 얼마나 많은 작업이 연관되어 있는지에 대한 측정

SuperContraGame 대신 MarioGame 를 사용하려면 특정 클래스에서 얼마나 많은 변화가 있어야 하는가?

결합은 일상생활에도 매우 중요하다.

엔진은 차와 강하게 결합되어 있어 교체하는데 많은 노력이 들지만, 바퀴는 느슨하게 연결되어 있어 쉽게 바꿀 수 있다.

비지니스에도 변화가 필요하고, 프레임워크도 변화하고, 코드고 변화하기 때문에 가능한 느슨하게 결합해야한다.
가능한 코드를 적게 변경하면서 기능을 변경할 수 있어야한다.

(GamingConsole 인터페이스를 생성하고 SuperContraGame, MarioGame 클래스를 GamingConsole 를 구현하도록 변경.
GameRunner 의 생성자에는 특정 클래스가 아니라 GamingConsole 를 매게변수로 받도록 변경)

이제 GameRunner 클래스가 특정 게임과 결합되어 있지 않기 때문에 GameRunner 의 코드를 변경하지 않고도 다른 게임을 실행할 수 있다.


### 어떤식으로 도입할 수 있을까?
해외 송금 시 다양한 파트너사가 존재하고 파트너사마다 기능과 요구사항이 다르다.
하지만 송금, 검증, 상태확인 등 공통적인 기능이 있을 것이고 이를 인터페이스로 정의한 뒤 구현한다면 느슨한 결합으로 만들 수 있을 것이다.

그렇다면 무조건 느슨하게 결합하면 좋을까?

위의 예제에서는 느슨하게 결합한 결과 GameRunner 클래스의 코드를 변경하지 않고도 다른 게임을 실행할 수 있없는데
해외 송금 시 기존의 코드를 변경하여 다른 파트너사를 사용해야하는 경우는 많지 않았다.


### 의존성 연결 (Wiring of Dependencies)
GameRunner 는 게임을 실행할 수 있어야하고 그러기 위해서는 게임이 필요하다.
따라서 게임은 의존성이다.
GameRunner 에게 GamingConsole 은 의존성이고 생성자를 통해 객체를 생성함과 동시에 의존성을 주입한다.
이 의존성을 주입하는 작업은 스프링 프레임워크만 맡기다면?


### 스프링 컨텍스트
객체를 생성하고 의존성을 주입하는 작업을 스프링 프레임워크에게 맡겨보자.

1. Context 생성 후 @Configuration 으로 등록
2. 빈으로 등록하고 싶은 객체에 @Bean 애노테이션을 붙여서 컨텍스트에 등록

등록한 빈을 사용하려면 context.getBean(beanName) 메소드를 사용하여 빈의 이름으로 빈을 찾을 수 있다.
이름 말고 빈의 타입으로도 가져올 수 있다.

빈의 이름은 기본적으로 메소드의 이름과 같다. 이를 변경하려면? @Bean(name = "yourCustomBean)

Spring 에는 여러개의 빈을 등록할 수 있으며 다양한 방식으로 빈을 가져와서 사용할 수 있다.

지금은 Bean 상호간에 의존성이 없지만 관계를 가지게 할려면? 

(기존 스프링 빈과 관계가 있는 새로운 빈 생성)

관계를 만드는 방법
1. 내부에서 메소드 호출
2. 매개변수로 전달 (Bean 메소드에 매개변수의 이름과 같이 Bean 이 자동으로 주입된다.)

3. Bean 의 이름을 사용자가 지정할 수 있다.
4. 다양한 방법으로 스프링 컨텍스트에서 Bean 을 찾을 수 있다. (name, class)
5. 기존의 빈을 재사용해서 새로운 빈을 만들 수 있다.

기존의 빈을 재사용할 수 있다.

### Recode 란?
자바 14 부터 등잔한 클래스로 불변 타입의 객체를 쉽게 설정할 수 있도록 한다.
Setter, Getter, Constructor 를 자동으로 생성해 준다.


### 스프링 컨테이너란?
스프링 컨테이너는 스프링 컨텍스트 IOC 컨테이너로도 불린다.
스프링 컨테이너의 스프링 빈의 생명주기를 관리한다.

Java Class 와 Configuration 을 만들면 IOC Container 가 Runtime System 을 만든다. 
이 시스템이 스프링 컨텍스트를 만들고 빈을 관리한다.

Bean Factory : 기본 스프링 컨테이너
Application Context : 엔터프라이즈 전용 기능이 있는 고급 스프링 컨테이너 (강의에서는 이 컨텍스트를 사용)

### Java Bean, POJO, Spring Bean 의 차이는?
POJO - 모든 자바 객체 (No constrains)

Java Bean - 다음의 3가지 제약 만족하는 클래스 (현재는 잘 사용하지 않는다.)
1. public no argument constructor
2. getter and setter 로 프로퍼티에 접근
3. Serializable 구현

Spring Bean - Spring 이 관리하는 모든 객체

### 모든 스프링 빈을 나열하려면?
context 의 getBeanDefinitionNames 를 사용하면 등록된 빈 이름 리스트를 가져올 수 있다.

### 후보 빈이 여러개 있을  사용하려면?
스프링 빈을 찾을 때 여러 개의 후보 빈이 있으면 에러가 발생한다.
이럴 경우 빈을 사용하기 위해서는 @Primary 애노테이션으로 우선 순위를 정해주면 @Primary 으로 설정된 빈을 사용한다.
@Primary 로 설정되지 않은 빈을 사용하기 위해서는 @Qualifier 애노테이션을 사용해서 직접 설정해줘야 한다.


### Spring bean 을 앞서 만든 Game App 에 적용해보자.
해야할 일은 객체들을 Configuration 으로 만들고 이 Configuration 로 context 를 만들어야 한다.

(GameConfiguraion 생성 후 GameConsole 과 GameRunner 를 빈으로 등록)

### 스프링 빈을 직접 코드를 작성해서 만들었는데 스프링이 자동으로 만들도록 하려면 어떻게 해야 될까?

* 클래스 타입으로 빈을 찾을 경우 입력한 클래스의 자식 클래스도 함께 찾아진다.