package uz.jl.springbootfeatures.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateDTO {
    private Long id;
    private String body;
}
