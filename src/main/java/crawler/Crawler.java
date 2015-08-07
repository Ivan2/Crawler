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
		//averageCountArray = new AverageCount[7];
		//for (int i=0; i<7; i++)
		//	averageCountArray[i] = new AverageCount(i, i+1, i+2);
		//info = new Info("qdqwdwqd", "42");
	}

	public AverageCount[] getAverageCountArray() {
		return averageCountArray;
	}

	public Info getInfo() {
		return info;
	}

}
