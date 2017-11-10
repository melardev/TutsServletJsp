package com.melardev.tutsservlet.servlets;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/api/books/*")
public class ServletWS extends HttpServlet {

	private static class Book {

		public String title;
		public String author;
		public String isbn;

		public Book(String title, String author, String isbn) {
			this.title = title;
			this.author = author;
			this.isbn = isbn;
		}

	}

	static CopyOnWriteArrayList<Book> books;
	static {
		books = new CopyOnWriteArrayList<Book>();
		books.add(new Book("Murach's Java Servlets and JSP, 3rd Edition",
				"Joel Murach (Author) & Michael Urban (Author)", "978-1890774783"));
		books.add(new Book("Head First Servlets and JSP", "Bryan Basham, Kathy Sierra, Bert Bates",
				"978-0596516680"));
		books.add(new Book("Java Servlet Programming", "Jason Hunter, William Crawford", "978-0596000400"));
		books.add(new Book("Core Servlets and Javaserver Pages", "by Marty Hall (Author), Larry Brown (Author)",
				"978-0130092298"));
		books.add(new Book("Java Servlet & JSP Cookbook", "Bruce W. Perry", "978-0596005726"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String json;
		Gson gson = new Gson();

		Book book = getBookFromUrl(request.getRequestURL());
		if (book != null)
			json = gson.toJson(book);
		else
			json = gson.toJson(books);
		response.setContentType("application/json");
		response.getWriter().println(json);
	}

	private Book getBookFromUrl(StringBuffer requestUrl) {
		Pattern pattern = Pattern.compile("books/(\\d+)");
		Matcher matcher = pattern.matcher(requestUrl);
		Book book = null;
		if (matcher.find()) {
			try {
				int position = Integer.parseInt(matcher.group(1));
				System.out.println("Requested : " + position);
				book = books.get(position);
			} catch (NumberFormatException e) {
			} catch (IndexOutOfBoundsException outEx) {
			}
		}
		return book;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		Gson gson = new Gson();
		if (Pattern.matches(".*api/books/$", request.getRequestURL())) {
			InputStreamReader reader = new InputStreamReader(request.getInputStream());
			Book book = gson.fromJson(reader, Book.class);
			books.add(book);
			reader.close();
			response.setContentType("application/json");
			response.getWriter().println(gson.toJson(book, Book.class));
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		Book book = getBookFromUrl(request.getRequestURL());
		if (book != null) {
			Gson gson = new Gson();
			Book newBook = gson.fromJson(new InputStreamReader(request.getInputStream()), Book.class);
			book.author = newBook.author;
			book.title = newBook.title;
			book.isbn = newBook.isbn;
			response.setContentType("application/json");
			response.getWriter().print(gson.toJson(book));
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		Gson gson = new Gson();

		Book book = getBookFromUrl(request.getRequestURL());
		if (book != null)
			books.remove(book);
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(book));
	}

}
