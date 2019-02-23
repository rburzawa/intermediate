package pl.sda.intermediate;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonExample {
    @Test
    void serializeToJson() {
        OtherObject otherObject = new OtherObject(1, "defe");
        /*List<OtherObject> otherObjects = new ArrayList<>();
        otherObjects.add(otherObject); klasyczne podejście do tworzenia listy*/
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Adam");
        map.put(4, "Andrzej");
        SomeObject someObject = new SomeObject(
                "Adam",
                30,
                BigDecimal.valueOf(4000),
                Lists.newArrayList(otherObject), //tworzenie listy z wykorzystaniem Guava
                map,
                true);

        Gson gson = new Gson();
        String json = new Gson().toJson(someObject);
        System.out.println(json);

        //uruchamiamy, kopiujemy to co się wyświetliło na konsoli, Shitf+ctrl+alt+insert, wpisujemy json i wklejamy, później ctrl+alt+L

        SomeObject result = gson.fromJson(json, SomeObject.class);
    }

    @Test
    void nbp() {
        try {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A/last?format=json");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);

            String inputLine;
            StringBuilder result = new StringBuilder();

            while ((inputLine = br.readLine()) != null) {
                result.append(inputLine);
            }

            br.close();
            System.out.println(result);

            RatesWrapper[] ratesWrapper = new Gson().fromJson(result.toString(), RatesWrapper[].class);
            System.out.println();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Getter
    @Setter
    private class RatesWrapper {

        private String table;
        private String no;
        private String effectiveDate;
        private List<Rate> rates;
    }

    @Getter
    @Setter
    private class Rate {
        private String currency;
        private String code;
        private Double mid;
    }

    @AllArgsConstructor
    private class SomeObject {
        private String name;
        private Integer age;
        private BigDecimal salary;
        private List<OtherObject> otherObjects;
        private Map<Integer, String> map;
        private boolean isItTrue;
    }

    @AllArgsConstructor
    private class OtherObject {
        private Integer id;
        private String text;
    }
}
