package simplewebapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import simplewebapp.beans.Credential;





@WebServlet(urlPatterns = { "/credentials" })
public class CredentialsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public CredentialsServlet() {
		super();
	}

	// Show Login page.
	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://alice-api.educa.ch/credentials");
		HttpResponse httpresponse = httpclient.execute(httpget);
		Scanner sc = new Scanner(httpresponse.getEntity().getContent());

		System.out.println(httpresponse.getStatusLine());
		if (httpresponse.getStatusLine().toString().contains("200")) {
			List<String> httpcontent = new ArrayList<String>();
			while (sc.hasNext()) {
				String nextLine = sc.nextLine();
				System.out.println(nextLine);
				httpcontent.add(nextLine);
			}
		
			JSONParser parser = new JSONParser();

			try {
				JSONObject credentials = (JSONObject) parser.parse(httpcontent.get(0));
				JSONArray array = (JSONArray) credentials.get("results");

				Iterator<JSONObject> iterator = array.iterator();
				List<String> keyValues = new ArrayList();
				
				while (iterator.hasNext()) {
					JSONObject credentialJSON = (JSONObject) iterator.next();
					JSONObject attrs = (JSONObject) credentialJSON.get("attrs");
					System.out.println(attrs);
					
					Iterator<String> keys = attrs.keySet().iterator();
					while(keys.hasNext()) {
						keyValues.add(keys.next());
					}
					
					Iterator<String> values = attrs.values().iterator();
					while(values.hasNext()) {
						keyValues.add(values.next());
					}
					
					Credential credential = new Credential(credentialJSON.get("referent").toString(), keyValues.get(0), keyValues.get(1), keyValues.get(2),
							keyValues.get(3), keyValues.get(4), keyValues.get(5), keyValues.get(6), keyValues.get(7),
							keyValues.get(8), keyValues.get(9));
					
					System.out.println(credential.toString());
					
					request.setAttribute("credentialList", credential);

				}
			} catch (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
			}

			String errorString = null;

			// Store info in request attribute, before forward to views
			request.setAttribute("errorString", errorString);
			
		}

		// Forward to /WEB-INF/views/loginView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/credentialsView.jsp");

		dispatcher.forward(request, response);


	}

	// When the user enters userName & password, and click Submit.
	// This method will be executed.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	
	private String getValue() {
		
		//check here if out of bounds and return an empty string if so
		return "";
	}

}
