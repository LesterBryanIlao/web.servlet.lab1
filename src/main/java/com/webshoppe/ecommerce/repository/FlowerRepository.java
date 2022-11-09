package com.webshoppe.ecommerce.repository;

import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;

public class FlowerRepository extends ProductRepository{
	private static final String TABLE = "FlowersDetails";
	private static final String ID = "fid";
	private static final String NAME = "fname";
	private static final String DESCRIPTION = "fdesc";
	private static final String PRICE = "fprice";

	public FlowerRepository(JdbcConnectionManager jdbcConnectionManager) {
		super(jdbcConnectionManager, TABLE, ID, NAME, DESCRIPTION, PRICE);

	}

}
