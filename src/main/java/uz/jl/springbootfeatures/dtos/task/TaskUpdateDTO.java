package uz.jl.springbootfeatures.dtos.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDTO {
    private Long id;
    private String title;
    private String description;
}
