package load;

import data.AverageCount;
import data.Info;

public interface ILoadDataManager {

	void connect();

	AverageCount[] loadAverageCount();

	Info loadInfo();

}
