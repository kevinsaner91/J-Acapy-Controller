package simplewebapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.owlike.genson.Genson;

import simplewebapp.beans.ActiveConnection;
import simplewebapp.beans.ProofExchangeRecord;
import simplewebapp.beans.Result;

@WebServlet(urlPatterns = { "/proofRequests" })
public class ProofRequestServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ProofRequestServlet() {
		super();
	}

	// Show Login page.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://alice-api.educa.ch/present-proof/records");
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

			//ActiveConnection activeConnection = new Genson().deserialize(httpcontent.get(0), ActiveConnection.class);

			JSONParser parser = new JSONParser();

			try {
				JSONObject respones = (JSONObject) parser.parse(httpcontent.get(0));
				JSONArray array = (JSONArray) respones.get("results");
				
				System.out.println(array.toString());

				Iterator<JSONObject> iterator = array.iterator();
				List<ProofExchangeRecord> proofExchangeRecords = new ArrayList<ProofExchangeRecord>();

				while (iterator.hasNext()) {
					
					JSONObject pER = (JSONObject) iterator.next();
					
					System.out.println(pER.toString());
					
					ProofExchangeRecord proofExchangeRecord = new ProofExchangeRecord(pER.get("role").toString(),
							pER.get("connection_id").toString(), pER.get("created_at").toString(),
							pER.get("state").toString(), pER.get("presentation_exchange_id").toString());
					proofExchangeRecords.add(proofExchangeRecord);

				}
				request.setAttribute("proofExchangeList", proofExchangeRecords);
				
			}catch(ParseException e) {
				
			}

			String errorString = null;

			// Store info in request attribute, before forward to views
			request.setAttribute("errorString", errorString);
			// Forward to /WEB-INF/views/loginView.jsp
			// (Users can not access directly into JSP pages placed in WEB-INF)
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/views/proofRequestsView.jsp");

			dispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/activeView.jsp");

		dispatcher.forward(request, response);

	}
	


}
