package home;

import home.DownloadDataAction.Location;
import java.awt.Robot;
import java.awt.event.InputEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadDataActionRunner implements Runnable {
  private static final Logger log = LoggerFactory.getLogger(DownloadDataActionRunner.class);

  private final DownloadDataAction action;

  public DownloadDataActionRunner(DownloadDataAction action) {
    this.action = action;
  }

  @Override
  public void run() {
    try {
      var robot = new Robot();
      gotoThenClick(action.activatePoint(), robot, 1000);

      for (var intervalDropDown : action.intervalDropdowns()) {
        gotoThenClick(intervalDropDown.downArrow(), robot, 1000);
        gotoThenClick(intervalDropDown.menuItem(), robot, 2000);
        for (int i = 0; i < 15; i++) {
          gotoThenMousePressThenMoveThenRelease(
              action.scrollScreen().clickAt(), action.scrollScreen().scrollTo(), robot);
        }
        gotoThenClick(action.exportDropdown().clickAt(), robot, 1500);
        gotoThenClick(action.exportDropdown().exportChartData(), robot, 1500);
        gotoThenClick(action.exportDropdown().exportButton(), robot, 2000);

        robot.delay(1000);
      }

      log.info("Downloading completed.");
    } catch (Exception e) {
      log.error("Run: {}", e.getMessage());
    } finally {
      TradingViewApplication.running.set(false);
    }
  }

  private void gotoThenMousePressThenMoveThenRelease(Location from, Location to, Robot robot)
      throws Exception {
    robot.mouseMove(from.x(), from.y());
    robot.delay(1000);
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    robot.delay(100);
    int n = 40;
    for (int i = 1; i <= n; i++) {
      robot.mouseMove(
          from.x() + i * (to.x() - from.x()) / n, from.y() + i * (to.y() - from.y()) / n);
      robot.delay(100);
    }

    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    robot.delay(1000);
  }

  private void gotoThenClick(Location loc, Robot robot, long msSleep) throws Exception {
    robot.mouseMove(loc.x(), loc.y());
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    robot.delay(100);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    Thread.sleep(msSleep);
  }
}
