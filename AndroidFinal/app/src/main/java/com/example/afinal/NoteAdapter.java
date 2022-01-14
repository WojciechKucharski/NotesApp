package com.example.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, List<Note> note){
        super(context,0, note);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note, parent, false);

        }
        TextView noteTitle = convertView.findViewById(R.id.noteTextViewTitle);
        TextView noteContent = convertView.findViewById(R.id.noteTextViewContent);
        noteTitle.setText(note.getTitle());
        noteContent.setText(note.getContent());

        return convertView;
    }
}
