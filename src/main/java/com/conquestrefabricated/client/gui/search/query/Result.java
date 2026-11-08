package com.conquestrefabricated.client.gui.search.query;

public class Result<T> implements Comparable<Result<T>> {
   private static final Result<?> EMPTY = new Result(null, Integer.MAX_VALUE, 2.147483647E9);
   private final T result;
   private final int rank;
   private final double score;

   public Result(T result, int rank, double score) {
      this.result = result;
      this.rank = rank;
      this.score = score;
   }

   public T getResult() {
      return this.result;
   }

   public int getRank() {
      return this.rank;
   }

   public boolean isPresent() {
      return this != EMPTY;
   }

   public int compareTo(Result<T> result) {
      if (this.rank == result.rank) {
         return this.score == result.score ? Integer.compare(this.result.hashCode(), result.result.hashCode()) : Double.compare(this.score, result.score);
      } else {
         return Integer.compare(this.rank, result.rank);
      }
   }

   @Override
   public String toString() {
      return String.format("%s, rank=%s, score=%s", this.result, this.rank, this.score);
   }

   public static <T> Result<T> empty() {
      return (Result<T>)EMPTY;
   }
}
