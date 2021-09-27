package me.givo.applicationdemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SortByIsInvalid {
    private final String error = "sortBy parameter is invalid";
}
