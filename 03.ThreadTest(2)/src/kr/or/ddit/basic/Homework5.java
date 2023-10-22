package kr.or.ddit.basic;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
 * 10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
 */
public class Homework5 {

   static int RANK = 1;

   static List<Horse> horse = new ArrayList<Horse>();

   public static void main(String[] args) {

      for (int i = 0; i <= 9; i++) {
         horse.add(new Horse(i + "번 마"));
      }

      for (Horse h : horse) {
         h.start();
      }

      for (Horse h : horse) {
         try {
            h.join();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

      System.out.println("경기 끝...");
      System.out.println("===============================");

      Collections.sort(horse);

      System.out.println("\n경마 종료! 결과");
      for (Horse h : horse) {
         System.out.println(h.getRank() + "등! 몇번마? " + h.getName());
      }
   }
}

class Horse extends Thread implements Comparable<Horse> {
   private String name;
   private int rank;

   public Horse(String name) {
      super(name);
      this.name = name;
   }

   public int getRank() {
      return rank;
   }

   public void setRank(int rank) {
      this.rank = rank;
   }

   @Override
   public void run() {
      for (int i = 0; i < 50; i++) {
         StringBuilder track = new StringBuilder();
         for (int j = 0; j < 50; j++) {
            if (j == i) {
               track.append(">");
            } else {
               track.append("-");
            }
         }
         System.out.println(name + " " + track);
         try {
            Thread.sleep(100);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println(name + "경기 끝...");
      setRank(Homework5.RANK++);
   }

   @Override
   public int compareTo(Horse h) {
      return new Integer(this.getRank()).compareTo(h.getRank());
   }
}