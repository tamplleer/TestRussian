package com.tamplleer.testrussian.activities.database.components;

import java.util.UUID;

public interface ICallBackFragment {
    void execute(String message, String word, String type, UUID id);
}
