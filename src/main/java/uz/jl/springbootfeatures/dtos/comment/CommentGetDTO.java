package uz.jl.springbootfeatures.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentGetDTO {
    private Long id;
    private String body;
    private String createdAt;
    private Long createdBy;
}
