package ru.infomaximum.service;

import ru.infomaximum.entity.Address;
import ru.infomaximum.entity.AddressStatistic;
import ru.infomaximum.entity.CityHouseStatistic;
import ru.infomaximum.entity.CityStatistic;
import ru.infomaximum.enums.FileExtension;
import ru.infomaximum.reader.AddressCsvFileReader;
import ru.infomaximum.reader.AddressReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CityStatisticService {
    private Map<Address, AddressStatistic> addressMap;
    private Map<String, CityHouseStatistic> cityHouseStatisticMap;

    public CityStatisticService () {
        /* стартовый размер коллекций поставил побольше так как предпологается работа с большими файлами*/
        addressMap = new HashMap<>(1024);
        cityHouseStatisticMap = new HashMap<>(128);
    }

    /* Добавил ExecutorService на один поток для того что бы во время чтения с диска выполнялась обработка */
    public CityStatistic processFile (File file) {
        FileExtension[] supportedExtension = { FileExtension.CSV };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try (AddressReader addressReader = new AddressCsvFileReader(file)) {
            Address address = null;
            while ((address = addressReader.readNextAddress()) != null) {
                Address finalAddress = address;
                Runnable task = () -> {
                    updateStats(finalAddress);
                };
                executorService.execute(task);
            }
            executorService.shutdown();
            addressReader.close();
            return buildCityStatistic();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (Exception e) {
            System.out.println("ошибка чтения файла");
        }
        return null;
    }

    private void updateStats(Address address) {
        boolean isAddressDuplicate = false;
        String cityName = address.getCity();
        AddressStatistic addressStatistic = addressMap.get(address);

        if (addressStatistic != null) {
            addressStatistic.increaseCounter();
            isAddressDuplicate = true;
        } else {
            addressMap.put(address, new AddressStatistic());
        };
        CityHouseStatistic cityHouseStatistic = cityHouseStatisticMap.get(cityName);
        if (cityHouseStatistic != null && !isAddressDuplicate) {
            cityHouseStatistic.updateHouseCounter(address.getFloor());
        } else {
            CityHouseStatistic newCityHouseStatistic = new CityHouseStatistic();
            newCityHouseStatistic.updateHouseCounter(address.getFloor());
            cityHouseStatisticMap.put(cityName, newCityHouseStatistic);
        }
    }

    private CityStatistic buildCityStatistic() {
        Map<Address, AddressStatistic> duplicateAddress;
        duplicateAddress = addressMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getRepeatCounter() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new CityStatistic(duplicateAddress, cityHouseStatisticMap);
    }
}
