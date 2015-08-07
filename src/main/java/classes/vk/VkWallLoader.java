package classes.vk;

import classes.service_locator.ServiceLocatorAdapter;
import abstractions.vk.*;

public class VkWallLoader implements IVkWallLoader {

	private final byte POST_COUNT = 100;
	private final String METHOD = "wall.get";
	private final String PARAMS = "owner_id=-%s&count=%d&";

	public String loadWall(String wallID) {
		String params = String.format(PARAMS, wallID, POST_COUNT);
		IVkLoader loader = ServiceLocatorAdapter.getInstance().getObject(IVkLoader.class);
		return loader.loadData(METHOD, params);
	}

}
