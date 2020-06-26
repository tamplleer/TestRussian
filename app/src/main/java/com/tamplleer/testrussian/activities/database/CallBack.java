package com.tamplleer.testrussian.activities.database;

import java.util.UUID;

public interface CallBack {
    void execute(String message, String word, String type, UUID id);
}
