package singleton;

public class SingletonClass {

    private static class Holder {
        private static final SingletonClass SINGLETON_CLASS = new SingletonClass();
    }

    public static SingletonClass getInstanceV1() {
        return Holder.SINGLETON_CLASS;
    }

    private static volatile SingletonClass INSTANCE = null;

    private SingletonClass() {
    }

    private static SingletonClass getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonClass.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonClass();
                }
            }
        }
        return SingletonClass.INSTANCE;
    }

}
