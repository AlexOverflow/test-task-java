package ru.infomaximum.service;

import ru.infomaximum.entity.Address;
import ru.infomaximum.entity.AddressStatistic;

import java.util.HashMap;
import java.util.Map;

public class AddressStatisticService {
    private final Map<Address, AddressStatistic> addressStatistic;

    private final int ADDRESS_STATISTICS_MAP_INITIAL_CAPACITY = 1024;

    public AddressStatisticService() {
        addressStatistic = new HashMap<>(ADDRESS_STATISTICS_MAP_INITIAL_CAPACITY);
    }

    public AddressStatistic addAddress(Address address) {
        AddressStatistic addressStatistic = this.addressStatistic.get(address);
        if (addressStatistic != null) {
            addressStatistic.setRepeatCounter(addressStatistic.getRepeatCounter() + 1);
        } else {
            addressStatistic = new AddressStatistic();
            this.addressStatistic.put(address, new AddressStatistic());
        };
        return addressStatistic;
    }

    public Map<Address, AddressStatistic> getAddressStatistic() {
        return addressStatistic;
    }
}
