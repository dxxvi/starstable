package home;

import static java.nio.file.StandardOpenOption.*;
import static java.util.Map.entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ActionRunnerTest {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Test
  void testRun() throws AWTException {
    JsonNode jsonNode =
        OBJECT_MAPPER.valueToTree(new Action(null, 5, 6, "C", null, null, null, null));

    var robot =
        new Robot() {
          @Override
          public synchronized void mouseMove(int x, int y) {
            System.out.printf("Move mouse to %d, %d%n", x, y);
          }

          @Override
          public synchronized void keyPress(int keyEvent) {
            System.out.printf("Press key %s%n", fromKeyEvent(keyEvent));
          }

          @Override
          public synchronized void keyRelease(int keyEvent) {
            System.out.printf("Release key %s%n", fromKeyEvent(keyEvent));
          }

          @Override
          public void delay(int ms) {
            System.out.printf("Delay %d ms%n", ms);
          }

          @Override
          public synchronized void mousePress(int buttons) {
            if (buttons == InputEvent.BUTTON1_DOWN_MASK) {
              System.out.println("Press button1 down mask");
            } else {
              System.out.printf("Press button %d%n", buttons);
            }
          }

          @Override
          public synchronized void mouseRelease(int buttons) {
            if (buttons == InputEvent.BUTTON1_DOWN_MASK) {
              System.out.println("Release button1 down mask");
            } else {
              System.out.printf("Release button %d%n", buttons);
            }
          }

          private static String fromKeyEvent(int keyEvent) {
            return switch (keyEvent) {
              case KeyEvent.VK_A -> "A";
              case KeyEvent.VK_B -> "B";
              case KeyEvent.VK_C -> "C";
              case KeyEvent.VK_D -> "D";
              case KeyEvent.VK_E -> "E";
              case KeyEvent.VK_F -> "F";
              case KeyEvent.VK_G -> "G";
              case KeyEvent.VK_H -> "H";
              case KeyEvent.VK_I -> "I";
              case KeyEvent.VK_J -> "J";
              case KeyEvent.VK_K -> "K";
              case KeyEvent.VK_L -> "L";
              case KeyEvent.VK_M -> "M";
              case KeyEvent.VK_N -> "N";
              case KeyEvent.VK_O -> "O";
              case KeyEvent.VK_P -> "P";
              case KeyEvent.VK_Q -> "Q";
              case KeyEvent.VK_R -> "R";
              case KeyEvent.VK_S -> "S";
              case KeyEvent.VK_T -> "T";
              case KeyEvent.VK_U -> "U";
              case KeyEvent.VK_V -> "V";
              case KeyEvent.VK_W -> "W";
              case KeyEvent.VK_X -> "X";
              case KeyEvent.VK_Y -> "Y";
              case KeyEvent.VK_Z -> "Z";
              case KeyEvent.VK_0 -> "0";
              case KeyEvent.VK_1 -> "1";
              case KeyEvent.VK_2 -> "2";
              case KeyEvent.VK_3 -> "3";
              case KeyEvent.VK_4 -> "4";
              case KeyEvent.VK_5 -> "5";
              case KeyEvent.VK_6 -> "6";
              case KeyEvent.VK_7 -> "7";
              case KeyEvent.VK_8 -> "8";
              case KeyEvent.VK_9 -> "9";
              default -> "Unknown keyEvent " + keyEvent;
            };
          }
        };
    new ActionRunner(
            List.of(
                new Action(
                    Map.ofEntries(
                        entry("action1", new Action(null, 3, 4, "B", false, 51L, null, null))),
                    1,
                    2,
                    "A",
                    true,
                    50L,
                    List.of(new TextNode("action1"), jsonNode),
                    2)),
            robot)
        .run();
  }

//  @Test
  void test() throws Throwable {
    long fileSize = -1;

    while (true) {
      var file = new File("/dev/shm/h1.png");

      if (!file.exists()) {
        Thread.sleep(1_500);
        continue;
      }

      if (file.length() == fileSize) {
        Thread.sleep(1_500);
        continue;
      }

      fileSize = file.length();
      var processBuilder = new ProcessBuilder("tesseract", "h1.png", "h1", "-l", "eng");
      processBuilder.directory(new File("/dev/shm"));
      int exitCode = processBuilder.start().waitFor();
      if (exitCode != 0) {
        System.out.println("\n\n\nTesseract failed\n\n\n");
        Thread.sleep(1_100);
        continue;
      }

      file = new File("/dev/shm/h1.txt");
      if (file.exists()) {
        String everything = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        everything = everything
            .replaceAll("\\r\\n", "\n")
            .replaceAll("\\n+", "\n")
            .replaceAll("\\n. ", "\n- ");
        Files.write(file.toPath(), everything.getBytes(StandardCharsets.UTF_8), TRUNCATE_EXISTING);
        System.out.println("Got a new image, processed it successfully");
      } else {
        System.out.println("\n\n\nTesseract failed to create h1.txt. Why???\n\n\n");
        Thread.sleep(1_200);
        continue;
      }
      Thread.sleep(1000);
    }
  }
}
