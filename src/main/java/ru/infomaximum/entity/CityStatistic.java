package ru.infomaximum.entity;

import java.util.Map;

public class CityStatistic {
    private Map<Address, AddressStatistic> duplicateAddressMap;
    private Map<String, CityHouseStatistic> cityHouseStatistic;

    public CityStatistic(Map<Address, AddressStatistic> addressMap, Map<String, CityHouseStatistic> cityHouseStatistic) {
        this.duplicateAddressMap = addressMap;
        this.cityHouseStatistic = cityHouseStatistic;
    }

    public Map<Address, AddressStatistic> getDuplicateAddressMap() {
        return duplicateAddressMap;
    }

    public void setDuplicateAddressMap(Map<Address, AddressStatistic> duplicateAddressMap) {
        this.duplicateAddressMap = duplicateAddressMap;
    }

    public Map<String, CityHouseStatistic> getCityHouseStatistic() {
        return cityHouseStatistic;
    }

    public void setCityHouseStatistic(Map<String, CityHouseStatistic> cityStatistic) {
        this.cityHouseStatistic = cityStatistic;
    }
}
