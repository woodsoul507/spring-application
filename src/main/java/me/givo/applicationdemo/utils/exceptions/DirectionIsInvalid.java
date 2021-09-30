package me.givo.applicationdemo.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DirectionIsInvalid {
    private final String error = "direction parameter is invalid";
}
