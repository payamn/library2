package domain.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import domain.mail.MailSender;

public class ExecuteJobWarning implements Job {

	public void execute(JobExecutionContext exeInfo) throws JobExecutionException {
		System.out.println("IN execute job warning");
		System.out.println("auction ID: "+exeInfo.getJobDetail().getJobDataMap().getInt("auctionId"));
		MailSender.sendAuctionWarningMailToOwner(exeInfo.getJobDetail().getJobDataMap().getString("mail"),exeInfo.getJobDetail().getJobDataMap().getString("bookName"),exeInfo.getJobDetail().getJobDataMap().getString("personName"));
	}
}
	

