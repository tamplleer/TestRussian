package com.tamplleer.testrussian.command;

import java.util.HashMap;
import java.util.Map;

public class CommandMap {
    Map<String, ICommand> commandMap = new HashMap<>();

    CommandMap() {
        commandMap.put("next", new Next());
        commandMap.put("random", new RundomWord());
        commandMap.put("allword", new AllWord());
    }

    void useCommand(String s) {
        commandMap.get(s);
    }


}
