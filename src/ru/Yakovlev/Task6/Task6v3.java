package ru.Yakovlev.Task6;

import ru.Yakovlev.Task4.Task4n5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task6v3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the length of tickets (n): ");
        int n = scanner.nextInt();
        System.out.print("Enter the biggest number of the computational system: ");
        int maxNumber = scanner.nextInt();
        BigInteger count = BigInteger.ONE;
        int i = 1;
        ArrayList<Repetition>[] repetitions = new ArrayList[n / 2 * maxNumber];
        for(int j = 0; j < repetitions.length; j ++){
            repetitions[j] = new ArrayList<>();
        }
        while (i <= n / 2){
            int sum = i;
            int small_sum_max = maxNumber * i;
            do{
                Integer[] rep1 = generateRepetition(maxNumber, sum, i);
                do{
                    Integer[] rep2 = generateRepetition(maxNumber, sum, n - i);
                    do{
                        Integer[] rep3 = unite(rep1, rep2);
                        Repetition repetition = new Repetition(rep3);
                        if(!repetitions[sum - 1].contains(repetition)){
                            repetitions[sum - 1].add(repetition);
                            count = count.add(calculateTranspositions(rep3));
                        }
                        rep2 = next(rep2, maxNumber);
                    }
                    while (sum(rep2) == sum);
                    rep1 = next(rep1, maxNumber);
                }
                while (sum(rep1) == sum);
                sum ++;
            }
            while (sum <= small_sum_max);
            i ++;
        }
        System.out.println(count);
    }

    static Integer[] unite (Integer[] repetition1, Integer[] repetition2){
        Integer result[] = new Integer[repetition1.length];
        for(int i = 0; i < result.length; i ++){
            result[i] = repetition1[i] + repetition2[i];
        }
        return result;
    }

    static Integer[] generateRepetition(int maxNumber, int sum, int count){
        Integer[] result = emptyRep(maxNumber);
        result[maxNumber] = sum / maxNumber;
        count -= sum / maxNumber;
        if(sum % maxNumber != 0){
            result[sum % maxNumber] ++;
            count --;
        }
        result[0] = count;
        return result;
    }

    static int sum(Integer[] repetition){
        int sum = 0;
        for(int i = 1; i < repetition.length; i ++)
            sum += i * repetition[i];
        return sum;
    }

    static Integer[] emptyRep(int maxValue){
        Integer[] result = new Integer[maxValue + 1];
        for (int i = 0; i < result.length; i ++){
            result[i] = 0;
        }
        return result;
    }

    static Integer[] next(Integer[] repetition, Integer maxNumber){
        return calculateRepetition(Task6v2.actuallyNextLine(fromRepetition(repetition), maxNumber), maxNumber);
    }

    static int findSmallest(Integer[] repetition){
        for(int i = 0; i < repetition.length; i++)
            if (repetition[i] > 0)
                    return i;
        return -1;
    }

    static int findSmallestNotZero(Integer[] repetition){
        for(int i = 1; i < repetition.length; i ++)
            if(repetition[i] > 0)
                return i;
        return -1;
    }

    static int findBiggest(Integer[] repetition){
        for(int i = repetition.length - 1; i >= 0; i --)
            if(repetition[i] > 0)
                return i;
        return -1;
    }

    static int countAll(Integer[] repetition){
        int count = 0;
        for(Integer c: repetition){
            count += c;
        }
        return count;
    }

    static int countAllButZero(Integer[] repetition){
        int count = 0;
        for(int i = 0; i < repetition.length; i++){
            count += repetition[i];
        }
        return count;
    }

    static Integer[] calculateRepetition(Character[] line, Integer maxNumber){
        Integer[] result = new Integer[maxNumber + 1];
        Arrays.fill(result, 0);
        for (Character character : line) result[Task4n5.rawValue(character)]++;
        return result;
    }

    static Character[] fromRepetition(Integer[] repetition){
        Character[] result = new Character[countAll(repetition)];
        int j = 0;
        for(int i = repetition.length - 1; i >= 0; i --){
            for(int k = 0; k < repetition[i]; k ++){
                result[j] = Task4n5.ofRawValue(i);
                j ++;
            }
        }
        return result;
    }

    static BigInteger factorial(BigInteger n){
        BigInteger result = BigInteger.ONE;
        while (n.compareTo(BigInteger.ONE) > 0){
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        return result;
    }

    static BigInteger calculateTranspositions(Integer[] repetition){
        BigInteger factOfSum;
        BigInteger sum = BigInteger.ZERO;
        BigInteger multOfFacts = BigInteger.ONE;
        for (Integer integer : repetition) {
            sum = sum.add(BigInteger.valueOf(integer));
            multOfFacts = multOfFacts.multiply(
                    factorial(BigInteger.valueOf(integer))
            );
        }
        factOfSum = factorial(sum);
        return factOfSum.divide(multOfFacts);
    }

}
