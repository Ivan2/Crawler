package abstractions.json;

import abstractions.data.IPostInfo;

public interface IJSONParser {

	IPostInfo[] parse(String wall);

}
