/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q2;

import Q1.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author manso
 */
public class CH3_PART2Q2 {

    public static void main(String[] args) {
      
        
        try  {
             Stream<String> lines = Files.lines(Paths.get("test.txt"));
             Map<Character, Integer> map = lines.
                    flatMap(line -> IntStream.range(0, line.length()).mapToObj(line::charAt)).
                    filter(Character::isLetter).
                    map(Character::toLowerCase).
                    collect(TreeMap::new, (m, c) -> m.merge(c, 1, Integer::sum), Map::putAll);
            map.forEach((letter, c) -> System.out.println(letter + ": " + c));
        } catch (IOException e) {
            System.out.println("Error");
            
        }

       

       

        
    }
}
