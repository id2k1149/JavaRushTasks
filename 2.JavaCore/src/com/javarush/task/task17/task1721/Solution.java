package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static String firstFileName;
    public static String secondFileName;

    static
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws CorruptedDataException {
        try(BufferedReader fr1 = new BufferedReader(new FileReader(firstFileName))){
            String s1 = null;
            while((s1 = fr1.readLine()) != null){
                allLines.add(s1);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        try(BufferedReader fr2 = new BufferedReader(new FileReader(secondFileName))){
            String s2 = null;
            while((s2 = fr2.readLine()) != null){
                forRemoveLines.add(s2);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        Solution solution = new Solution();
        solution.joinData();

    }

    public void joinData() throws CorruptedDataException {
        if(allLines.containsAll(forRemoveLines)){
            allLines.removeAll(forRemoveLines);
        }else{
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
