package com.javarush.task.task28_additional;

public class MainClass
{
    public static void main(String[] args)
    {
        int sum5 = sum(5);
        System.out.println(sum5);

        int sum7 = sum(7);
        System.out.println(sum7);
    }

    public static int sum(int n)
    {
        int sum = 0;

        for (int i = 0; i < n; i++)
        {
            sum += i;
        }

        return sum;
    }
}