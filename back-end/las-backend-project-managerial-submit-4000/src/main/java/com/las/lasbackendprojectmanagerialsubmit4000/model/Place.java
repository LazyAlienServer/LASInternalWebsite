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
public class Place{

    private String dimension;
    private int x;
    private int y;
    private int z;
}
