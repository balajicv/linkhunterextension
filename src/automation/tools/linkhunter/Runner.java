package automation.tools.linkhunter;

import java.util.Date;

public class Runner {
	public Date start =null;
	public Date end =null;
	public boolean started = false;
	public boolean ended = false;
	public Reporter reporter = null;
	
	
	public void start()
	{
		started = true;
		start = new Date();
		reporter = new Reporter();
		reporter.start(start);
	}
	
	public void append(String report,String status,String snap){
		reporter.log(report,status,snap);
	}
	
	public void end()
	{
		ended = true;
		end = new Date();
		reporter.end(end);
	}
}
