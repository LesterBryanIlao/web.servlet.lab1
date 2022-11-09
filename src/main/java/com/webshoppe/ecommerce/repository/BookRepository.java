package com.webshoppe.ecommerce.repository;

import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;

public class BookRepository extends ProductRepository {
	private static final String TABLE = "BooksDetails";
	private static final String ID = "bid";
	private static final String NAME = "title";
	private static final String DESCRIPTION = "bookdesc";
	private static final String PRICE = "bookprice";

	public BookRepository(JdbcConnectionManager jdbcConnectionManager) {
		super(jdbcConnectionManager, TABLE, ID, NAME, DESCRIPTION, PRICE);

	}

}
