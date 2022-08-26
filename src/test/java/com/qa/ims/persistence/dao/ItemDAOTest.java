package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {

	private final ItemDAO DAO = new ItemDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
	    Item created = new Item(1L, "drink", 10.5 , 2);
		assertEquals(created.getItemName(), DAO.create(created).getItemName());
		assertEquals(created.getStock(), DAO.create(created).getStock());
	}

	@Test
	public void testReadAll(){
	            List<Item> expected = new ArrayList<>();		
		expected.add(new Item(1L, "Espresso Martini", 7.8, 100));
//		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		Item readLatest = new Item(100L, "drink2", 10.5, 2);
		DAO.create(readLatest);
		assertEquals(readLatest.getItemName(), DAO.readLatest().getItemName());
		assertEquals(readLatest.getStock(), DAO.readLatest().getStock());
	}

	@Test
	public void testRead() {
		final long ID = 1500L;
		Item read = new Item(ID, "Negroni", 10.5, 100);
		DAO.create(read);
		assertEquals(read.getItemName(), DAO.read(ID).getItemName());
		assertEquals(read.getStock(), DAO.read(ID).getStock());
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(2L, "drink", 10.5, 1);
		assertEquals(updated, DAO.update(updated));
//
	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(0));
	}
}