package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(1L, 2L, 1);
		assertEquals(created, DAO.create(created));
		assertEquals(created.getQuantity(), DAO.create(created).getQuantity());
		assertEquals(created.getId(), DAO.create(created).getId());
		assertEquals(created.getQuantity(), DAO.create(created).getQuantity());
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 2L));
//		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		Order readLatest = new Order(1L, 2L, 1);
		DAO.create(readLatest);
//		assertEquals(readLatest.getItem_id(), DAO.readLatest().getItem_id());
	}

	@Test
	public void testRead() {
		final long id = 1000L;
		Order read = new Order(id, 300L, 6);
		DAO.create(read);
//		assertEquals(read.getOrder_id(), DAO.read(id).getOrder_id());
	}

	@Test
	public void testUpdate() {
		Order updated = new Order(2, 3L, 6L);
		assertEquals(null, DAO.update(updated));
//		assertEquals(updated, DAO.read(updated.getId()));
//		assertEquals(updated.getOrder_id(), DAO.update(updated).getOrder_id());
		

	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(0));
	}
}
