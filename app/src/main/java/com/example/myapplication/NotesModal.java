package com.example.myapplication;

public class NotesModal {

    private String notePath;
    private String noteType;
    private String creationDate;
    private String modificationDate;

    private int id;

    public String getNotePath() {
        return notePath;
    }

    public void setNotePath(String notePath) {
        this.notePath = notePath;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NotesModal(String notePath, String noteType, String creationDate, String modificationDate) {
        this.notePath = notePath;
        this.noteType = noteType;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }
}
