package ru.infomaximum;
import ru.infomaximum.entity.CityStatistic;
import ru.infomaximum.service.CityStatisticService;
import ru.infomaximum.util.LineReader;
import java.io.File;


public class Main {
    public static void main(String[] args) {
        System.out.println("Введите путь к файлу или q + ENTER для выходы из программы");
        LineReader lineReader = new LineReader(System.in, 'q');
        String line;
        while ((line = lineReader.readLine()) != null ) {
            File file = new File(line);
            CityStatisticService service = new CityStatisticService();
            CityStatistic statistics = service.processFile(file);
            showStatistics(statistics);
        }
    }

    public static void showStatistics(CityStatistic cityStatistic) {
        cityStatistic.getCityHouseStatistic().forEach(
                (key, value) -> System.out.println( "В городе " + key + " " + value)
        );
        System.out.println("Дублирующиеся задреса: ");
        cityStatistic.getDuplicateAddressMap().forEach(
                (key, value) -> System.out.println(key + " Кол-во повторений: " + value.getRepeatCounter())
        );
    }
}
