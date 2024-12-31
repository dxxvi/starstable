package home;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataLoadingTest {
  private static final Logger log = LoggerFactory.getLogger(DataLoadingTest.class);

  @Autowired private Table1Service service;

  @DisplayName("read src/test/resources/data/**/*.csv and store in h2")
  @Test
  void loadData() throws Exception {
    try (Stream<Path> stream = Files.walk(Path.of("src/test/resources/data"))) {
      for (var path : stream.filter(Files::isRegularFile).toArray(Path[]::new)) {
        if (!path.toFile().getName().endsWith(".csv")) {
          break;
        }

        Tuple2<String, Integer> tuple2 = parseCsvFileName(path.toFile().getName());
        String symbol = tuple2._1();
        int intv = tuple2._2();

        Files.lines(path).skip(1).forEach(line -> {
          String[] array = line.split(",");

          Table1Id id = new Table1Id(symbol, intv, Long.parseLong(array[0]));
          Table1 t = new Table1()
              .setId(id)
              .setOpen(parseFloat(array[1]))
              .setHigh(parseFloat(array[2]))
              .setLow(parseFloat(array[3]))
              .setClose(parseFloat(array[4]))
              .setMa1(parseFloat(array[5]))
              .setMa2(parseFloat(array[6]))
              .setMa3(parseFloat(array[7]))
              .setMa4(parseFloat(array[8]))
              .setMa5(parseFloat(array[9]))
              .setVolume(() -> {
                Float fl = parseFloat(array[10]);
                if (fl == null) {
                  return null;
                }
                return fl.intValue();
              })
              .setBasis(parseFloat(array[11]))
              .setUpper(parseFloat(array[12]))
              .setLower(parseFloat(array[13]))
              .setHistogram(parseFloat(array[14]))
              .setMacd(parseFloat(array[15]))
              .setSignal(parseFloat(array[16]))
              .setPercentk(parseFloat(array[17]))
              .setPercentd(parseFloat(array[18]))
              .setRsi(parseFloat(array[19]))
              .setRsiBasedMa(parseFloat(array[20]));
          service.upsert(t);
        });
      }
    }
  }

  /**
   * @param fileName like `AMEX_SOXL, 1.csv` or `AMEX_SOXL, 60.csv` or `AMEX_SOXL, 1D.csv`
   * @return a tuple of symbol (SOXL) and interval or throw runtime exceptions
   */
  private static Tuple2<String, Integer> parseCsvFileName(String fileName) {
    String[] array = fileName.replace(".csv", "").split(", *");

    if (array.length != 2) {
      throw new IllegalArgumentException("Invalid file name: " + fileName + " no , in file name");
    }

    int intv;
    try {
      intv = Integer.parseInt(array[1].trim());
    } catch (NumberFormatException _) {
      if (array[1].trim().equals("1D")) {
        intv = 24 * 60;
      } else {
        throw new IllegalArgumentException("Invalid interval in file name: " + fileName);
      }
    }

    array = array[0].split("_");
    String symbol = array.length > 1 ? array[1] : array[0];
    return new Tuple2<>(symbol, intv);
  }

  private static Integer parseInt(String s) {
    if (s.equals("NaN")) {
      return null;
    }
    return Integer.parseInt(s);
  }

  private static Float parseFloat(String s) {
    if (s.equals("NaN")) {
      return null;
    }
    return Float.parseFloat(s);
  }
}
