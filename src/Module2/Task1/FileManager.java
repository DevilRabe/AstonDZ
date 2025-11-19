package Module2.Task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final Path FILE_PATH = Paths.get("C:\\LearnJava\\data.txt");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Загружает содержимое файла. Если файла нет — выбрасывает исключение.
    public List<String> Load() throws MyFileOperationException {
        Path path = Paths.get(FILE_PATH.toString());
        if (!Files.exists(path)) {
            throw new MyFileOperationException("Файл не найден: " + FILE_PATH);
        }
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new MyFileOperationException("Ошибка при чтении файла: " + FILE_PATH, e);
        }
    }

    public void Save(List<String> lines) throws MyFileOperationException {
        EnsureDirectoryExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH.toString()))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new MyFileOperationException("Ошибка при записи в файл: " + FILE_PATH, e);
        }
    }

    public void Append(String line) throws MyFileOperationException {
        EnsureDirectoryExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH.toString(), true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new MyFileOperationException("Ошибка при добавлении в файл: " + FILE_PATH.toString(), e);
        }
    }

    public void Update(String oldLine, String newLine) throws MyFileOperationException {
        List<String> lines = Load(); // Если файла нет — исключение здесь
        boolean found = false;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(oldLine)) {
                lines.set(i, newLine);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Предупреждение: строка для замены не найдена: \"" + oldLine + "\"");
        }
        Save(lines);
    }

    private void EnsureDirectoryExists() throws MyFileOperationException {
        Path dir = FILE_PATH.getParent();
        try {
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
        } catch (IOException e) {
            throw new MyFileOperationException("Не удалось создать директорию " + dir.toString(), e);
        }
    }

    // Генерирует начальные данные (для первого запуска)
    public List<String> GenerateInitialData() {
        List<String> data = new ArrayList<>();
        data.add("Имя пользователя: Алексей");
        data.add("Дата и время последнего обновления: " + LocalDateTime.now().format(FORMATTER));
        data.add("Список задач:");
        data.add("1. Завершить Java-проект по работе с файлами");
        data.add("2. Протестировать собственное исключение");
        data.add("3. Сделать резервную копию важных данных");
        data.add("Статус выполнения: В процессе");
        return data;
    }

    public String getFilePath() {
        return FILE_PATH.toString();
    }
}
