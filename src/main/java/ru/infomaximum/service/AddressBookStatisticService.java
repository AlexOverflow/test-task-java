package ru.infomaximum.service;

import ru.infomaximum.entity.Address;
import ru.infomaximum.entity.AddressStatistic;
import ru.infomaximum.entity.AddressBookStatistic;
import ru.infomaximum.enums.FileExtension;
import ru.infomaximum.exception.AddressReaderException;
import ru.infomaximum.reader.AddressReader;
import ru.infomaximum.reader.AddressReaderFactory;
import ru.infomaximum.utils.FileValidator;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class AddressBookStatisticService {

    private AddressStatisticService addressStatisticService;
    private CityStatisticService cityStatisticService;

    /* Добавил ExecutorService на один поток для того что бы во время чтения с диска выполнялась обработка */
    public AddressBookStatistic getAddressBookStatistics(File file) throws AddressReaderException {
        addressStatisticService = new AddressStatisticService();
        cityStatisticService = new CityStatisticService();
        FileExtension fileExtension = null;
        try {
            fileExtension = FileValidator.getFileExtension(file);
        } catch (FileNotFoundException e) {
            throw new AddressReaderException("Файл не найден");
        } catch (IllegalArgumentException e) {
            throw new AddressReaderException("Данный формат файла не поддерживается");
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try (
            InputStream inputStream = new FileInputStream(file);
            AddressReader addressReader = new AddressReaderFactory().createAddressReader(fileExtension, inputStream);
        ) {
            Address address = null;
            while ((address = addressReader.readNextAddress()) != null) {
                Address finalAddress = address;
                Runnable task = () -> {
                    updateStats(finalAddress);
                };
                executorService.execute(task);
            }
            executorService.shutdown();
            return buildAddressBookStatistic();
        } catch (FileNotFoundException e) {
            throw new AddressReaderException("Файл не найден!");
        } catch (Exception e) {
            throw new AddressReaderException("Ошибка при чтении файла");
        }
    }

    private void updateStats(Address address) {
        AddressStatistic addressStatistic = addressStatisticService.addAddress(address);
        // Если repeatCounter > 0, то это дупликат записи и не нужно обновлять статистику по городу
        if (addressStatistic.getRepeatCounter() == 0) {
            cityStatisticService.addAddress(address);
        }
    }

    private AddressBookStatistic buildAddressBookStatistic() {
        Map<Address, AddressStatistic> duplicateAddress;
        duplicateAddress = addressStatisticService.getAddressStatistic().entrySet()
                .stream()
                .filter(entry -> entry.getValue().getRepeatCounter() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new AddressBookStatistic(duplicateAddress, cityStatisticService.getCityStatisticMap());
    }
}
