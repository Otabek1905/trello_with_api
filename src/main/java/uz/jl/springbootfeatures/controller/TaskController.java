package uz.jl.springbootfeatures.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.springbootfeatures.config.security.UserDetails;
import uz.jl.springbootfeatures.dtos.task.TaskCreateDTO;
import uz.jl.springbootfeatures.dtos.task.TaskGetDTO;
import uz.jl.springbootfeatures.dtos.task.TaskUpdateDTO;
import uz.jl.springbootfeatures.response.ApiResponse;
import uz.jl.springbootfeatures.services.TaskService;

import javax.validation.Valid;
import java.util.List;

import static uz.jl.springbootfeatures.controller.ApiController.PATH;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping(PATH + "/task/all/{id}")
    public List<TaskGetDTO> getAll(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getAll(id, userDetails.authUser().getId());
    }

    @PostMapping(PATH + "/task")
    public ApiResponse<Void> create(@Valid @RequestBody TaskCreateDTO dto,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        service.create(dto, userDetails.authUser().getId());
        return new ApiResponse<>(200);
    }

    @GetMapping(PATH + "/task/{id}")
    public ApiResponse<TaskGetDTO> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @DeleteMapping(PATH + "/task/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200);
    }

    @PutMapping(PATH + "/task/update")
    public ApiResponse<TaskGetDTO> update(@Valid @RequestBody TaskUpdateDTO dto) {
        return new ApiResponse<>(service.update(dto));
    }

}
