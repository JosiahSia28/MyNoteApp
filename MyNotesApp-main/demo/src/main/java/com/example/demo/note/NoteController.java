package com.example.demo.note;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ... other imports ...

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public Note createNote(@RequestBody Note note, Authentication auth) {
        // The principal is expected to be a User object based on JwtFilter
        User user = (User) auth.getPrincipal();
        note.setUser(user);
        return noteRepository.save(note);
    }

    @GetMapping("/user/{username}")
    public List<Note> getNotesByUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return noteService.getUserNotesByUsername(username);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(
            @PathVariable Long id,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        noteService.deleteNote(id, user);

        return ResponseEntity.ok("Note deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> editNote(
            @PathVariable Long id,
            @RequestBody Note note,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        Note updatedNote = noteService.updateNote(id, note, user);

        return ResponseEntity.ok(updatedNote);
    }
}
