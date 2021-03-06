package stream.api;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import common.test.tool.annotation.Easy;
import common.test.tool.dataset.ClassicOnlineStore;
import common.test.tool.entity.Customer;

public class Exercise3Test extends ClassicOnlineStore {

    @Easy
    @Test
    public void howManyItemsWanted() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Count how many items there are in {@link Customer.wantToBuy} using {@link Stream#count}
         */
        long sum = customerList.stream()
            .flatMap(c -> c.getWantToBuy()
                .stream())
            .count();
        // .mapToInt(c -> c.getWantToBuy().size()).sum();
        // .mapToLong(c -> c.getWantToBuy()
        // .stream()
        // .count())
        // .sum();
        // .map(Customer::getWantToBuy).map(List::stream).mapToLong(Stream::count).sum();
        // .map(c -> c.getWantToBuy()
        // .stream())
        // .mapToLong(Stream::count)
        // .sum();

        assertThat(sum, is(32L));
    }

    @Easy
    @Test
    public void richestCustomer() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Find the richest customer's budget by using {@link Stream#max} and {@link Comparator#naturalOrder}
         * Don't use {@link Stream#sorted}
         */
        Comparator<Integer> comparator = Comparator.naturalOrder();
        Optional<Integer> richestCustomer = customerList.stream()
            .map(Customer::getBudget)
            .max(comparator);

        assertThat(comparator.getClass()
            .getSimpleName(), is("NaturalOrderComparator"));
        assertThat(richestCustomer.get(), is(12000));
    }

    @Easy
    @Test
    public void youngestCustomer() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Find the youngest customer by using {@link Stream#min}
         * Don't use {@link Stream#sorted}
         */
        Comparator<Customer> comparator = (c1, c2) -> c1.getAge() - c2.getAge();
        Optional<Customer> youngestCustomer = customerList.stream()
            .min(comparator);

        assertThat(youngestCustomer.get(), is(customerList.get(8)));
    }
}
