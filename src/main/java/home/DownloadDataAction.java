package home;

public record DownloadDataAction(
    IntervalDropdown intervalDropdown, ScrollScreen scrollScreen, ExportDropdown exportDropdown) {
  public record Location(int x, int y) {}

  public record IntervalDropdown(
      Location loc,
      Location loc1m,
      Location loc2m,
      Location loc3m,
      Location loc5m,
      Location loc10m,
      Location loc15m,
      Location loc30m,
      Location loc45m,
      Location loc1h,
      Location loc2h,
      Location loc3h,
      Location loc4h,
      Location loc12h,
      Location loc1d,
      Location loc4d) {}

  public record ScrollScreen(Location clickAt, Location scrollTo) {}

  public record ExportDropdown(Location clickAt, Location exportChartData, Location exportButton) {}
}
