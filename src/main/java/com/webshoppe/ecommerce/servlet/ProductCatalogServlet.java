package com.webshoppe.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webshoppe.ecommerce.entity.Product;
import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;
import com.webshoppe.ecommerce.repository.BookRepository;
import com.webshoppe.ecommerce.repository.FlowerRepository;
import com.webshoppe.ecommerce.repository.ToyRepository;
import com.webshoppe.ecommerce.service.ProductCatalogService;

public class ProductCatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductCatalogService toyCatalogService;
	private ProductCatalogService flowerCatalogService;
	private ProductCatalogService bookCatalogService;

	@Override
	public void init(ServletConfig config) throws ServletException {

		final JdbcConnectionManager jdbcConnectionManager = new JdbcConnectionManager();
		toyCatalogService = new ProductCatalogService(new ToyRepository(jdbcConnectionManager));
		flowerCatalogService = new ProductCatalogService(new FlowerRepository(jdbcConnectionManager));
		bookCatalogService = new ProductCatalogService(new BookRepository(jdbcConnectionManager));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		final List<Product> products = getProductCatalogService(request).getProductCatalog();
		showProducts(products, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		final BigDecimal minimumPrice = new BigDecimal(request.getParameter("minimum-price"));
		final BigDecimal maximumPrice = new BigDecimal(request.getParameter("maximum-price"));

		final List<Product> products = getProductCatalogService(request).getProductCatalog(minimumPrice, maximumPrice);

		showProducts(products, response);
	}

	private ProductCatalogService getProductCatalogService(HttpServletRequest request) {
		ProductCatalogService catalogService = null;
		String category = request.getParameter("catalogs");

		if (category.equals("toys")) {
			catalogService = toyCatalogService;
		} else if (category.equals("books")) {
			catalogService = bookCatalogService;
		} else if (category.equals("flowers")) {
			catalogService = flowerCatalogService;
		}
		return catalogService;

	}

	private void showProducts(List<Product> products, HttpServletResponse response) throws IOException {
		final StringBuilder stringBuilder = new StringBuilder();
		if (products.isEmpty()) {
			stringBuilder.append("<b>Cannot find items that met the price range.</b>");
		} else {
			stringBuilder.append("<table class='table'>");
			stringBuilder.append("<thead>");
			stringBuilder.append("<th scope='col'>ID</th>");
			stringBuilder.append("<th scope='col'>Name</th>");
			stringBuilder.append("<th scope='col'>Description</th>");
			stringBuilder.append("<th scope='col'>Price</th>");
			stringBuilder.append("</thead>");
			products.forEach(e -> {
				stringBuilder.append("<tr scope='row'>");
				stringBuilder.append("<td>").append((e).getId()).append("</td>");
				stringBuilder.append("<td>").append((e).getName()).append("</td>");
				stringBuilder.append("<td>").append((e).getDescription()).append("</td>");
				stringBuilder.append("<td>").append((e).getPrice()).append("</td>");
				stringBuilder.append("</tr>");
			});
			stringBuilder.append("</table>");
		}

		PrintWriter out = response.getWriter();
		out.println(stringBuilder.toString());
		out.flush();
		out.close();
	}

}
