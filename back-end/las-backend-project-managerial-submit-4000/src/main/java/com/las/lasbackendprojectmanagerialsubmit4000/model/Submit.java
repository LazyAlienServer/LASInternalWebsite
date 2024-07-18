package com.las.lasbackendprojectmanagerialsubmit4000.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author sunyinuo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submit  {
    private String name;
    private int progress;
    private List<String> authors;
    private String start_time;
    private Place place;
    private boolean innovation;

    private Boolean pass;
    private String auditor;

    private String note;
    private List<String> picture;
    private List<String> type;
    private List<String> children;
}
