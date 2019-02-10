package pl.sda.intermediate;

import org.junit.jupiter.api.Test;
import pl.sda.intermediate.customer.CustomerStatistics;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerStatisticsTest {

    @Test
    void shouldPopulateSalaryStatisticsMapProperly() {
        CustomerStatistics customerStatistics = new CustomerStatistics();

        Map<String, Map<BigDecimal, Integer>> statisticsMap = customerStatistics.populateSalaryStatisticsMap();

        assertEquals(Integer.valueOf(2), statisticsMap.get("Adam").get(BigDecimal.valueOf(3333)));
    }
}