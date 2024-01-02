package ru.infomaximum.entity;

import java.util.Map;

public class AddressBookStatistic {
    private Map<Address, AddressStatistic> duplicateAddressMap;
    private Map<String, CityStatistic> cityHouseStatistic;

    public AddressBookStatistic(Map<Address, AddressStatistic> addressMap, Map<String, CityStatistic> cityHouseStatistic) {
        this.duplicateAddressMap = addressMap;
        this.cityHouseStatistic = cityHouseStatistic;
    }

    public Map<Address, AddressStatistic> getDuplicateAddressMap() {
        return duplicateAddressMap;
    }

    public void setDuplicateAddressMap(Map<Address, AddressStatistic> duplicateAddressMap) {
        this.duplicateAddressMap = duplicateAddressMap;
    }

    public Map<String, CityStatistic> getCityHouseStatistic() {
        return cityHouseStatistic;
    }

    public void setCityHouseStatistic(Map<String, CityStatistic> cityStatistic) {
        this.cityHouseStatistic = cityStatistic;
    }
}
