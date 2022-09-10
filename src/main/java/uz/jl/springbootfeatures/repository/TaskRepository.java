package uz.jl.springbootfeatures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.springbootfeatures.domains.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

        @Query(nativeQuery = true, value = "select t.* from task t where t.is_deleted = false and t.column_id = :columnId and exists(select t from board_member t where t.board_id=t.column_id and t.user_id=:userId)")
//        @Query(value = "select t from Task t where t.deleted=false ")
        List<Task> findAllByFalse( Long columnId, Long userId);

}
