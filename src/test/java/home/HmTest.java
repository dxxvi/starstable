package home;

import java.util.Date;
import org.h2.server.web.WebServer;
import org.junit.jupiter.api.Test;

class HmTest {
  @Test
  void test() {
    System.out.println(new Date(1728912600000L)); // 1m
    System.out.println(new Date(1722259800000L)); // 2m
    System.out.println(new Date(1709319000000L)); // 5m

    System.out.println(new Date(1735592340000L));

    System.out.println(WebServer.encodeAdminPassword("Abcdefgh1234"));
  }
}
