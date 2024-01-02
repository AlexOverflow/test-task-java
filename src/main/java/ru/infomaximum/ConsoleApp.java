package ru.infomaximum;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.TA_GridThemes;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import ru.infomaximum.entity.AddressBookStatistic;
import ru.infomaximum.exception.AddressReaderException;
import ru.infomaximum.reader.LineReader;
import ru.infomaximum.service.AddressBookStatisticService;

import java.io.File;

public class ConsoleApp {
    public static void run() {
        System.out.println("Введите путь к файлу или q + ENTER для выходы из программы");
        LineReader lineReader = new LineReader(System.in, 'q');
        String line;
        while ((line = lineReader.readLine()) != null ) {
            try {
                File file = new File(line);
                AddressBookStatisticService service = new AddressBookStatisticService();
                AddressBookStatistic statistics = service.getAddressBookStatistics(file);
                showStatistics(statistics);
            } catch (AddressReaderException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(" \n Введите путь к файлу или q + ENTER для выходы из программы");
            }
        }
    }

    public static void showStatistics(AddressBookStatistic addressBookStatistic) {
        AsciiTable duplicateAddressTable = new AsciiTable();
        duplicateAddressTable.addRow(null, "Дублирующиеся записи");
        duplicateAddressTable.addRule();
        duplicateAddressTable.addRow("Адрес", "Кол-во повторений");
        duplicateAddressTable.addRule();
        addressBookStatistic.getDuplicateAddressMap().forEach(
                (key, value) -> duplicateAddressTable.addRow(key, value.getRepeatCounter())
        );
        duplicateAddressTable.setTextAlignment(TextAlignment.CENTER);
        duplicateAddressTable.addRule();
        duplicateAddressTable.getContext().setGridTheme(TA_GridThemes.FULL);
        duplicateAddressTable.getContext().setWidth(100);


        AsciiTable cityHouseStatisticTable = new AsciiTable();
        cityHouseStatisticTable.addRow(null, null, null, null, null, "Кол-во многоэтажных зданий по городам");
        cityHouseStatisticTable.addRule();
        cityHouseStatisticTable.addRow("Город", "1-этажных", "2-этажных", "3-этажных", "4-этажных", "5-этажных");
        cityHouseStatisticTable.addRule();
        addressBookStatistic.getCityHouseStatistic().forEach((cityName, statistic) -> cityHouseStatisticTable.addRow(
                cityName,
                statistic.getOneStoryHouseCounter(),
                statistic.getTwoStoryHouseCounter(),
                statistic.getThreeStoryHouseCounter(),
                statistic.getFourStoryHouseCounter(),
                statistic.getFiveStoryHouseCounter()
        ));
        cityHouseStatisticTable.setTextAlignment(TextAlignment.CENTER);
        cityHouseStatisticTable.addRule();
        cityHouseStatisticTable.getContext().setGridTheme(TA_GridThemes.FULL);
        cityHouseStatisticTable.getContext().setWidth(100);
        System.out.println(duplicateAddressTable.render());
        System.out.println("\n");
        System.out.println(cityHouseStatisticTable.render());
    }
}
