package automation.tools.linkhunter;

import java.io.IOException;

public class LaunchBrowser implements Runnable {
	public void run() {
		Process p;
		try {
			p = Runtime.getRuntime().exec("C:\\Balaji\\Projects\\LinkHunter\\lib\\test.bat");		
		    p.waitFor();
		    System.out.println("Google Chrome launched!");
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
