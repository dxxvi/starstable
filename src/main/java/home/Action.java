package home;

import java.util.List;

public record Action(
    Integer movetoX,
    Integer movetoY,
    String keypress,
    Boolean click,
    Long msSleep,
    List<Action> subactions,
    Integer repeattimes) {}
