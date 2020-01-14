package org.corgi.tobyexample.pattern;

public class Singleton {

    private static Singleton instance = new Singleton();

    // New를 통해 외부에서 객체 생성을 못하도록 막음
    private Singleton() {
    }

    // getInstance를 통해 외부에서 이미 생성된 단 하나의 Singleton 객체만 공유할 수 있도록 함
    // 다수의 스레드가 접근하여 내부 필드를 동시 다발적으로 변경시킬 수 있기 때문에
    // 상태 정보를 내부에 갖고 있지 않는 무상태(stateless) 방식으로 만들어져야 한다.
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
