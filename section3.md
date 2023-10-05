### 스프링 빈을 자동으로 생성하려면?
일단 애플리케이션과 configuration 을 결합한다.
자바 빈에 등록할 클래스에 @Component 애노테이션을 추가한다.
이렇게 설정 후 애플리케이션을 실행해보면 빈을 찾지못해서 에러가 발생한다.
왜 component 로 설정했는데 찾지 못 할까? spring 에게 빈을 찾을 위치를 알려줘야 한다.
이 때 사용하는게 @componentScan 으로 찾을 패키지를 설정해 줄 수 있다.
패키지를 따로 설정하지 않으면 현재 패키지에서 componentScan 을 진행한다.


### 무엇이 달라졌을까?
기존에는 빈으로 등록할 클래스와 configuration 클래스를 만들 후 configuration 클래스에 빈에 등록할 객체를 return 해주는 메소드를 만들어서 직접 빈을 등록했었지만
이제 configuration 클래스 필요없어 애플리케이션을 configuration 로 사용하고 빈에 등록할 클래스에 @Component 애노테이션을 붙여주면 자동으로 등록된다.
즉, configuration 클래스를 작성할 필요가 없어졌다.
빈으로 등록할 클래스의 인스턴스를 리턴 해주는 메소드를 작성할 필요가 없어졌다.

Spring 은 객체를 관리하고 Auto-wiring 해줄 뿐만 아니라 @Component 를 바탕으로 객체를 자동으로 생성해 준다.

만약 애플리케이션이 작동하지 않는다면 먼저 @ComponentSacn 이 제대로 설정되었는지 확인한다.
@ComponentSacn 이 설정된 패키지에서 빈으로 생성하려는 클래스에 @Component 가 설정되었는지 확인한다.

### @Primary 와 @Qualifier 중 어떤걸 사용해야 할까?
@Primary - 여러 개의 빈 후보가 있는 경우 Bean 에 우선권을 준다.
@Qualifier - 명시된 빈이 auto-wiring 되어야한다.

@Autowired 를 사용할 때
@Autowired 만 사용하는 경우 : 적절한 빈 주입 (해당하는 후보가 하나 인 경우 그냥 주입하고 여러 개인 경우 @Primary 객체를 주입한다.)
@Autowired @Qualifier 2 개를 사용하는 경우 : 명시된 빈 주입
@Qualifier 가 @Primary 보다 높은 우선순위를 가진다.
만약 클래스에 @Qualifier 를 설정해주지 않았다면 빈의 이름을 Qualifier 로 사용할 수 있다. 

### 의존성 주입의 방식
1. 생성자 기반 - Class 생성자를 사용해서 의존성 주입 (@Autowired 를 적어주지 않아도 된다.)
2. Setter 기반 (수정자 기반) - Setter 메소드 에 @Autowired 를 사용해서 의존성 주입
3. 필드 기반 (reflection 사용) - 생성자나 수정자 없이 필드에 @Autowired 를 사용해서 의존성 주입

### 어떤 방식의 의존성 주입이 적절할까?
스프링에서는 생성자 기반 주입하는 것을 추천한다.
Why? 모든 초기화가 하나의 메소드 안에서 발생하기 때문에 

### 용어 정리
@Component - 스프링에 의해 관리될 클래스의 인스턴스(빈).

Dependency - GameRunner 를 사용하기 위해서는 GamingConsole 의 구현체가 필요하기 때문에 GamingConsole 의 구현체 (ex: MarioGame) 은 GameRunner 의 의존성이다.

Component scan - 스프링에게 컴포넌트 클래스를 찾을 패키지를 알려준다. (따로 명시하지 않은 경우 현재 패키지를 스캔)

의존성 주입 -  어플리케이션을 실행하면 스프링는 가장 먼저 컴포넌트를 스캔하고 컴포너틑의 의존성을 확인하고 연결하는데 이 과정을 의존성 주입이라고 한다.

제어의 역전(IoC) - 제일 처음에 만든 App01 은 객체 생성과 의존성을 프로그래머가 직접 코드로 제어를 했었는데 
GamingAppLauncherApplication 에서는 @ComponentScan 과 @Component 만 설정해주었을 뿐 객체를 생성하고 의존성을 주입하는 일은 스프링이 대신해주고 있다.
제어권이 프로그래머에서 스프링으로 넘어갔기 때문에 제어의 역전이라고 부른다.

스프링 빈 - 스프링이 관리하는 모든 객체를 스프링 빈이라고 한다.

IoC container - 빈의 생명주기 의존성을 관리하는 스프링 컴포넌트. 앞서 나온 Application Container 와 BeanFactory 가 있다.

Auto-wiring - 스프링 빈에 대한 의존성 wiring process 으로 특정 빈이 필요한 의존성이 무엇인지 식별하기위해 사용한다.

### @Component 와 @ Bean 차이는? 
Where ? 자바클래스에 사용 | 일반적으로 Configuration 클래스의 메소드에 사용
Ease of use ? 애노테이션만 붙이면 끝이기 때문에 간단 | 모든 코드를 작성해야한다.
Autowiring ? 필드, 수정자, 생성자 주입으로 가능 | 메소드 호출, 메소드 파라미터로 전달하는 방법으로 가능
Who creates beans ? 스프링 프레임 워크 | 직접 코드로 작성
Recommended For ? 대부분의 경우에 권장 | 빈을 생성하기 전에 비즈니스 로직을 만들어야하는 경우, 서드파티 라이브러리를 인스터스화 하는 경우 (다른 라이브러리를 사용할 때는 코드에 액세스 권한이 없기 때문에 @Component 를 추가할 수 없고 직접 빈을 생성해야한다.)

### 정리
실제 애플리케이션에는 예제보다 훨씬 많은 의존성이 존재하는데 스프링 프레임워크를 사용하면 의존성 주입을 대신 처리해주기 때문에 비지니스 로직에만 집중할 수 있다.
