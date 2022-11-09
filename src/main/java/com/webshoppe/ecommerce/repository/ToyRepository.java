package com.webshoppe.ecommerce.repository;

import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;

public class ToyRepository extends ProductRepository {
	private static final String TABLE_NAME = "ToysDetails";
	private static final String ID_COLUMN = "tid";
	private static final String NAME_COLUMN = "tname";
	private static final String DESCRIPTION_COLUMN = "tdesc";
	private static final String PRICE_COLUMN = "tprice";

	public ToyRepository(JdbcConnectionManager jdbcConnectionManager) {
		super(jdbcConnectionManager, TABLE_NAME, ID_COLUMN, NAME_COLUMN, DESCRIPTION_COLUMN, PRICE_COLUMN);
	}
}
