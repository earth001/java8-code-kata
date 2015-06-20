package stream.api.code.kata;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import stream.api.code.kata.utils.ClassicOnlineStore;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Exercise4Test extends ClassicOnlineStore {

    @Test
    public void firstRegistrant() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Find the first customer who registered this online store, using {@link Stream#findFirst}
         * The customerList are ascending ordered by registered timing.
         */
        Optional<Customer> firstCustomer = customerList.stream().findFirst();

        assertThat(firstCustomer.get(), is(customerList.get(0)));
    }

    @Test
    public void isThereAnyoneOlderThan40() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Check whether any customer older than 40 exists or not, using {@link Stream#anyMatch}
         */
        boolean olderThan40Exists = customerList.stream().anyMatch(o -> o.getAge() > 40);

        assertThat(olderThan40Exists, is(false));
    }

    @Test
    public void isEverybodyOlderThan20() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Check whether all customer are older than 20 or not, using {@link Stream#allMatch}
         */
        boolean allOlderThan20 = customerList.stream().allMatch(o -> o.getAge() > 20);

        assertThat(allOlderThan20, is(true));
    }

    @Test
    public void everyoneWantsSomething() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Confirm that none of the customer has empty list for their {@link Customer.wantToBuy}
         * using {@link Stream#noneMatch}
         */
        boolean everyoneWantsSomething = customerList.stream().noneMatch(o -> o.getWantToBuy().isEmpty());

        assertThat(everyoneWantsSomething, is(true));
    }
}