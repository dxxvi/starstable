package home;

import java.util.List;

public record DownloadDataAction(
    List<IntervalDropdown> intervalDropdowns,
    ScrollScreen scrollScreen,
    ExportDropdown exportDropdown) {
  public record Location(int x, int y) {}

  public record IntervalDropdown(Location downArrow, Location menuItem) {}

  public record ScrollScreen(Location clickAt, Location scrollTo) {}

  public record ExportDropdown(Location clickAt, Location exportChartData, Location exportButton) {}
}
