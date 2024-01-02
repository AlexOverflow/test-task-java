package ru.infomaximum.service;

import org.junit.jupiter.api.Test;
import ru.infomaximum.entity.Address;
import ru.infomaximum.entity.CityStatistic;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityStatisticServiceTest {

    @Test
    void shouldReturnUpdatedCityStatisticWhenAddAddress () {
        CityStatisticService service = new CityStatisticService();
        Address address1 = new Address("Барнаул", "Дальняя улица", 56, 2);
        Address address2 = new Address("Батайск", "Мостотреста, улица", 133, 4);
        Address address3 = new Address("Батайск", "Мостотреста, улица", 131, 4);
        CityStatistic statistic1 = service.addAddress(address1);
        service.addAddress(address2);
        CityStatistic statistic3 = service.addAddress(address3);
        assertEquals(statistic3.getFourStoryHouseCounter(), 2);
        assertEquals(statistic1.getTwoStoryHouseCounter(), 1);
        assertEquals(service.getCityStatisticMap().entrySet().size(), 2);
    }
}
