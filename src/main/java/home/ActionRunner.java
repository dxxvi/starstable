package home;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionRunner implements Runnable {
  private static final Logger log = LoggerFactory.getLogger(ActionRunner.class);

  private final List<Action> actions;

  public ActionRunner(List<Action> actions) {
    this.actions = actions;
  }

  @Override
  public void run() {
    try {
      var robot = new Robot();
      actions.forEach(action -> doSingleAction(action, robot));

    } catch (Exception e) {
      log.error("Run: {}", e.getMessage());
    } finally {
      StarstableApplication.running.set(false);
    }
  }

  private void doSingleAction(Action action, Robot robot) {
    try {
      if (action.movetoX() != null && action.movetoY() != null) {
        robot.mouseMove(action.movetoX(), action.movetoY());
      }

      if (action.keypress() != null) {
        int keyEvent = toKeyEvent(action.keypress());
        if (keyEvent == -1) {
          log.info("Invalid keypress: {}", action.keypress());
          return;
        }

        robot.keyPress(keyEvent);
        robot.delay(9);
        robot.keyRelease(keyEvent);
      }

      if (action.click() != null && action.click()) {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(9);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
      }

      Thread.sleep(action.msSleep() == null ? 500 : action.msSleep());

      if (action.subactions() != null) {
        int repeatTimes = action.repeattimes() == null ? 1 : action.repeattimes();
        for (int i = 0; i < repeatTimes; i++) {
          action.subactions().forEach(subaction -> doSingleAction(subaction, robot));
        }
      }
    } catch (Exception e) {
      log.error("Do single action: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  // returns -1 if keypress is invalid
  private static int toKeyEvent(String keypress) {
    return switch (keypress.charAt(0)) {
      case 'A', 'a' -> KeyEvent.VK_A;
      case 'B', 'b' -> KeyEvent.VK_B;
      case 'C', 'c' -> KeyEvent.VK_C;
      case 'D', 'd' -> KeyEvent.VK_D;
      case 'E', 'e' -> KeyEvent.VK_E;
      case 'F', 'f' -> KeyEvent.VK_F;
      case 'G', 'g' -> KeyEvent.VK_G;
      case 'H', 'h' -> KeyEvent.VK_H;
      case 'I', 'i' -> KeyEvent.VK_I;
      case 'J', 'j' -> KeyEvent.VK_J;
      case 'K', 'k' -> KeyEvent.VK_K;
      case 'L', 'l' -> KeyEvent.VK_L;
      case 'M', 'm' -> KeyEvent.VK_M;
      case 'N', 'n' -> KeyEvent.VK_N;
      case 'O', 'o' -> KeyEvent.VK_O;
      case 'P', 'p' -> KeyEvent.VK_P;
      case 'Q', 'q' -> KeyEvent.VK_Q;
      case 'R', 'r' -> KeyEvent.VK_R;
      case 'S', 's' -> KeyEvent.VK_S;
      case 'T', 't' -> KeyEvent.VK_T;
      case 'U', 'u' -> KeyEvent.VK_U;
      case 'V', 'v' -> KeyEvent.VK_V;
      case 'W', 'w' -> KeyEvent.VK_W;
      case 'X', 'x' -> KeyEvent.VK_X;
      case 'Y', 'y' -> KeyEvent.VK_Y;
      case 'Z', 'z' -> KeyEvent.VK_Z;
      case '0' -> KeyEvent.VK_0;
      case '1' -> KeyEvent.VK_1;
      case '2' -> KeyEvent.VK_2;
      case '3' -> KeyEvent.VK_3;
      case '4' -> KeyEvent.VK_4;
      case '5' -> KeyEvent.VK_5;
      case '6' -> KeyEvent.VK_6;
      case '7' -> KeyEvent.VK_7;
      case '8' -> KeyEvent.VK_8;
      case '9' -> KeyEvent.VK_9;
      default -> -1;
    };
  }
}
