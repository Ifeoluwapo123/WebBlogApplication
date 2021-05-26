package com.webblog.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentMapper {
    private Long postId;
    private Long id;
    private String username;
    private String name;
    private String comment;
    private String title;
    private String image;
    private Long userId;
}

