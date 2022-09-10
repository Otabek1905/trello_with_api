package uz.jl.springbootfeatures.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.jl.springbootfeatures.domains.BoardColumn;
import uz.jl.springbootfeatures.domains.Task;
import uz.jl.springbootfeatures.dtos.column.ColumnCreateDTO;
import uz.jl.springbootfeatures.dtos.column.ColumnGetDTO;
import uz.jl.springbootfeatures.dtos.task.TaskCreateDTO;
import uz.jl.springbootfeatures.dtos.task.TaskGetDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

//    @Mapping(target = "columnId",ignore = true)
    Task fromDTO(TaskCreateDTO dto);

    TaskGetDTO toDTO(Task task);

    List<TaskGetDTO> toDTO(List<Task> workSpaces);
}
