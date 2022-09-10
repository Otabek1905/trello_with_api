package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.springbootfeatures.domains.Comment;
import uz.jl.springbootfeatures.domains.Task;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.comment.CommentCreateDTO;
import uz.jl.springbootfeatures.dtos.comment.CommentGetDTO;
import uz.jl.springbootfeatures.dtos.comment.CommentUpdateDTO;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.mappers.CommentMapper;
import uz.jl.springbootfeatures.repository.CommentRepository;
import uz.jl.springbootfeatures.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final TaskRepository taskRepository;
    private final CommentMapper mapper;

    public List<CommentGetDTO> getAll(Long id, Long userId) {
        return mapper.toDTO(repository.findAllByFalse(id,userId));
    }

    public void create(CommentCreateDTO dto, AuthUser user) {
        Task task = taskRepository.findById(dto.getTaskId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Task not found!", 404);
        });

        repository.save(Comment.builder()
                .body(dto.getBody())
                .task(task)
                .isDeleted(false)
                .authUser(user)
                .build());
    }


    public void delete(Long id) {
        Comment comment = repository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Comment not found!", 404);
        });
        comment.setDeleted(true);
        repository.save(comment);
    }

    public CommentGetDTO update(CommentUpdateDTO dto) {
        Comment comment = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Comment not found!", 404);
        });
        comment.setBody(dto.getBody());
        return mapper.toDTO(repository.save(comment));
    }

//    public CommentGetDTO get(Long id) {
//        CommentGetDTO commentGetDTO = mapper.toDTO(repository.findById(id).orElseThrow(() -> {
//            throw new GenericNotFoundException("Comment not Founf!", 404);
//        }));
//
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        commentGetDTO.setCreatedBy(userDetails.authUser().getId());
//        return commentGetDTO;
//    }
}
