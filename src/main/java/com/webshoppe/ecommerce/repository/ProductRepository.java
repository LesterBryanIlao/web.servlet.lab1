package com.webshoppe.ecommerce.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webshoppe.ecommerce.entity.Product;
import com.webshoppe.ecommerce.exception.DataAccessException;
import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;

public class ProductRepository {
	private final JdbcConnectionManager jdbcConnectionManager;
	private final String tableName;
	private final String idColumn;
	private final String nameColumn;
	private final String descriptionColumn;
	private final String priceColumn;

	protected ProductRepository(JdbcConnectionManager jdbcConnectionManager, String tableName, String idColumn,
			String nameColumn, String descriptionColumn, String priceColumn) {

		this.jdbcConnectionManager = jdbcConnectionManager;
		this.tableName = tableName;
		this.idColumn = idColumn;
		this.nameColumn = nameColumn;
		this.descriptionColumn = descriptionColumn;
		this.priceColumn = priceColumn;
	}

	public List<Product> findAll() {
		try {
			final Connection connection = jdbcConnectionManager.getConnection();

			final PreparedStatement findAllQuery = connection.prepareStatement(generateFindAllString());

			final ResultSet resultSet = findAllQuery.executeQuery();
			final List<Product> toys = new ArrayList<>();
			while (resultSet.next()) {
				toys.add(this.createInstance(resultSet));
			}

			return toys;
		} catch (Exception e) {
			throw DataAccessException.instance("failed_to_retrieve_product");
		}
	}

	public List<Product> findByPrice(BigDecimal minimumPrice, BigDecimal maximumPrice) {
		final List<Product> toys = new ArrayList<>();
		try {
			final Connection connection = jdbcConnectionManager.getConnection();

			final PreparedStatement findAllQuery = connection.prepareStatement(generateFindByPriceString());
			findAllQuery.setBigDecimal(1, minimumPrice);
			findAllQuery.setBigDecimal(2, maximumPrice);

			final ResultSet resultSet = findAllQuery.executeQuery();

			while (resultSet.next()) {

				toys.add(this.createInstance(resultSet));
			}

			return toys;
		} catch (Exception e) {
			throw DataAccessException.instance("failed_to_retrieve_products_by_price");
		}
	}

	private Product createInstance(ResultSet resultSet) throws SQLException {
		return new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
				resultSet.getBigDecimal(4));
	}

	private String generateFindAllString() {

		return String.format("SELECT %s, %s, %s, %s FROM %s", idColumn, nameColumn, descriptionColumn, priceColumn, tableName);

	}

	private String generateFindByPriceString() {
		return String.format("%s WHERE %s BETWEEN ? AND ?", generateFindAllString(), priceColumn);
	}
}
