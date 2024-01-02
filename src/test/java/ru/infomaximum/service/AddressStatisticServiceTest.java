package ru.infomaximum.service;

import org.junit.jupiter.api.Test;
import ru.infomaximum.entity.Address;
import ru.infomaximum.entity.AddressStatistic;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressStatisticServiceTest {
    @Test
    void shouldReturnUpdatedAddressStatisticWhenAddDuplicateAddress() {
        AddressStatisticService service = new AddressStatisticService();
        Address address1 = new Address("Барнаул", "Дальняя улица", 56, 2);
        Address address2 = new Address("Батайск", "Мостотреста, улица", 133, 4);
        service.addAddress(address1);
        service.addAddress(address2);
        AddressStatistic statistic2 = service.addAddress(address2);
        service.addAddress(address1);
        AddressStatistic statistic1 = service.addAddress(address1);
        assertEquals(statistic1.getRepeatCounter(), 2);
        assertEquals(statistic2.getRepeatCounter(), 1);
    }

    @Test
    void shouldReturnUpdatedAddressStatisticWhenAddressNotDuplicate() {
        AddressStatisticService service = new AddressStatisticService();
        Address address1 = new Address("Барнаул", "Дальняя улица", 56, 2);
        Address address2 = new Address("Батайск", "Мостотреста, улица", 133, 4);
        service.addAddress(address1);
        service.addAddress(address1);
        AddressStatistic statistic2 = service.addAddress(address2);
        assertEquals(statistic2.getRepeatCounter(), 0);
    }
}
