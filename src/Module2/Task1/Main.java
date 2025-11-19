package Module2.Task1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileManager handler = new FileManager();

        try {
            // Попробуем загрузить файл (если он есть)
            List<String> data = handler.Load();
            System.out.println("Файл уже существует. Текущее содержимое:");
            data.forEach(System.out::println);
        } catch (MyFileOperationException e) {
            if (e.getMessage().contains("Файл не найден")) {
                System.out.println(e.getMessage());
                System.out.println("Создаём новый файл с начальными данными...");
                List<String> initialData = handler.GenerateInitialData();
                try {
                    handler.Save(initialData);
                    System.out.println("Файл успешно создан!");
                } catch (MyFileOperationException ex) {
                    System.err.println("Не удалось создать файл: " + ex.getMessage());
                    return;
                }
            } else {
                System.err.println("Ошибка при загрузке: " + e.getMessage());
                return;
            }
        }

        try {
            handler.Append("4. Протестировать методы добавления и изменения");
            System.out.println("\nДобавлена новая задача.");

            handler.Update("Статус выполнения: В процессе", "Статус выполнения: Частично завершено");

            System.out.println("\nОбновлённое содержимое файла:");
            List<String> updatedData = handler.Load();
            updatedData.forEach(System.out::println);

        } catch (MyFileOperationException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
}
