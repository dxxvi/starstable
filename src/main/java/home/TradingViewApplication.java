package home;

import java.awt.MouseInfo;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TradingViewApplication {
  public static AtomicBoolean running = new AtomicBoolean(false);

  private static final Logger log = LoggerFactory.getLogger(TradingViewApplication.class);

  public static void main(String[] args) throws Exception {
    Thread.ofVirtual()
        .start(
            () -> {
              int x = 0;
              int y = 0;
              while (true) {
                try {
                  var point = MouseInfo.getPointerInfo().getLocation();
                  if (point.x != x || point.y != y) {
                    x = point.x;
                    y = point.y;
                    System.out.printf("X: %d, Y: %d%n", x, y);
                  }
                  Thread.sleep(100);
                } catch (Exception e) {
                  log.warn(e.getMessage());
                }
              }
            });

    SpringApplication.run(TradingViewApplication.class, args);
  }

  @PostMapping(path = "/download-data", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> downloadData(@RequestBody DownloadDataAction action) {
    if (!running.compareAndSet(false /*expected*/, true /*new value*/)) {
      return ResponseEntity.badRequest().body("Another downloading is running");
    }

    Thread.ofVirtual().start(new DownloadDataActionRunner(action));
    return ResponseEntity.ok("Downloading is happening.");
  }
}
