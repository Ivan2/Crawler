package crawler;

import data.Data;
import db.LoadDBManager;
import load.ILoadDataManager;

import java.util.Date;

public class Crawler {

	private long[][] averageCount;
	private String update;

	public Crawler() {
		ILoadDataManager loadDataManager = new LoadDBManager();
		Data data = loadDataManager.loadData();

		averageCount = Calc.calcAverageCount(data);

		update = new Date(data.getUpdate()).toString();
	}

	public long[][] getAverageCount() {
		return averageCount;
	}

	public String getUpdate() {
		return update;
	}

}
