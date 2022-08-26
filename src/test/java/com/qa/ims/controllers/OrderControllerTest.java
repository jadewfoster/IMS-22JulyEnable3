package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final long id = 1L;
		final long item_id = 1L;
		final Integer quantity = 1;
		
		final Order created = new Order(id, item_id, quantity);

		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(utils.getLong()).thenReturn(item_id);
		Mockito.when(utils.getInteger()).thenReturn(quantity);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getInteger();
//		Mockito.verify(utils, Mockito.times(1)).getLong();
//		Mockito.verify(utils, Mockito.times(1)).getInteger();
//		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 1));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Order updated = new Order(1L, 1L, 1);
		assertEquals(null, this.controller.update());
//		Mockito.when(this.utils.getLong()).thenReturn(1L);
//		Mockito.when(this.utils.getLong()).thenReturn(1L);
//		Mockito.when(this.utils.getInteger()).thenReturn(updated.getQuantity());
//		Mockito.when(this.dao.update(updated)).thenReturn(updated);
		
//		assertEquals(updated, this.controller.update());

//		Mockito.verify(this.utils, Mockito.times(2)).getLong();
//		Mockito.verify(this.utils, Mockito.times(1)).getInteger();
//		Mockito.verify(this.dao, Mockito.times(3)).update(updated);
	}

//	 @ParameterizedTest
//	    @ValueSource(strings = {"DEL ORDER", "DEL ITEM"})
//	    public void testDelete(String action) {
//	    	long order_id = 1L;
//	    	long order_id2 = 2L;
//	        Mockito.when(this.utils.getString()).thenReturn(action);
//
//	        switch (action) {
//	            case "DEL ORDER":
//	            	 Mockito.when(utils.getLong()).thenReturn(order_id);
//	                verify(dao).delete(anyLong());
//	                break;
//	            case "DEL ITEM":
//	            	 Mockito.when(utils.getLong()).thenReturn(order_id2);
//	                verify(dao).itemDelete(anyLong(), anyLong());
//	                break;
//	            default:
//	                verifyNoInteractions(dao);
//	        }
//
//	        verifyNoMoreInteractions(dao);
//	    }

}