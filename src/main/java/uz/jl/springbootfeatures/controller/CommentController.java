package uz.jl.springbootfeatures.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.springbootfeatures.config.security.UserDetails;
import uz.jl.springbootfeatures.dtos.comment.CommentCreateDTO;
import uz.jl.springbootfeatures.dtos.comment.CommentGetDTO;
import uz.jl.springbootfeatures.dtos.comment.CommentUpdateDTO;
import uz.jl.springbootfeatures.response.ApiResponse;
import uz.jl.springbootfeatures.services.CommentService;

import javax.validation.Valid;
import java.util.List;

import static uz.jl.springbootfeatures.controller.ApiController.PATH;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @GetMapping(PATH + "/comment/all/{id}")
    public List<CommentGetDTO> getAll(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getAll(id, userDetails.authUser().getId());
    }

    @PostMapping(PATH + "/comment")
    public ApiResponse<Void> create(@Valid @RequestBody CommentCreateDTO dto,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        service.create(dto, userDetails.authUser());
        return new ApiResponse<>(200);
    }

//    @GetMapping(PATH + "/comment/{id}")
//    public ApiResponse<CommentGetDTO> get(@PathVariable Long id) {
//        return new ApiResponse<>(service.get(id));
//    }

    @DeleteMapping(PATH + "/comment/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200);
    }

    @PutMapping(PATH + "/comment/update")
    public ApiResponse<CommentGetDTO> update(@Valid @RequestBody CommentUpdateDTO dto) {
        return new ApiResponse<>(service.update(dto));
    }

}
