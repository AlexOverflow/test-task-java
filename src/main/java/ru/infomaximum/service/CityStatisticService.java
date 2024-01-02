package ru.infomaximum.service;

import ru.infomaximum.entity.Address;
import ru.infomaximum.entity.CityStatistic;

import java.util.HashMap;
import java.util.Map;

public class CityStatisticService {

    private final Map<String, CityStatistic> cityStatisticMap;

    private final int CITY_STATISTICS_MAP_INITIAL_CAPACITY = 64;

    public CityStatisticService() {
        cityStatisticMap = new HashMap<>(CITY_STATISTICS_MAP_INITIAL_CAPACITY);
    }

    public CityStatistic addAddress(Address address) {
        if (address != null) {
            String cityName = address.getCity();
            CityStatistic cityStatistic = cityStatisticMap.get(cityName);
            if (cityStatistic != null) {
                updateHouseCounter(cityStatistic, address.getFloor());

            } else {
                cityStatistic = new CityStatistic();
                updateHouseCounter(cityStatistic, address.getFloor());
                cityStatisticMap.put(cityName, cityStatistic);
            }
            return cityStatistic;
        }
        return null;
    }

    private void updateHouseCounter(CityStatistic statistic, int floor) {
        switch (floor) {
            case 1: statistic.setOneStoryHouseCounter(statistic.getOneStoryHouseCounter() + 1);
                break;
            case 2: statistic.setTwoStoryHouseCounter(statistic.getTwoStoryHouseCounter() + 1);
                break;
            case 3: statistic.setThreeStoryHouseCounter(statistic.getThreeStoryHouseCounter() + 1);
                break;
            case 4: statistic.setFourStoryHouseCounter(statistic.getFourStoryHouseCounter() + 1);
                break;
            case 5: statistic.setFiveStoryHouseCounter(statistic.getFiveStoryHouseCounter() + 1);
        }
    }

    public Map<String, CityStatistic> getCityStatisticMap() {
        return cityStatisticMap;
    }
}
