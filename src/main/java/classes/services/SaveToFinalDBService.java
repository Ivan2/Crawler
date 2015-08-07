package classes.services;

import abstractions.data.IPostInfo;
import abstractions.date.IDateTime;
import abstractions.db.FinalDB;
import abstractions.db.IntermediateDB;
import abstractions.queue.IQueue;
import abstractions.services.Service;
import classes.crawler.Control;
import classes.service_locator.ServiceLocatorAdapter;

import java.util.List;

public class SaveToFinalDBService extends Service {

	private IntermediateDB intermediateDB;
	private FinalDB finalDB;

	public SaveToFinalDBService(IntermediateDB intermediateDB, FinalDB finalDB,
	                            IQueue consumerQueue, IQueue producerQueue) {

		super(consumerQueue, producerQueue);
		this.intermediateDB = intermediateDB;
		this.finalDB = finalDB;
		start();
	}

	@Override
	protected void threadBody() {
		List<IPostInfo> postInfoList = intermediateDB.getPostInfoList();

		long[] averageCommentsCountOfDayOfWeek = new long[7];
		long[] averageLikesCountOfDayOfWeek = new long[7];
		long[] averageRepostsCountOfDayOfWeek = new long[7];

		CalcAverageCountManager.calcAverageCount(postInfoList,
				averageCommentsCountOfDayOfWeek,
				averageLikesCountOfDayOfWeek,
				averageRepostsCountOfDayOfWeek);

		saveAverageDataToDB(averageCommentsCountOfDayOfWeek,
				averageLikesCountOfDayOfWeek, averageRepostsCountOfDayOfWeek);

		saveInfoToDB(postInfoList.size());

		try {
			Thread.sleep(600000);
		} catch (InterruptedException e) {
			Control.error(getClass().getName(), e.toString());
		}
	}

	private void saveAverageDataToDB(long[] averageCommentsCountOfDayOfWeek,
	                                 long[] averageLikesCountOfDayOfWeek,
	                                 long[] averageRepostsCountOfDayOfWeek) {

		for (byte i=0; i<7; i++) {
			int updateCount = finalDB.setAverageDataOfDayOfWeek(i,
					averageCommentsCountOfDayOfWeek[i],
					averageLikesCountOfDayOfWeek[i],
					averageRepostsCountOfDayOfWeek[i]);
			if (updateCount == 0)
				finalDB.addAverageDataOfDayOfWeek(i,
						averageCommentsCountOfDayOfWeek[i],
						averageLikesCountOfDayOfWeek[i],
						averageRepostsCountOfDayOfWeek[i]);
		}
	}

	private void saveInfoToDB(long postCount) {
		IDateTime dateTime = ServiceLocatorAdapter.getInstance().getObject(IDateTime.class);
		String now = dateTime.now();

		int updateCount = finalDB.setUpdateInfo(now);
		if (updateCount == 0)
			finalDB.addUpdateInfo(now);

		updateCount = finalDB.setPostCountInfo(postCount);
		if (updateCount == 0)
			finalDB.addPostCountInfo(postCount);

		Control.info(getClass().getName(), "Save info to final DB. Date: "
				+ now + ". PostCount: " + postCount);
	}

	@Override
	public Service copyService() {
		return new SaveToFinalDBService(intermediateDB, finalDB, consumerQueue,
				producerQueue);
	}

}
