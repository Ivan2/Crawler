package crawler;

import data.AverageCount;
import data.Info;
import db.PostgresQLManager;
import load.ILoadDataManager;

public class Crawler {

	private AverageCount[] averageCountArray;
	private Info info;

	public Crawler() {
		ILoadDataManager loadDataManager = new PostgresQLManager();
		loadDataManager.connect();
		averageCountArray = loadDataManager.loadAverageCount();
		info = loadDataManager.loadInfo();
	}

	public AverageCount[] getAverageCountArray() {
		return averageCountArray;
	}

	public Info getInfo() {
		return info;
	}

}
