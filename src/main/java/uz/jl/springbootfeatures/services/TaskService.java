package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.springbootfeatures.domains.BoardColumn;
import uz.jl.springbootfeatures.domains.Task;
import uz.jl.springbootfeatures.dtos.task.TaskCreateDTO;
import uz.jl.springbootfeatures.dtos.task.TaskGetDTO;
import uz.jl.springbootfeatures.dtos.task.TaskUpdateDTO;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.mappers.TaskMapper;
import uz.jl.springbootfeatures.repository.ColumnRepository;
import uz.jl.springbootfeatures.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final ColumnRepository columnRepository;
    private final TaskMapper mapper;

    public List<TaskGetDTO> getAll(Long columnId, Long userId) {
        return mapper.toDTO(repository.findAllByFalse(columnId, userId));
    }

    public TaskGetDTO get(Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(() ->
                new GenericNotFoundException("Task not Found!", 404)));
    }

    public TaskGetDTO update(TaskUpdateDTO dto) {
        Task task = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Task not Found!", 404);
        });

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());

        return mapper.toDTO(repository.save(task));
    }

    public void create(TaskCreateDTO dto, Long userId) {
        BoardColumn column = columnRepository.findById(dto.getColumnId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Column not Found!", 404);
        });

        Task task = mapper.fromDTO(dto);
        task.setColumn(column);
        task.setCreatedBy(userId);

        repository.save(task);
    }

    public void delete(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Task not Found!", 404);
        });
        task.setDeleted(true);
        repository.save(task);
    }

}
