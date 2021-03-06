package domain.scheduler;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author onlinetechvision.com
 * @since 17 Sept 2011
 * @version 1.0.0
 *
 */
public class JobScheduler {
	
	public static void createNewJobEnd(Date endDate, int auctionId,String bookName,String name) {
		System.out.println("in CreateNewJob");
		System.out.println(endDate.toString());
		System.out.println("auctionID:   "+auctionId);
		try {
			// specify the job' s details..
			JobDetail job = JobBuilder.newJob(ExecuteJobFinish.class)
					.withIdentity("lib")
					.build();
			// specify the running period of the job
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.startAt(endDate)
					.build();
			//schedule the job
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();
			job.getJobDataMap().put("auctionId", auctionId);
			job.getJobDataMap().put("bookName", bookName);
			job.getJobDataMap().put("personName", name);
			sch.start();
			sch.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			System.out.println("job scheduler handle error");
			e.printStackTrace();	
		}
	System.out.println("End Create new job");
	}
	public static void createNewJobWarning(Date endDate, int auctionId,String bookName,String name,String mail) {
		System.out.println("in CreateNewJobWarning");
		System.out.println(endDate.toString());
		System.out.println("auctionID:   "+auctionId);
		try {
			// specify the job' s details..
			JobDetail job = JobBuilder.newJob(ExecuteJobWarning.class)
					.withIdentity("warn")
					.build();
			// specify the running period of the job
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger2", "group2")
					.startAt(endDate)
					.build();
			//schedule the job
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();
			job.getJobDataMap().put("auctionId", auctionId);
			job.getJobDataMap().put("bookName", bookName);
			job.getJobDataMap().put("personName", name);
			job.getJobDataMap().put("mail", mail);
			sch.start();
			sch.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			System.out.println("job scheduler handle error");
			e.printStackTrace();	
		}
	System.out.println("End Create new job");
	}
}