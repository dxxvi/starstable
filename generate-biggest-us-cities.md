```java
class TopBiggestUSCitiesTest {
  @Test
  void test() throws Exception {
    List<City> cities = new ArrayList<>();
    // use https://www.biggestuscities.com/2022 for the previous year
    final String baseUrl = "https://www.biggestuscities.com";

    String path = "/";

    while (path != null && cities.size() < 10001) {
      var document = Jsoup.connect(baseUrl + path).get();
      for (Element a :
          document.select(
              "table.table-striped td > a:first-child, table.table-striped td > strong > a:first-child")) {
        Element stateElement = a.nextElementSibling();
        if (stateElement == null || !stateElement.tagName().equalsIgnoreCase("a")) {
          continue;
        }
        Element td = specificAncestor(a, "td");
        if (td == null) {
          continue;
        }
        Element nextTd = td.nextElementSibling();
        if (nextTd == null || !nextTd.tagName().equalsIgnoreCase("td")) {
          continue;
        }
        try {
          cities.add(
              new City(
                  a.text(), stateElement.text(), Integer.parseInt(nextTd.text().replace(",", ""))));
        } catch (NumberFormatException _) { /* who cares */ }
      }

      path = null;
      for (Element a : document.select("a[href^='/'][rel='next']")) {
        try {
          int next = Integer.parseInt(a.attr("href").substring(1));
          path = "/" + next;
          break;
        } catch (NumberFormatException _) { /* who cares */ }
      }
    }

    cities.forEach(System.out::println);
  }

  private Element specificAncestor(Element el, String tagName) { // might return null
    Element parent = el.parent();
    if (parent == null) {
      return null;
    }
    if (parent.tagName().equalsIgnoreCase(tagName)) {
      return parent;
    }
    return specificAncestor(parent, tagName);
  }
}

record City(String city, String state, int population) {}
```
