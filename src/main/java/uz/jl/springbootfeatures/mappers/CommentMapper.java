package uz.jl.springbootfeatures.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.jl.springbootfeatures.domains.Comment;
import uz.jl.springbootfeatures.dtos.comment.CommentCreateDTO;
import uz.jl.springbootfeatures.dtos.comment.CommentGetDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

//    @Mapping(target = "taskId",ignore = true)
    Comment fromDTO(CommentCreateDTO dto);

    @Mapping(target = "createdBy",ignore = true)
    CommentGetDTO toDTO(Comment task);

    List<CommentGetDTO> toDTO(List<Comment> workSpaces);
}
