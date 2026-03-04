package com.example.demo.note;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Note> getUserNotesByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> noteRepository.findByUser(user))
                .orElse(Collections.emptyList());
    }

    public void createNote(Note note, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow();
        note.setUser(user);
        noteRepository.save(note);
    }

    public void deleteNote(Long noteId, User user) {
        Note note = noteRepository.findByIdAndUser(noteId, user)
                .orElseThrow(() -> new RuntimeException("Note not found or not yours"));

        noteRepository.delete(note);
    }

    public Note updateNote(Long noteId, Note updatedNote, User user) {
        Note note = noteRepository.findByIdAndUser(noteId, user)
                .orElseThrow(() -> new RuntimeException("Note not found or not yours"));

        // Update fields (adjust based on your Note entity)
        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());

        return noteRepository.save(note);
    }
}
