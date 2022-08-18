package io;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class PersistableIO {
    public static void save(Persistable persistable, String filePath) {
        if(filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Filepath cannot be null or empty");
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(persistable.serialise());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Persistable> T load(Class<T> clazz, String filePath) {
        if(filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Filepath cannot be null or empty");
        }

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            T persistable = clazz.getDeclaredConstructor().newInstance();

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append(line);
            }

            persistable.applySerialisedData(stringBuilder.toString());
            return persistable;
        } catch (IOException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
