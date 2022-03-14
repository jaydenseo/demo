package com.example.demo.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.example.demo.model.Trader;
import com.example.demo.model.Transaction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo")
public class DemoController {

    // quiz2
    private static List<String> WORDS = Arrays.asList("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea");

    // quiz3
    private static List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    private static List<Integer> numbers2 = Arrays.asList(3, 4);

    // quiz4
    private List<Transaction> transactions;
    private void init() {
        Trader kyu = new Trader("Kyu", "Seoul");
        Trader ming = new Trader("Ming", "Gyeonggi");
        Trader hyuk = new Trader("Hyuk", "Seoul");
        Trader hwan = new Trader("Hwan", "Busan");

        transactions = Arrays.asList(
                new Transaction(kyu, 2019, 30000),
                new Transaction(kyu, 2020, 12000),
                new Transaction(ming, 2020, 40000),
                new Transaction(ming, 2020, 7100),
                new Transaction(hyuk, 2019, 5900),
                new Transaction(hwan, 2020, 4900)
        );
    }

    @GetMapping("/hello")
    public String HelloTest() {
        return "Hello world";
    }
    
    /*
    @GetMapping("/quiz2-1")
    public Map quiz2_1() {
        Map<String, Integer> wordCountMap = new HashMap<>();
        WORDS.stream().map(w -> w.substring(0, 1)).forEach(prefix -> wordCountMap.merge(prefix, 1, (oldValue, newValue) -> (newValue += oldValue)));
        wordCountMap.keySet().forEach(k -> System.out.println(k + ": " + wordCountMap.get(k)));
        return wordCountMap;
    }
    */

    @GetMapping("/quiz2-2")
    public String quiz2_2() {
        Map<String, Integer> wordCountMap = new HashMap<>();
        WORDS.stream().map(w -> w.substring(0, 1)).forEach(prefix -> wordCountMap.merge(prefix, 1, (oldValue, newValue) -> (newValue += oldValue)));
        wordCountMap.keySet().forEach(k -> System.out.println(k + ": " + wordCountMap.get(k)));
        
        String result =  WORDS.stream().filter(w -> w.length()>1).map(String::toUpperCase).map(w -> w.substring(0, 1)).collect(Collectors.joining(" "));
        
        return result;
    }

    @GetMapping("/quiz3-1")
    public List<Integer[]> quiz3_1() {

        Stream<Integer> stream1 = numbers1.stream();
        System.out.println("++ stream1 ++");
        stream1.forEach(System.out::println);

        List<Integer[]> stream2 = numbers1.stream().flatMap(n -> numbers2.stream().map(m -> new Integer[]{n, m})).collect(Collectors.toList());        
        System.out.println("++ stream2 ++");
        String result = stream2.stream().map(a -> "(" + a[0] + ", " + a[1] + ")").collect(Collectors.joining("\n"));
        System.out.println(result);

        return stream2;
    }

    @GetMapping("/quiz3-2")
    public Integer quiz3_2() {

        Stream<Integer> stream1 = numbers1.stream();
        System.out.println("++ stream1 ++");
        stream1.forEach(System.out::println);

        
        //System.out.println("++ stream2 ++");
        //stream1.flatMap(n -> numbers2.stream().map(m -> new Integer[]{n, m})).forEach(System.out::println);

        int maxValue = numbers1.stream().flatMap(n -> numbers2.stream().map(m -> new Integer[]{n, m})).mapToInt(a -> a[0]*a[1]).max().getAsInt();
        System.out.println("++ maxValue ++");
        System.out.println(maxValue);
        return maxValue;
    }


    @GetMapping("/quiz4-1")
    public List<Transaction> quiz4_1() {

        init();
        
        List<Transaction> transactionList = transactions.stream()
            .filter(t -> t.getYear() == 2019)
            .sorted(Comparator.comparing(Transaction::getValue).reversed())
            .collect(Collectors.toList());

        transactionList.forEach(System.out::println);

        return transactionList;
    }
    
    @GetMapping("/quiz4-2")
    public List<String> quiz4_2() {

        init();
        
        List<String> cityList = transactions.stream()
            .map(m -> m.getTrader().getCity())
            .distinct()
            .collect(Collectors.toList());

        System.out.println(cityList);

        return cityList;
    }

    @GetMapping("/quiz4-3")
    public List<String> quiz4_3() {

        init();
        
        List<String> nameList = transactions.stream()
            .filter(t-> t.getTrader().getCity().equals("Seoul"))
            .map( m -> m.getTrader().getName())
            .distinct()
            .sorted()
            .collect(Collectors.toList());

        System.out.println(nameList);

        return nameList;
    }

    @GetMapping("/quiz4-4")
    public List<String> quiz4_4() {

        init();
        
        List<String> nameList = transactions.stream()
            .map( m -> m.getTrader().getName())
            .distinct()
            .sorted()
            .collect(Collectors.toList());

        System.out.println(nameList);

        return nameList;
    }


    @GetMapping("/quiz4-5")
    public List<String> quiz4_5() {

        init();
        
        List<String> nameList = transactions.stream()
            .filter( t -> t.getTrader().getCity().equals("Busan"))
            .map( m -> m.getTrader().getName())
            .distinct()
            .sorted()
            .collect(Collectors.toList());

        System.out.println(nameList);

        boolean isExist = transactions.stream()
            .anyMatch( t -> t.getTrader().getCity().equals("Busan"));
        
        System.out.println(isExist);

        return nameList;
    }


    @GetMapping("/quiz4-6")
    public List<Transaction> quiz4_6() {

        init();
        
        List<Transaction> nameList = transactions.stream()
            .filter( t -> t.getTrader().getCity().equals("Seoul"))
            .collect(Collectors.toList());

        System.out.println(nameList);

        return nameList;
    }


    @GetMapping("/quiz4-7")
    public Integer[] quiz4_7() {

        Integer[] arr = new Integer[2];

        init();
        
        arr[0] = transactions.stream()
            .map( t -> t.getValue())
            .reduce(Integer::max).orElse(-1);

        arr[1] = transactions.stream()
            .min(Comparator.comparingInt(Transaction::getValue))
            .orElseThrow(RuntimeException::new).getValue();

        System.out.println(arr);

        return arr;
    }

    @GetMapping("/quiz5-1")
    public Integer quiz5_1() {

        String[] strArr = {"aaa","bb","c","dddd"};

        Integer size = Arrays.stream(strArr)
            .mapToInt( m -> m.length() )
            .sum();

        System.out.println(size);

        return size;
    }


    @GetMapping("/quiz5-2")
    public Integer quiz5_2() {

        String[] strArr = {"aaa","bb","c","dddd"};

        Integer size = Arrays.stream(strArr)
            .mapToInt( t -> t.length())
            .max()
            .getAsInt();

        System.out.println(size);

        return size;
    }


    @GetMapping("/quiz5-3")
    public List<Integer> quiz5_3() {

        List<Integer> lottoList = new Random().ints(1,46)
            .distinct()
            .limit(6)
            .boxed()
            .collect(Collectors.toList());

        System.out.println(lottoList);

        return lottoList;
    }


    @GetMapping("/quiz5-4")
    public List<Integer[]> quiz5_4() {

        Stream<Integer> dice = IntStream.rangeClosed(1, 6).boxed();

        List<Integer[]> list = dice.flatMap( i -> IntStream.rangeClosed(1, 6).boxed().map( j -> new Integer[] {i,j}))
            .filter( a -> a[0]+a[1]==6)
            .collect(Collectors.toList());

        System.out.println(list);

        return list;
    }

}