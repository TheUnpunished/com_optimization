package ru.Yakovlev.Task6;

import ru.Yakovlev.Task4.Task4n5;

import java.math.BigInteger;
import java.util.*;

public class Task6v2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the length of tickets (n): ");
        int n = scanner.nextInt();
        System.out.print("Enter the biggest number of the computational system: ");
        int maxNumber = scanner.nextInt();
        BigInteger count = BigInteger.ONE;
        int i = 1;
        ArrayList<Repetition> repetitions = new ArrayList<>();
        while (i <= n / 2){
            int small_sum_max = maxNumber * i;
            int sum = 1;
            do{
                Character[] line1 = firstLine(sum, i, maxNumber);
                do{
                    Character[] line2 = firstLine(sum, n - i, maxNumber);
                    do{
                        if(sum(line1) == sum(line2)){
                            Character[] unitedLine = unite(line1, line2);
                            Repetition repetition = calculateRepetition(unitedLine, maxNumber);
                            if(!repetitions.contains(repetition)){
                                repetitions.add(repetition);
                                count = count.add(calculateTranspositions(repetition));
                                System.out.println(count);
                            }
                        }
                        line2 = actuallyNextLine(line2, maxNumber);
                    }
                    while (line2[0] != '0');
                    line1 = actuallyNextLine(line1, maxNumber);
                }
                while (line1[0] != '0');
                sum ++;
            }
            while (sum <= small_sum_max);
            i ++;
        }
        System.out.println(count);

    }

    public static Character[] nextLine (Character[] prevLine, int maxNumber, int index){
        try{
            if(index >= 0){
                if(Task4n5.rawValue(prevLine[index + 1]) >= 0
                        && Task4n5.rawValue(prevLine[index + 1]) < maxNumber){
                    prevLine[index] = Task4n5.ofRawValue(Task4n5.rawValue(prevLine[index]) - 1);
                    prevLine[index + 1] = Task4n5.ofRawValue(Task4n5.rawValue(prevLine[index + 1]) + 1);
                    int sum = 0;
                    for (Character character : prevLine) sum += Task4n5.rawValue(character);
                    int sum_prev = 0;
                    for (int i = 0; i <= index; i ++)
                        sum_prev += Task4n5.rawValue(prevLine[i]);
                    Character[] line = firstLine(sum - sum_prev,
                            prevLine.length - (index + 1),
                            Task4n5.rawValue(prevLine[index]));
                    for(int i = 0; i < line.length; i++){
                        prevLine[index + i + 1] = line[i];
                    }
                }
            }
            else {
                prevLine[0] = '0';
                return prevLine;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            return Task6n5.sort(prevLine);
        }
        return Task6n5.sort(prevLine);
    }

    public static Character[] actuallyNextLine(Character[] prevLine, int maxNumber){
        int firstMod = firstModifiableIndex(prevLine, 0);
        int lastMod = lastModifiableIndex(prevLine, 0);
        int index;
        if(firstMod != -1 & lastMod != -1)
            index = lastMod;
        else
            index = -1;
        return nextLine(prevLine, maxNumber, index);
    }

    public static Character[] firstLine(int sum, int n, int maxNumber){
        Character[] result = new Character[n];
        int i = 0;
        while (i < n & sum > 0){
            result[i] = sum / maxNumber > 0
                    ? Character.valueOf(Task4n5.ofRawValue(maxNumber))
                    : Character.valueOf(Task4n5.ofRawValue(sum % maxNumber));
            sum -= Task4n5.rawValue(result[i]);
            i ++;
        }
        if (sum > 0)
            result[0] = '0';
        else
            fillWithZeros(result);
        return result;
    }

    public static Character[] fillWithZeros(Character[] line){
        for (int i = 0; i < line.length; i ++)
            line[i] = (line[i] == null) ? Character.valueOf('0') : line[i];
        return line;
    }

    public static int lastModifiableIndex(Character[] line, int offset){
        int index = 0;
        try{
            for (int i = 0; i < line.length - 1; i ++){
                if (Task4n5.rawValue(line[i]) - 1 >= Task4n5.rawValue(line[i + 1])
                        & Task4n5.rawValue(line[i]) >= 1)
                    if (!noMoreRoom(
                            Arrays.copyOfRange(line, i, line.length),
                            Task4n5.rawValue(line[i]) - 1
                    ))
                        index = i + offset;
                    else {
                        Character NMR_char = line[i];
                        index = i + offset - 1;
                        while (NMR_char == line[index])
                            index --;
                        break;
                    }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            return -1;
        }
        return index;
    }

    public static int firstModifiableIndex(Character[] line, int offset){
        for (int i = 0; i < line.length - 1; i ++){
            try{
                if (Task4n5.rawValue(line[i]) - 1 >= Task4n5.rawValue(line[i + 1])
                        & Task4n5.rawValue(line[i]) - 1 > 0)
                    return i + offset;
            }
            catch (ArrayIndexOutOfBoundsException e){
                if (i > 0 && Task4n5.rawValue(line[i - 1]) - 1 >= Task4n5.rawValue(line[i])
                        & Task4n5.rawValue(line[i - 1]) - 1 > 0)
                    return i + offset;
                else
                    return -1;
            }
        }
        return -1;
    }

    public static boolean noMoreRoom(Character[] line, int offset){
        // 2 and 1 1 1 situation
        boolean result = false;
        try {
            result = line[0] == Task4n5.ofRawValue(
                    offset + 1
            );
            int i = 1;
            while (result && i < line.length){
                result = (
                        (line[i] == Task4n5.ofRawValue(offset) | line[i] == Task4n5.ofRawValue(offset + 1)) & result
                );
                i ++;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            return result;
        }
        return result;
    }

    static Character[] unite (Character[] line1, Character[] line2){
        int i = 0;
        int j = 0;
        Character[] result = new Character[line1.length + line2.length];
        while (i + j < result.length){
            while (i < line1.length && (j == line2.length || Task4n5.rawValue(line1[i]) >= Task4n5.rawValue(line2[j]))){
                result[i + j] = line1[i];
                i ++;
            }
            while (j < line2.length && (i == line1.length || Task4n5.rawValue(line2[j]) >= Task4n5.rawValue(line1[i]))){
                result[i + j] = line2[j];
                j ++;
            }
        }
        return result;
    }

    static Repetition calculateRepetition(Character[] line, int maxNumber){
        Integer[] result = new Integer[maxNumber + 1];
        Arrays.fill(result, 0);
        for (Character character : line) result[Task4n5.rawValue(character)]++;
        return new Repetition(result);
    }

    static boolean newRepetition(Integer[] repetition, ArrayList<Integer[]> repetitions){
        if(repetitions.size() == 0)
            return true;
        int sum = 0;
        for(int i = 0; i < repetition.length; i ++){
            sum += repetition[i] * i;
        }
        boolean found = false;
        int i = 0;
        while (!found & i < repetitions.size()){
            Integer[] repetition1 = repetitions.get(i);
            int sum2 = 0;
            for (int j = 0; j < repetition1.length; j ++){
                sum2 += repetition1[j] * j;
            }
            if(sum == sum2){
                found = true;
                int j = 0;
                while (found & j < repetition.length){
                    found = repetition[j].equals(repetition1[j]) & found ;
                    j ++;
                }
            }
            else {
                found = false;
            }
            i ++;
        }
        return !found;
    }

    static BigInteger calculateTranspositions(Repetition rep){
        Integer[] repetition = rep.getRep();
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

    static BigInteger factorial(BigInteger n){
        BigInteger result = BigInteger.ONE;
        while (n.compareTo(BigInteger.ONE) > 0){
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        return result;
    }

    static int sum(Character[] line){
        int sum = 0;
        for (Character character : line) sum += Task4n5.rawValue(character);
        return sum;
    }
}
