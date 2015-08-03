package classes.services;

import classes.crawler.Control;
import classes.ioc.IoCAdapter;
import interfaces_abstracts.data.IPostInfo;
import interfaces_abstracts.date.IDateTime;
import interfaces_abstracts.db.FinalDB;
import interfaces_abstracts.db.IntermediateDB;
import interfaces_abstracts.services.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class SaveToFinalDBService extends Service {

	private IntermediateDB intermediateDB;
	private FinalDB finalDB;

	public SaveToFinalDBService(IntermediateDB intermediateDB,
	                            FinalDB finalDB, String rabbitMQHost,
	                            String consumerQName, String producerQName)
			throws TimeoutException, IOException {

		super(rabbitMQHost, consumerQName, producerQName);
		this.intermediateDB = intermediateDB;
		this.finalDB = finalDB;
		start();
	}

	@Override
	public void run() {
		while (true) {

			List<IPostInfo> postInfoList = intermediateDB.getPostInfoList();

			long[] averageCommentsCountOfDayOfWeek = new long[7];
			long[] averageLikesCountOfDayOfWeek = new long[7];
			long[] averageRepostsCountOfDayOfWeek = new long[7];

			CalcAverageCountMenager.calcAverageCount(postInfoList,
					averageCommentsCountOfDayOfWeek,
					averageLikesCountOfDayOfWeek,
					averageRepostsCountOfDayOfWeek);

			saveAverageDataToDB(averageCommentsCountOfDayOfWeek,
					averageLikesCountOfDayOfWeek, averageRepostsCountOfDayOfWeek);

			saveInfoToDB(postInfoList.size());

			try {
				Thread.sleep(600000);
			} catch (InterruptedException e) {
				Control.log(e.toString());
			}
		}
	}

	@Override
	public Service copyService() throws TimeoutException, IOException {
		return new SaveToFinalDBService(intermediateDB, finalDB, rabbitMQHost,
				consumerQName, producerQName);
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
		IDateTime dateTime = IoCAdapter.getInstance().getIDateTimeObject();
		String now = dateTime.now();

		int updateCount = finalDB.setUpdateInfo(now);
		if (updateCount == 0)
			finalDB.addUpdateInfo(now);

		updateCount = finalDB.setPostCountInfo(postCount);
		if (updateCount == 0)
			finalDB.addPostCountInfo(postCount);

		Control.log("Save info to final DB. Date: "+now+". PostCount: "+postCount);
	}


}
