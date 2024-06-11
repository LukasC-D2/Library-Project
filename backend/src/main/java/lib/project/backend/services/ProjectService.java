package lib.project.backend.services;

import lib.project.backend.model.dto.ProjectCreationDTO;
import lib.project.backend.model.dto.TaskCreationDTO;
import lib.project.backend.model.entity.Project;
import lib.project.backend.model.entity.Task;

import java.util.List;

public interface ProjectService {
    Project createProject(ProjectCreationDTO projectCreationDTO);
    Project getProjectById(Long id);
    void deleteProjectById(Long id);
    Project updateProject(Long id, Project project);
    List<Project> getAllProjects();
    List<Project> findProjectsByName(String name, String show);
    Task createProjectTask(Long projectId, TaskCreationDTO taskCreationDTO);
    boolean deleteProjectTask(Long projectId, Long taskId);
    Task updateProjectTask(Long id, Long taskID, Task task);
    List<Task> getAllProjectTasks(Long projectId);
    List<Task> findProjectTasksByName(Long projectId, String name);
}