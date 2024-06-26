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
public class SubmitOriginal{

    private String name;
    private List<String> authors;
    private String start_time;
    private Place place;
    private boolean innovation;
    private String note;

}
