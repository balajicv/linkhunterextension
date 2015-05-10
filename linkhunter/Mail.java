package wiqa.automation.tools.linkhunter;

public class Mail {
	Reporter r = null;
	
	Mail(Runner r)
	{
		this.r=r.reporter;
	}
	
	void send()
	{
		String report = r.getReport();
		//send report;
	}
}
