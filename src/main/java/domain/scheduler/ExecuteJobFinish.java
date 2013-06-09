package domain.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import domain.model.Library;

public class ExecuteJobFinish implements Job{

	public void execute(JobExecutionContext exeInfo) throws JobExecutionException {
		System.out.println("IN execute job");
		System.out.println("auction ID: "+exeInfo.getJobDetail().getJobDataMap().getInt("auctionId"));
		
		try {
			Library.finishExpiredAuction(exeInfo.getJobDetail().getJobDataMap().getInt("auctionId"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}