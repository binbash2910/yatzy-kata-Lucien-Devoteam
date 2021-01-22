import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Yatzy {

    private int[] dice;

    public Yatzy(int d1, int d2, int d3, int d4, int d5)
    {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = d5;
    }

    public static int chance(int d1, int d2, int d3, int d4, int d5)
    {
        return d1 + d2 + d3 + d4 + d5 ;
    }

    public static int yatzy(int... dice)
    {
        int first = dice[0] ;
        boolean allMatchs = Arrays.stream(dice).allMatch(x -> x == first) ;
        if(allMatchs)
            return 50;
        else
            return 0;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        int sum = 0;
        if (d1 == 1) sum++;
        if (d2 == 1) sum++;
        if (d3 == 1) sum++;
        if (d4 == 1) sum++;
        if (d5 == 1)
            sum++;

        return sum;
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        int sum = 0;
        if (d1 == 2) sum += 2;
        if (d2 == 2) sum += 2;
        if (d3 == 2) sum += 2;
        if (d4 == 2) sum += 2;
        if (d5 == 2) sum += 2;
        return sum;
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        int s;
        s = 0;
        if (d1 == 3) s += 3;
        if (d2 == 3) s += 3;
        if (d3 == 3) s += 3;
        if (d4 == 3) s += 3;
        if (d5 == 3) s += 3;
        return s;
    }

    public int fours()
    {
        int sum;
        sum = 0;
        for (int at = 0; at != 5; at++) {
            if (dice[at] == 4) {
                sum += 4;
            }
        }
        return sum;
    }

    public int fives()
    {
        int s = 0;
        int i;
        for (i = 0; i < dice.length; i++)
            if (dice[i] == 5)
                s = s + 5;
        return s;
    }

    public int sixes()
    {
        int sum = 0;
        for (int at = 0; at < dice.length; at++)
            if (dice[at] == 6)
                sum = sum + 6;
        return sum;
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5)
    {
        Integer[] tallies = new Integer[5];
        tallies[0] = d1;
        tallies[1] = d2;
        tallies[2] = d3;
        tallies[3] = d4;
        tallies[4] = d5;

        List<Integer> list = Arrays.stream(tallies).sorted(Comparator.comparing(x -> x.toString()).reversed()).collect(Collectors.toList());
        Map<Integer, Long> counters = list.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        int oneCount = 0 ;
        int[] values = new int[counters.size()] ;
        for(Map.Entry<Integer, Long> entry : counters.entrySet()){
            if(entry.getValue() >= 2){
                values[oneCount] = entry.getKey() * 2 ;
                oneCount ++ ;
            }
        }

        if(oneCount > 0){
            Arrays.sort(values);
            return values[values.length - 1] ;
        }
        return 0;
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
        Integer[] tallies = new Integer[5];
        tallies[0] = d1;
        tallies[1] = d2;
        tallies[2] = d3;
        tallies[3] = d4;
        tallies[4] = d5;

        Map<Integer, Long> counters = Arrays.stream(tallies).collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        Long sum = new Long("0") ;
        int oneCount = 0 ;
        for(Map.Entry<Integer, Long> entry : counters.entrySet()){
            if(entry.getValue() >= 2){
                sum += entry.getKey() * 2 ;
            }
            else{
                oneCount ++ ;
            }
        }

        if(oneCount == 0 || oneCount == 1)
            return sum.intValue() ;
        return 0;
    }

    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int[] t = new int[5];
        t[0] = d1;
        t[1] = d2;
        t[2] = d3;
        t[3] = d4;
        t[4] = d5;

        for (int i = 0; i < t.length; i++){
            final int y = t[i] ;
            int[] four =  Arrays.stream(t).filter(x -> x == y).toArray();
            if(four.length >= 4)
                return Arrays.stream(four).limit(4).sum() ;
        }
        return 0;
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int[] t = new int[5];
        t[0] = d1;
        t[1] = d2;
        t[2] = d3;
        t[3] = d4;
        t[4] = d5;

        for (int i = 0; i < t.length; i++){
            final int y = t[i] ;
            int[] three =  Arrays.stream(t).filter(x -> x == y).toArray();
            if(three.length >= 3)
                return Arrays.stream(three).limit(3).sum() ;
        }
        return 0;
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies = new int[5];
        tallies[0] = d1;
        tallies[1] = d2;
        tallies[2] = d3;
        tallies[3] = d4;
        tallies[4] = d5;
        Arrays.sort(tallies);

        int[] ordered = IntStream.range(1, 6).toArray();
        if(Arrays.equals(tallies, ordered))
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies = new int[5];
        tallies[0] = d1;
        tallies[1] = d2;
        tallies[2] = d3;
        tallies[3] = d4;
        tallies[4] = d5;
        Arrays.sort(tallies);

        int[] ordered = IntStream.range(2, 7).toArray();
        if(Arrays.equals(tallies, ordered))
            return 20;
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        Integer[] tallies = new Integer[5];
        tallies[0] = d1;
        tallies[1] = d2;
        tallies[2] = d3;
        tallies[3] = d4;
        tallies[4] = d5;

        Map<Integer, Long> counters = Arrays.stream(tallies).collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        Long sum = new Long("0") ;
        boolean match = true ;
        for(Map.Entry<Integer, Long> entry : counters.entrySet()){
            if(entry.getValue() == 2){
                match = true ;
                sum += entry.getKey() * 2 ;
            }
            else if(entry.getValue() ==3){
                match = true ;
                sum += entry.getKey() * 3 ;
            }else{
                match = false ;
                break ;
            }

        }

        if(match)
            return sum.intValue() ;
        return 0;
    }

}
