package home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Table1Service {
  private static final Logger log = LoggerFactory.getLogger(Table1Service.class);

  private final Table1Repository table1Repository;

  public Table1Service(Table1Repository table1Repository) {
    this.table1Repository = table1Repository;
  }

  public void upsert(Table1 t) {
    table1Repository
        .findById(t.getId())
        .ifPresentOrElse(
            existing -> {
              log.info("Updating existing {}", existing.getId());
              table1Repository.save(
                  existing
                      .setOpen(t.getOpen())
                      .setHigh(t.getHigh())
                      .setLow(t.getLow())
                      .setClose(t.getClose())
                      .setMa1(t.getMa1())
                      .setMa2(t.getMa2())
                      .setMa3(t.getMa3())
                      .setMa4(t.getMa4())
                      .setMa5(t.getMa5())
                      .setVolume(t.getVolume())
                      .setBasis(t.getBasis())
                      .setUpper(t.getUpper())
                      .setLower(t.getLower())
                      .setHistogram(t.getHistogram())
                      .setMacd(t.getMacd())
                      .setSignal(t.getSignal())
                      .setPercentd(t.getPercentd())
                      .setPercentk(t.getPercentk())
                      .setRsi(t.getRsi())
                      .setRsiBasedMa(t.getRsiBasedMa()));
            },
            () -> table1Repository.save(t));
  }
}
