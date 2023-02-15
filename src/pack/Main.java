package pack;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        String fileName = "notes.txt";
        Charset fileCharset = Charset.defaultCharset();
        System.out.println("Введите кодировку файла (например, UTF-8): ");
        Scanner scanner = new Scanner(System.in);
        String charsetName = scanner.nextLine();
        fileCharset = Charset.forName(charsetName);

        boolean exit = false;
        while (!exit) {
            System.out.println("Введите команду (выбрать файл - /f, записаить заметку - /w, прочитать все заметки - /r, выход - /exit): ");
            String command = scanner.nextLine();
            switch (command) {
                case "/f" -> {
                    System.out.print("Введите путь к файлу: "); // не совсем удобно
                    fileName = scanner.nextLine();
                }
                case "/w" -> {
                    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), fileCharset))) {
                        System.out.println("Введите заголовок: ");
                        String title = new Scanner(System.in).nextLine();
                        System.out.println("Введите текст заметки: ");
                        String text = new Scanner(System.in).nextLine();

                        writer.write(LocalDate.now().toString()+"\n");
                        writer.write(title.toUpperCase() + "\n");
                        writer.write(text + "\n\n");
                        System.out.println("Запись успешна");
                    } catch (IOException e) {
                        System.err.println("Ошибка записи " + e.getMessage());
                    }
                }
                case "/r" -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), fileCharset))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.equals("-----")) {
                                String title = reader.readLine();
                                String text = reader.readLine();
                                reader.readLine();
                                System.out.println(title);
                                System.out.println(text);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.err.println("Файл не знайдено. Введіть інший файл.");
                    } catch (IOException e) {
                        System.err.println("Помилка читання файлу: " + e.getMessage());
                    }
                }
                case "/exit" -> exit = true;
                default -> System.out.println("Невідома команда.");
            }
        }
    }
}
