package classes.vk;

import classes.ioc.IoCAdapter;
import interfaces_abstracts.vk.*;

public class VkWallLoader implements IVkWallLoader {

	private final byte POST_COUNT = 100;
	private final String METHOD = "wall.get";
	private final String PARAMS = "owner_id=-%s&count=%d&";

	public String loadWall(String wallID) {
		String params = String.format(PARAMS, wallID, POST_COUNT);
		IVkLoader loader = IoCAdapter.getInstance().getIVkLoaderObject();
		return loader.loadData(METHOD, params);
	}

}
