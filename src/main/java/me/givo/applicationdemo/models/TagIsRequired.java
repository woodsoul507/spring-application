package me.givo.applicationdemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TagIsRequired {
    private final String error = "Tags parameter is required";
}
