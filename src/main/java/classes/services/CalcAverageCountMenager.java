package classes.services;

import interfaces_abstracts.data.IPostInfo;

import java.util.List;

public class CalcAverageCountMenager {

	public static void calcAverageCount(List<IPostInfo> postInfoList,
	                             long[] averageCommentsCountOfDayOfWeek,
	                             long[] averageLikesCountOfDayOfWeek,
	                             long[] averageRepostsCountOfDayOfWeek) {

		long[] commentsCountOfDayOfWeek = new long[7];
		long[] likesCountOfDayOfWeek = new long[7];
		long[] repostsCountOfDayOfWeek = new long[7];
		long[] countOfDayOfWeek = new long[7];

		for (IPostInfo postInfo : postInfoList) {
			byte dayOfWeek = postInfo.getDayOfWeek();

			commentsCountOfDayOfWeek[dayOfWeek] += postInfo.getCommentsCount();
			likesCountOfDayOfWeek[dayOfWeek] += postInfo.getLikesCount();
			repostsCountOfDayOfWeek[dayOfWeek] += postInfo.getRepostsCount();

			countOfDayOfWeek[dayOfWeek] ++;
		}

		for (int i=0; i<7; i++)
			if (countOfDayOfWeek[i] != 0) {
				averageCommentsCountOfDayOfWeek[i] = commentsCountOfDayOfWeek[i]
						/ countOfDayOfWeek[i];
				averageLikesCountOfDayOfWeek[i] = likesCountOfDayOfWeek[i]
						/ countOfDayOfWeek[i];
				averageRepostsCountOfDayOfWeek[i] = repostsCountOfDayOfWeek[i]
						/ countOfDayOfWeek[i];
			}
	}

}
