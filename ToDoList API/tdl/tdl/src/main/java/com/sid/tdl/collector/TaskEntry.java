package com.sid.tdl.collector;

import com.sid.tdl.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storage")
public class TaskEntry {

    private final Map<Long, Task> taskEntries = new HashMap<>();

    @GetMapping
    public List<Task> getAll(@RequestParam(required = false) Boolean completed) {
        if (completed == null) {
            return new ArrayList<>(taskEntries.values());
        }
        return taskEntries.values().stream()
                .filter(task -> task.isStatus() == completed)
                .toList();
    }


    @GetMapping("/{myId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long myId) {
        Task task = taskEntries.get(myId);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task mytask) {
        taskEntries.put(mytask.getId(), mytask);
        return ResponseEntity.status(HttpStatus.CREATED).body(mytask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task mytask) {
        if (!taskEntries.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        mytask.setId(id);
        taskEntries.put(id, mytask);
        return ResponseEntity.ok(mytask);
    }

    @DeleteMapping("/{myId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long myId) {
        if (!taskEntries.containsKey(myId)) {
            return ResponseEntity.notFound().build();
        }
        taskEntries.remove(myId);
        return ResponseEntity.noContent().build();
    }
}
