package uz.jl.springbootfeatures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.springbootfeatures.domains.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true, value = "select c.* from comment c where c.is_deleted = false and c.task_id = :taskId and exists(select t from task_member t where t.task_id = :taskId and t.member_id = :userId)")
    List<Comment> findAllByFalse(@Param(value = "taskId") Long taskId, @Param(value = "userId") Long userId);

}
