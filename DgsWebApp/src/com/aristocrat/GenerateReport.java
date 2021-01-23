package com.aristocrat;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class renerateReport
 */
@WebServlet("/GenerateReport")
public class GenerateReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String UPLOAD_DIRECTORY;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		// Get the file location where it would be stored.
		UPLOAD_DIRECTORY = getServletContext().getInitParameter("file-upload");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		// doGet(request, response);
		if (request.getParameter("Submit") != null) {
			// update button is clicked
			// Do the update action or forward the request to the servlet to do update
			// action

			String startDir = request.getParameter("versionType");
			File dir = new File(UPLOAD_DIRECTORY + startDir + "/");
			Collection<File> f = listFileTree(dir);
			try {
				String message = "";
				message = "<html><body><form><center><table border=\"1\">" + "	<tr><td>Game Name</td>"
						+ "	<td>Total Cases</td>" + "	<td>Total Failed</td>" + " <td>Total Times(Hrs.)</td></tr>";

				Iterator<File> itr = f.iterator();
				message = message + "============================</br>";
				message = message + "API/Recovery Report :" + request.getParameter("versionType") + " </br>";
				message = message + "============================</br>";
				while (itr.hasNext()) {
					File inputFile = (File) itr.next();
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					NodeList nList = doc.getElementsByTagName("testsuites");
					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							message = message + "<tr><td style =font-weight:normal>" + eElement.getAttribute("name")
									+ "</td>";
							message = message + "<td style =font-weight:normal>" + eElement.getAttribute("tests")
									+ "</td>";
							message = message + "<td style =font-weight:normal>" + eElement.getAttribute("failures")
									+ "</td>";
							message = message + "<td style =font-weight:normal>"
									+ Float.parseFloat(eElement.getAttribute("time")) / 3600 + "</td></tr>";
						}

					}
				}
				/*
				 * message = message +
				 * "<td colspan=\"2\" style=\"text-align: center\"><a href=\"generateReport.jsp\">Back</a></td>"
				 * ; message = message +
				 * "<td colspan=\"2\" style=\"text-align: center\"><a href=\"index.jsp\">Home</a></td>"
				 * ;
				 */
				message = message + "</table></center></form></body></html>";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/result.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (request.getParameter("Back") != null) {
			response.sendRedirect("index.jsp");
		}
	}

	public static Collection<File> listFileTree(File dir) {
		Set<File> fileTree = new HashSet<File>();
		if (dir == null || dir.listFiles() == null) {
			return fileTree;
		}
		for (File entry : dir.listFiles()) {
			if (entry.isFile())
				fileTree.add(entry);
			else
				fileTree.addAll(listFileTree(entry));
		}
		return fileTree;
	}

}
