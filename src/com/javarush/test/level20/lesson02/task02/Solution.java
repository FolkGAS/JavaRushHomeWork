package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User us = new User();
            us.setFirstName("Alex");
            us.setLastName("Nope");
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            us.setBirthDate(format.parse("26.11.1985"));
            us.setMale(Boolean.valueOf("true"));
            us.setCountry(User.Country.valueOf("RUSSIA"));
            javaRush.users.add(us);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            for (User usr : loadedObject.users){
                System.out.println(usr.getFirstName());
                System.out.println(usr.getLastName());
                System.out.println(format.format(us.getBirthDate()));
                System.out.println(usr.isMale());
                System.out.println(usr.getCountry().getDisplayedName());
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println(users.size());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            for (User us : users){
                printStream.println(us.getFirstName());
                printStream.println(us.getLastName());
                printStream.println(format.format(us.getBirthDate()));
                printStream.println(us.isMale());
                printStream.println(us.getCountry().getDisplayedName());
            }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int usersSize = Integer.valueOf(reader.readLine());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            if (usersSize > 0)
                for (int i = 0; i < usersSize; i++){
                User us = new User();
                us.setFirstName(reader.readLine());
                us.setLastName(reader.readLine());
                us.setBirthDate(format.parse(reader.readLine()));
                us.setMale(Boolean.valueOf(reader.readLine()));
                String sCountry = reader.readLine();
                if ("Russia".equals(sCountry))
                    us.setCountry(User.Country.RUSSIA);
                else if ("Ukraine".equals(sCountry))
                    us.setCountry(User.Country.UKRAINE);
                else us.setCountry(User.Country.OTHER);
                users.add(us);
            }
        }
    }
}
