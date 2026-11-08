package com.conquestrefabricated.client.gui.search.query;

import java.util.regex.Pattern;

public class Query {
   private final String raw;
   private final String args;
   private final Pattern tags;
   private final Pattern ordered;
   private final Pattern unordered;

   public Query(String input) {
      input = input.trim().toLowerCase();
      String[] split = input.split(" ");
      StringBuilder args = new StringBuilder();
      StringBuilder tagBuilder = new StringBuilder();
      StringBuilder queryBuilder = new StringBuilder();
      StringBuilder unorderedQueryBuilder = new StringBuilder();

      for (String token : split) {
         if (!token.isEmpty()) {
            if (token.startsWith("#")) {
               tagBuilder.append("(?=.*").append(Pattern.quote(token)).append(")");
            } else {
               if (queryBuilder.length() == 0) {
                  queryBuilder.append("(^").append(Pattern.quote(token)).append(")");
               } else {
                  queryBuilder.append("(.*").append(Pattern.quote(token)).append(")");
               }

               args.append(args.length() > 0 ? " " : "").append(token);
               unorderedQueryBuilder.append("(?=.*").append(Pattern.quote(token)).append(")");
            }
         }
      }

      this.raw = input;
      this.args = args.toString();
      this.tags = Pattern.compile(tagBuilder.toString(), 2);
      this.ordered = Pattern.compile(queryBuilder.toString(), 2);
      this.unordered = Pattern.compile(unorderedQueryBuilder.toString(), 2);
   }

   public <T> Result<T> test(Entry<T> entry) {
      String key = entry.getName();
      String tag = entry.getTags();
      if (this.tags.matcher(tag).find()) {
         double score = Levenshtein.distance(this.args, key);
         if (this.ordered.matcher(key).find()) {
            int rank = 0;
            return new Result<>(entry.getValue(), rank, score);
         } else if (this.unordered.matcher(key).find()) {
            int rank = 1;
            return new Result<>(entry.getValue(), rank, score);
         } else {
            return new Result<>(entry.getValue(), 2, score);
         }
      } else {
         return Result.empty();
      }
   }
}
