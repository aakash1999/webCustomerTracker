package com.test.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = "root";
		String pass = "root";

		String jdbcUrl = "jdbc:mysql://localhost:3310/crm?useSSL=false&serverTimezone=UTC";
		String className = "com.mysql.jdbc.Driver";
		PrintWriter out = response.getWriter();
		try {
			out.println("Connecting to the database");
			Class.forName(className);
			Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);
			out.println("Successfully connected to the database");
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.println(e.getMessage());
		}
	}

}
