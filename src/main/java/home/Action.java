package home;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.Map;

public record Action(
    Map<String, Action> declaredActions,
    Integer movetoX,
    Integer movetoY,
    String keypress,
    Boolean click,
    Long msSleep,
    List<JsonNode> subactions, // an element in this list can be an action name or a concrete action
    Integer repeattimes /* repeat the subactions above this many times */) {}
