package io;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileIO<T> {
    public void writeFile(String pathFile, List<T> data) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File(pathFile).toPath()));
            oos.writeObject(data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<T> readFile(String pathFile){
        List<T> data = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream((Files.newInputStream(new File(pathFile).toPath())));
            data = (List<T>) ois.readObject();
        }catch (IOException | ClassCastException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return data;
    }
}
