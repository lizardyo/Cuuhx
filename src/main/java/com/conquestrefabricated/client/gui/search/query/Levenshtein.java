package com.conquestrefabricated.client.gui.search.query;

public final class Levenshtein {
   public Levenshtein() {
   }

   public static double distance(String s1, String s2) {
      if (s1 == null) {
         throw new NullPointerException("s1 must not be null");
      }

      if (s2 == null) {
         throw new NullPointerException("s2 must not be null");
      }

      if (s1.equals(s2)) {
         return 0.0;
      }

      if (s1.length() == 0) {
         return s2.length();
      }

      if (s2.length() == 0) {
         return s1.length();
      }

      int[] v0 = new int[s2.length() + 1];
      int[] v1 = new int[s2.length() + 1];
      int i = 0;

      while (i < v0.length) {
         v0[i] = i++;
      }

      for (int ix = 0; ix < s1.length(); ix++) {
         v1[0] = ix + 1;

         for (int j = 0; j < s2.length(); j++) {
            int cost = 1;
            if (s1.charAt(ix) == s2.charAt(j)) {
               cost = 0;
            }

            v1[j + 1] = Math.min(v1[j] + 1, Math.min(v0[j + 1] + 1, v0[j] + cost));
         }

         int[] vtemp = v0;
         v0 = v1;
         v1 = vtemp;
      }

      return v0[s2.length()];
   }
}
