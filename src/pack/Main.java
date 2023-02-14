package pack;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = "";

//        while (!text.equalsIgnoreCase("/w")) {
//            System.out.print("Enter text: ");
//            text = sc.nextLine();
//        }

            System.out.print("Enter zagolovok: ");
              String zagolovok = sc.nextLine();
              zagolovok=zagolovok.toUpperCase();

//             System.out.print("Enter text zametki: ");
//              String zametka = sc.nextLine();

        Path path = Path.of("C:\\Users\\artem\\OneDrive\\Документы\\", "fignia.txt");

        ByteBuffer buffer = ByteBuffer.wrap(zagolovok.getBytes());
//        ByteBuffer buffer1 = ByteBuffer.wrap(zametka.getBytes());

        try (FileChannel open = FileChannel.open(path, CREATE, WRITE, APPEND)) {
            FileLock lock = open.tryLock();
            open.write(buffer.flip());
            buffer.clear();
//            open.write(buffer1.flip());
//            buffer1.clear();


            open.read(buffer);
            zagolovok=new String(buffer.array());
//            open.read(buffer1);
//            zametka=new String(buffer1.array());
//
            System.out.println(zagolovok);
//            System.out.println(zametka);
            lock.release();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}