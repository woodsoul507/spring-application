package me.givo.applicationdemo.models;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public final class PostElement {
    private Long id;
    private String author;
    private Long authorId;
    private Long likes;
    private Double popularity;
    private Long reads;
    private String[] tags;
}
