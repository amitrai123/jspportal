package com.aristocrat;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

/**
 * Servlet implementation class testrailReport
 */
@WebServlet("/testrailReport")
public class TestrailReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestrailReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @throws IOException
	 * @throws MalformedURLException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, MalformedURLException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		if (request.getParameter("Submit") != null) {
			
		
		APIClient client = new APIClient("https://productmadness.testrail.com");
		client.setUser("amit.rai@ali.com.au");
		client.setPassword("Amit.123#");
		System.out.println("Login Successfully");
		JSONObject c;
		try {
			String message = "";
			String url = request.getParameter("testRailUrl");
			int x = url.lastIndexOf("/");
			url = url.substring(x);
			
			//Object a = (Object) client.sendGet("get_results/" + url);
			//System.out.println(a);
			c = (JSONObject) client.sendGet("get_plan/" + url);
			//c = (JSONObject) client.sendGet("get_run/" + url);
			//System.out.println(c);
			System.out.println("name: " + c.get("name"));
			System.out.println("Project ID: " + c.get("project_id"));
			// System.out.println("Test Suite: "+c.get("entries"));
			JSONArray testSuiteJsonArray = (JSONArray) c.get("entries");
			message = "<html><body><form><center><table border=\"1\">" + " <tr><td>Game Name</td>"
					+ "	<td>Total Cases</td>" + "	<td>Total Failed</td>" + " <td>Not Applicable </td>"
					+ " <td>Pass/Warning</td>" + " <td>Block</td>" + " <td>Could Not Test</td></tr>";
			message = message + "============================</br>";
			message = message + "Test Rail Report :" + c.get("name") + " </br>";
			message = message + "============================</br>";
			int passCount = 0;
			int failCount = 0;
			int notApplicable = 0;
			int passWithWarning = 0;
			int block = 0;
			int counldNotTest = 0;
			for (int i = 0; i < testSuiteJsonArray.size(); i++) {
				JSONObject testSuite = (JSONObject) testSuiteJsonArray.get(i);
				JSONArray testRunJsonArray = (JSONArray) testSuite.get("runs");

				for (int j = 0; j < testRunJsonArray.size(); j++) {
					JSONObject testRun = (JSONObject) testRunJsonArray.get(j);
					message = message + "<tr><td style =font-weight:normal>" + testSuite.get("name") + "</td>";
					message = message + "<td style =font-weight:normal>" + testRun.get("passed_count") + "</td>";
					message = message + "<td style =font-weight:normal>" + testRun.get("failed_count") + "</td>";
					message = message + "<td style =font-weight:normal>" + testRun.get("custom_status2_count") + "</td>";
					message = message + "<td style =font-weight:normal>" + testRun.get("custom_status5_count") + "</td>";
					message = message + "<td style =font-weight:normal>" + testRun.get("blocked_count") + "</td>";
					message = message + "<td style =font-weight:normal>" + testRun.get("custom_status1_count") + "</td></tr>";
					
					passCount = passCount + Integer.parseInt(testRun.get("passed_count").toString());
					failCount = failCount + Integer.parseInt(testRun.get("failed_count").toString());
					notApplicable = notApplicable + Integer.parseInt(testRun.get("custom_status2_count").toString());
					passWithWarning = passWithWarning + Integer.parseInt(testRun.get("custom_status5_count").toString());
					block = block + Integer.parseInt(testRun.get("blocked_count").toString());
					counldNotTest = counldNotTest + Integer.parseInt(testRun.get("custom_status1_count").toString());
				}
			}
			int total = passCount+failCount+notApplicable+passWithWarning+block+counldNotTest;
			message = message + "<tr><td style =font-weight:normal><b>" + "Total: " +total+ "</b></td>";
			message = message + "<td style =font-weight:normal><b>" + passCount + "</b></td>";
			message = message + "<td style =font-weight:normal><b>" + failCount + "</b></td>";
			message = message + "<td style =font-weight:normal><b>" + notApplicable + "</b></td>";
			message = message + "<td style =font-weight:normal><b>" + passWithWarning + "</b></td>";
			message = message + "<td style =font-weight:normal><b>" + block + "</b></td>";
			message = message + "<td style =font-weight:normal><b>" + counldNotTest + "</b></td></tr>";
			message = message + "</table></center></form></body></html>";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/result.jsp").forward(request, response);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} else if (request.getParameter("Back") != null) {
		response.sendRedirect("index.jsp");
	}
	}
}
