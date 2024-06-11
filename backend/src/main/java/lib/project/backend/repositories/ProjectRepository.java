package lib.project.backend.repositories;

import lib.project.backend.model.entity.Project;
import lib.project.backend.model.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByNameContainingIgnoreCaseAndStatus(String name, ProjectStatus status);
    List<Project> findByNameContainingIgnoreCase(String name);
    List<Project> findByStatus(ProjectStatus status);
}