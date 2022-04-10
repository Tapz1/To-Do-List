package com.zybooks.to_dolist;


import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    public static final String FILENAME = "todolist.txt";

    private Context mContext;
    private List<String> mTaskList;

    // constructor
    public ToDoList(Context context){
        mContext = context;
        mTaskList = new ArrayList<>();
    }

    public void addItem(String item) throws IllegalArgumentException{
        mTaskList.add(item);
    }

    public String[] getItems(){
        // converts the list to an array
        String[] items = new String[mTaskList.size()];
        return mTaskList.toArray(items);
    }

    public void clear(){
        // clears the list >:D
        mTaskList.clear();
    }

    public void saveToFile() throws IOException{
        // this writes the list to a file in internal storage

        FileOutputStream outputStream = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
    }

    public void readFromFile() throws IOException{
        // this reads from files :D

        BufferedReader reader = null;

        try {
            // read in list from file that's in internal storage
            // always need a try or try with resources
            FileInputStream inputStream = mContext.openFileInput(FILENAME);
            reader = new BufferedReader(new InputStreamReader(inputStream));

            mTaskList.clear();
            String line;
            while((line = reader.readLine()) != null){
                mTaskList.add(line);
            }
        }
        catch (FileNotFoundException ex) {
            // only here to catch exceptions
        }
        finally {
            if(reader != null){
                reader.close();
            }
        }
    }

    private void writeListToStream(FileOutputStream outputStream){
        PrintWriter writer = new PrintWriter(outputStream);
        for(String item : mTaskList){
            writer.println(item);
        }

        writer.close();
    }


}
