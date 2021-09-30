package me.givo.applicationdemo.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TagIsInvalid {
    private final String error = "tags parameter is invalid";
}
