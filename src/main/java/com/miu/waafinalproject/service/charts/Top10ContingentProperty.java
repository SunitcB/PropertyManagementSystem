package com.miu.waafinalproject.service.charts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Top10ContingentProperty {
    String title;
    String state;
    Double price;
    String owner;
}
