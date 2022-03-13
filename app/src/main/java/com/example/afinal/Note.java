package com.example.afinal;

import java.util.ArrayList;

public class Note {
    private String note_title, note_content, note_id;
    public static ArrayList<Note> noteList = new ArrayList<>();

    public Note(String note_title, String note_content, String note_id){
        setTitle(note_title);
        setContent(note_content);
        setID(note_id);
        noteList.add(this);
    }
    public Note(String note_title, String note_content){
        setTitle(note_title);
        setContent(note_content);
        setID("null");
        noteList.add(this);
    }

    public String getID()  {
        return this.note_id;
    }
    public String getTitle() {
        return this.note_title;
    }
    public String getContent() {
        return this.note_content;
    }
    public void setID(String note_id) {this.note_id = note_id; }
    public void setTitle(String note_title) {
        this.note_title = correctTitle(note_title);
    }
    public void setContent(String note_content) {
        this.note_content = correctContent(note_content);
    }


    private String correctContent(String note_content) {
        if(note_content.length() != 0) {
            return note_content;
        }
        return "Nothing";
    }

    private String correctTitle(String note_title) {
        String new_note_title = note_title;
        if(note_title.length() == 0) {
            new_note_title = "Noname";
        }
        ////////////////////////////////////////////////////
        //while(getAllTitles().contains(new_note_title)){
        //    new_note_title = new_note_title + "_d";
        //}
        ////////////////////////////////////////////////////
        return new_note_title;
    }
    public ArrayList<String> getAllTitles(){
        ArrayList<String> allTitles = new ArrayList<>();
        for(Note note_ : noteList){
            allTitles.add(note_.getTitle());
        }
        return allTitles;
    }
}
