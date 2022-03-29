package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    // static으로 자기 자신을 가지고 있으므로 한개만 생성
    // JVM이 뜰 때, 객체를 생성 후 자기 자신을 생성하여 넣어둠

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
