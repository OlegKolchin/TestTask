package org.kolchin.TestTask.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private boolean success;
    private String errors;

    public ResponseMessage(boolean success) {
        this.success = success;
    }
}
