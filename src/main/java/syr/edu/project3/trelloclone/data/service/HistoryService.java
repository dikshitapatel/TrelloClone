package syr.edu.project3.trelloclone.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syr.edu.project3.trelloclone.data.models.History;
import syr.edu.project3.trelloclone.data.models.Task;
import syr.edu.project3.trelloclone.data.repository.HistoryRepository;
import syr.edu.project3.trelloclone.data.repository.TaskRepository;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    HistoryRepository historyRepository;

    public List<History> getHistoryOfTask(long taskId) throws Exception {
        if (!taskRepository.existsById(taskId)) {
            throw new Exception("Not found Task with id = " + taskId);
        }

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Not found"));

        List<History> history = historyRepository.findByTask(task);

        return history;
    }

}
