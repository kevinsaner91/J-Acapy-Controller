package simplewebapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.owlike.genson.Genson;

import simplewebapp.beans.ActiveConnection;
import simplewebapp.beans.Attrs;
import simplewebapp.beans.CredentialResult;
import simplewebapp.beans.Credentials;
import simplewebapp.beans.Result;

@WebServlet(urlPatterns = { "/credentials" })
public class CredentialsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public CredentialsServlet() {
		super();
	}

	// Show Login page.
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

			Credentials credentials = new Genson().deserialize(httpcontent.get(0), Credentials.class);

			List<CredentialResult> credeList = credentials.getResults();
			List<Attrs> attrs = new ArrayList<Attrs>();
			List<String> referent = new ArrayList<String>();
			
			for(CredentialResult cr : credeList ) {
				referent.add(cr.getReferent());
				attrs.add(cr.getAttrs());
			}

			String errorString = null;

			// Store info in request attribute, before forward to views
			request.setAttribute("errorString", errorString);
			request.setAttribute("referent_id", referent);
			request.setAttribute("credentialList", attrs);
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

}
