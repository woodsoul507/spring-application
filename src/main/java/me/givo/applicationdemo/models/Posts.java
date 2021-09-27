package me.givo.applicationdemo.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public final class Posts {
    private PostElement[] posts;

}
