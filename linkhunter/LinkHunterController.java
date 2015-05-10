package wiqa.automation.tools.linkhunter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.sun.net.httpserver.*;

public class LinkHunterController 
{
	
	public static void main(String[] args) 
	{
		try
		{
			Thread server = new Thread(new Server());	
			Thread browser = new Thread(new LaunchBrowser());
			server.start();
			browser.start();			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	} 
};

class MyHandler implements HttpHandler {
	
    @SuppressWarnings("null")
	public void handle(HttpExchange t) throws IOException 
    {
    	URI str = t.getRequestURI();
    	String requestedURL=str.getRawPath();
    	System.out.println(requestedURL); 
    	String response=null;
    	Runner runner =null;
    	
    	if(requestedURL.equalsIgnoreCase("/"))
    		response ="<html><body><h1>hi there welcome</h1></body></html>";
    	else if(requestedURL.equalsIgnoreCase("/started"))
    	{
    		runner=new Runner();
    		runner.start();
      		response="Ticker initiated";
    	}  
    	else if(requestedURL.equalsIgnoreCase("/log"))
    	{
    		InputStream is = t.getRequestBody();
    		Document body = readXml(is);
    		runner.append(body.getElementsByTagName("step").toString(), body.getElementsByTagName("status").toString(), body.getElementsByTagName("snap").toString());
    		response="Ticker initiated";
    	}
    	else if(requestedURL.equalsIgnoreCase("/ended"))
    	{
    		runner.end();
    		response="Ticker initiated";
    	}
    	else if(requestedURL.equalsIgnoreCase("/sendmail"))
    	{
    		new Mail(runner).send();
    		response="Ticker initiated";
    	}
        //InputStream is = t.getRequestBody();
        //read(is);  //.. read the request body
        //String response = "This is the response";
        //String response ="<html><body><h1>hi there welcome</h1></body></html>";
    	Headers headers = t.getResponseHeaders();
    	headers.add("Access-Control-Allow-Origin", "*");    	
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
    public static Document readXml(InputStream is){
    	
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    Document d = null;
	
	    dbf.setValidating(false);
	    dbf.setIgnoringComments(false);
	    dbf.setIgnoringElementContentWhitespace(true);
	    dbf.setNamespaceAware(true);
	    // dbf.setCoalescing(true);
	    // dbf.setExpandEntityReferences(true);
	
	    DocumentBuilder db = null;
	    try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //db.setEntityResolver(new NullResolver());
	
	    // db.setErrorHandler( new MyErrorHandler());
	
	    try {
			return db.parse(is);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    return d;
}
    
}

class Server implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			HttpServer server = HttpServer.create(new InetSocketAddress(4567), 0);
			server.createContext("/",new MyHandler());
			server.setExecutor(null);
			server.start();
			System.out.println("Server listening on localhost 4567...");			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
}