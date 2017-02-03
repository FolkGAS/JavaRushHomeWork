package com.javarush.test.level32.lesson06.task01;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
        {
            ByteArrayOutputStream password = getPassword();
            System.out.println(password.toString());
        }
    }

    public static ByteArrayOutputStream getPassword() {
        byte[] pass = new byte[8];
        int countInts = 0;
        int countUpper = 0;
        int countLower = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < 8; i++){
            int choise = ThreadLocalRandom.current().nextInt(1,3);
            if (i > 4)
                if (countInts == 0)
                    choise = 1;
                else if (countUpper == 0)
                    choise = 2;
                else if (countLower == 0)
                    choise = 3;
            switch (choise)
            {
                case 1 : pass[i] = (byte)ThreadLocalRandom.current().nextInt(48, 58);
                         countInts++;
                         break;
                case 2 : pass[i] = (byte)ThreadLocalRandom.current().nextInt(65, 91);
                         countUpper++;
                         break;
                case 3 : pass[i] = (byte)ThreadLocalRandom.current().nextInt(97, 123);
                         countLower++;
                         break;
            }
        }
        try (ByteArrayInputStream bais = new ByteArrayInputStream(pass))
        {
            byte[] buffer = new byte[bais.available()];
            bais.read(buffer);
            baos.write(buffer);
        }
        catch (IOException e){}
        return baos;
    }
}
