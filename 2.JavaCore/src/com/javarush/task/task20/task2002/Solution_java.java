package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution_java {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу

        try {
            String my_path = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2002/";

            File yourFile = File.createTempFile("file_", ".txt", new File(my_path));
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

            Date birthdayDate;
            User user1 = new User();
            user1.setFirstName("user_1_first_name");
            user1.setLastName("user_1_last_name");
            user1.setMale(true);
            birthdayDate = simpleDateFormat.parse("25-12-2000");
            user1.setBirthDate(birthdayDate);
            user1.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user1);

            User user2 = new User();
            user2.setFirstName("user_2_first_name");
            user2.setLastName("user_2_last_name");
            user2.setMale(false);
            birthdayDate = simpleDateFormat.parse("31-12-2001");
            user2.setBirthDate(birthdayDate);
            user2.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user2);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);
            if (this.users.size() > 0) {
                for (User current : this.users) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(current.getFirstName());
                    sb.append("/");
                    sb.append(current.getLastName());
                    sb.append("/");
                    sb.append(current.getBirthDate().getTime());
                    sb.append("/");
                    sb.append(current.isMale());
                    sb.append("/");
                    sb.append(current.getCountry());

                    printWriter.println(sb.toString());
                }
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                User user = new User();
                String[] lineArray = line.split("/");
                if (lineArray.length < 5) {
                    break;
                }
                user.setFirstName(lineArray[0]);
                user.setLastName(lineArray[1]);
                user.setBirthDate(new Date(Long.parseLong(lineArray[2])));
                user.setMale(Boolean.parseBoolean(lineArray[3]));
                switch (lineArray[4]) {
                    case "UKRAINE":
                        user.setCountry(User.Country.UKRAINE);
                        break;
                    case "RUSSIA":
                        user.setCountry(User.Country.RUSSIA);
                        break;
                    default:
                        user.setCountry(User.Country.OTHER);
                        break;
                }
                this.users.add(user);
            }
            reader.close();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
