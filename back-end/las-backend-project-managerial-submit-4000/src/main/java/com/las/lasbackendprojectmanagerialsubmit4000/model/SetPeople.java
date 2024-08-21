package com.las.lasbackendprojectmanagerialsubmit4000.model;

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
public class SetPeople {
    public List<String> people;
    public String projectName;
    public int progress;
}
