package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTest {

	private final CustomerDAO DAO = new CustomerDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Customer created = new Customer(15L, "chris", "perrins");
		assertEquals(created.getFirstName(), DAO.create(created).getFirstName());
		assertEquals(created.getSurname(), DAO.create(created).getSurname());
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();		
		expected.add(new Customer(1L, "jordan", "harrison"));
//		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		Customer readLatest = new Customer(1L, "jordan", "harrison");
		DAO.create(readLatest);
		assertEquals(readLatest.getFirstName(), DAO.readLatest().getFirstName());
		assertEquals(readLatest.getSurname(), DAO.readLatest().getSurname());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		Customer read = new Customer(ID, "blah", "blah");
		DAO.create(read);
//		assertEquals(read.getFirstName(), DAO.read(ID).getFirstName());
//		assertEquals(read.getSurname(), DAO.read(ID).getSurname());
	}

	@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins");
//		assertEquals(updated, DAO.update(updated));
//		assertEquals(updated.getFirstName(), DAO.update(updated).getFirstName());
//		assertEquals(updated.getSurname(), DAO.update(updated).getSurname());

	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(0));
	}
}
