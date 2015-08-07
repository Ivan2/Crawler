package classes.service_locator;

import java.util.HashMap;

public class ServiceLocator {

	private HashMap<Class, Class> classesMap;
	private HashMap<Class, Object> objectMap;

	protected ServiceLocator() {
		classesMap = new HashMap<Class, Class>();
		objectMap = new HashMap<Class, Object>();
	}

	public boolean add(Class abstraction, Class realization, boolean singleton) {
		classesMap.put(abstraction, realization);
		try {
			if (singleton)
				objectMap.put(abstraction, realization.newInstance());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public <T> T getObject(Class<T> abstraction) {
		if (objectMap.containsKey(abstraction))
			return (T)objectMap.get(abstraction);

		Class realization = classesMap.get(abstraction);
		try {
			return (T)realization.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

}
