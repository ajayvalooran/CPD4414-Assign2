/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cpd4414.assign2;

import cpd4414.assign2.Order;
import cpd4414.assign2.OrderQueue;
import cpd4414.assign2.OrderQueue.NoCustomerIDException;
import cpd4414.assign2.OrderQueue.NoPurchasesExistsException;
import cpd4414.assign2.OrderQueue.NoTimeRecievedException;
import cpd4414.assign2.Purchase;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class OrderQueueTest {

    public OrderQueueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testWhenCustomerExistsAndPurchasesExistThenTimeReceivedIsNow() {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(4, 450));
        order.addPurchase(new Purchase(6, 250));
        try {
            orderQueue.add(order);
        } catch (Exception e) {
            System.out.println("");
        }

        long expResult = new Date().getTime();
        long result = order.getTimeReceived().getTime();
        assertTrue(Math.abs(result - expResult) < 1000);
    }

    @Test
    public void testWhenOrderArrivesNeitherCustomerExistsThenThrowException() {
        boolean result = false;
        try {
            OrderQueue orderQueue = new OrderQueue();
            Order order = new Order("", "ANC");
            order.addPurchase(new Purchase(4, 150));
            orderQueue.add(order);

        } catch (NoCustomerIDException e) {
            result = true;
        } catch (Exception e) {
            System.out.println("");
        }
        assertTrue(result);

    }

    @Test
    public void testWhenOrderArrivesNoListOfPurchasesThenThrowException() {
        boolean result = false;
        try {
            OrderQueue orderQueue = new OrderQueue();
            Order order = new Order("CUST00001", "ABC Construction");
            orderQueue.add(order);
        } catch (NoPurchasesExistsException e) {
            result = true;
        } catch (Exception e) {
            System.out.println("");
        }
        assertTrue(result);
    }

    @Test
    void testWhenRequestNextOrderThereAreOrdersInSystemThenReturnOrderWithEarliestTimeReceived() {
        OrderQueue orderQueue = new OrderQueue();
        Order order = new Order("CUST00001", "ABC Construction");
        order.addPurchase(new Purchase(2, 450));
        try {
            orderQueue.add(order);
        } catch (Exception e) {
            System.out.println("");
        }
        Order newOrder = orderQueue.nextOrder();
        assertEquals(newOrder, order);

    }
    
    @Test
    void testWhenRequestNextOrderThereAreOrdersInSystemThenReturnOrderThenReturnNull() {
        OrderQueue orderQueue = new OrderQueue();
        Order order = orderQueue.nextOrder();
        assertNull(order);

    }

}
