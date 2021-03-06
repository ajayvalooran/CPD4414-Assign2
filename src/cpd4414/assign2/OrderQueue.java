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

import java.util.ArrayDeque;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class OrderQueue {

    Queue<Order> orderQueue = new ArrayDeque<>();
    Queue<Order> processQueue = new ArrayDeque<>();

    public void add(Order order) throws Exception {

        if (order.getCustomerId().isEmpty() && order.getCustomerName().isEmpty()) {
            throw new NoCustomerIDException();
        } else if (order.getListOfPurchases().isEmpty()) {
            throw new NoPurchasesExistsException();
        } else {
            orderQueue.add(order);
            order.setTimeReceived(new Date());
        }
    }
    
    public Order nextOrder() {
        return orderQueue.peek();
    }

    public void orderProcess() throws Exception {
        Order order = orderQueue.remove();
        if (order.getTimeReceived() == null) {
            throw new NoTimeRecievedException();
        } else {
            order.setTimeProcessed(new Date());
            processQueue.add(order);
        }
    }
    
    public void orderFulfill() throws Exception{
        Order order = orderQueue.peek();
        if(order.getTimeReceived() == null){
            throw new NoTimeRecievedException();
        }
    }

    public static class NoCustomerIDException extends Exception {

        public NoCustomerIDException() {
        }
    }

    public static class NoPurchasesExistsException extends Exception {

        public NoPurchasesExistsException() {
        }
    }

    public static class NoTimeRecievedException extends Exception {

        public NoTimeRecievedException() {
        }
    }

}
