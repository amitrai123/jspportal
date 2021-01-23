package com.aristocrat;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024
		* 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {
	private String UPLOAD_DIRECTORY;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String gameName = request.getParameter("gameName");
		String resultType = request.getParameter("resultType");
		String versionType = request.getParameter("versionType");
		String path = (UPLOAD_DIRECTORY + versionType + "/" + resultType + "/" + gameName + "/");

		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				String fileName = "";
				for (Part part : request.getParts()) {
					fileName = extractFileName(part);
					// refines the fileName in case it is an absolute path
					fileName = new File(fileName).getName();
					if (fileName != null && !"".equals(fileName)) {
						part.write(path + File.separator + fileName);
					}
				}

				request.setAttribute("message", "Files uploaded successfully.");
			} catch (Exception ex) {
				request.setAttribute("message", "Files upload failed due to : " + ex);
			}

		} else {
			request.setAttribute("message", "Sorry this servlet only handles file upload request.");
		}

		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

}
