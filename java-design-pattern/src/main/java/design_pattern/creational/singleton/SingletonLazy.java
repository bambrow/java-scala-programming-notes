package design_pattern.creational.singleton;

// lazy loading
// thread safe
// low efficiency

public class SingletonLazy {

	private static SingletonLazy instance;
	private SingletonLazy() { }
	
	public static synchronized SingletonLazy getInstance() {
		if (instance == null) {
			instance = new SingletonLazy();
		}
		return instance;
	}
	
}
