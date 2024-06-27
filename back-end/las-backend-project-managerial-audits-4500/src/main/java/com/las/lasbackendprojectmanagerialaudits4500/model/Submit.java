package com.las.lasbackendprojectmanagerialaudits4500.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sunyinuo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submit {

    private String name;
    private int progress;
    private List<String> authors;
    private String start_time;
    private Place place;
    private boolean innovation;
    private boolean pass;
    private String auditor;
    private String note;
    private String picture;

}
