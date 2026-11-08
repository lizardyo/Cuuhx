package com.conquestrefabricated.client.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

public class TextUtils {
   public TextUtils() {
   }

   public static void wrapComplexTextToList(Component text, Font textRenderer, int maxWidth, List<Component> resultList) {
      List<TextUtils.StyledTextSegment> segments = new ArrayList<>();
      text.visit((stylex, string) -> {
         segments.add(new TextUtils.StyledTextSegment(string, stylex));
         return Optional.empty();
      }, Style.EMPTY);
      MutableComponent currentLine = Component.literal("");
      int currentLineWidth = 0;

      for (TextUtils.StyledTextSegment segment : segments) {
         String content = segment.text;
         Style style = segment.style;

         for (String word : splitIntoWords(content)) {
            int wordWidth = getWidth(word, textRenderer, style);
            if (currentLineWidth + wordWidth > maxWidth) {
               if (!currentLine.getString().isEmpty()) {
                  resultList.add(currentLine);
                  currentLine = Component.literal("");
                  currentLineWidth = 0;
               }

               if (wordWidth > maxWidth) {
                  splitLongWord(word, textRenderer, style, maxWidth, resultList);
               } else {
                  currentLine = currentLine.append(Component.literal(word).setStyle(style));
                  currentLineWidth = wordWidth;
               }
            } else {
               currentLine = currentLine.append(Component.literal(word).setStyle(style));
               currentLineWidth += wordWidth;
            }
         }
      }

      if (!currentLine.getString().isEmpty()) {
         resultList.add(currentLine);
      }
   }

   private static List<String> splitIntoWords(String text) {
      List<String> words = new ArrayList<>();
      StringBuilder currentWord = new StringBuilder();
      boolean inWhitespace = false;

      for (int i = 0; i < text.length(); i++) {
         char c = text.charAt(i);
         boolean isWhitespace = Character.isWhitespace(c);
         if (isWhitespace != inWhitespace && currentWord.length() > 0) {
            words.add(currentWord.toString());
            currentWord = new StringBuilder();
         }

         currentWord.append(c);
         inWhitespace = isWhitespace;
      }

      if (currentWord.length() > 0) {
         words.add(currentWord.toString());
      }

      return words;
   }

   private static void splitLongWord(String word, Font textRenderer, Style style, int maxWidth, List<Component> resultList) {
      int start = 0;

      while (start < word.length()) {
         int end = findFittingCharacters(word.substring(start), textRenderer, style, maxWidth);
         String part = word.substring(start, start + end);
         resultList.add(Component.literal(part).setStyle(style));
         start += end;
      }
   }

   private static int findFittingCharacters(String text, Font textRenderer, Style style, int maxWidth) {
      if (text.isEmpty()) {
         return 0;
      }

      int low = 0;
      int high = text.length();

      while (low < high) {
         int mid = (low + high + 1) / 2;
         int width = getWidth(text.substring(0, mid), textRenderer, style);
         if (width <= maxWidth) {
            low = mid;
         } else {
            high = mid - 1;
         }
      }

      return Math.max(1, low);
   }

   private static int getWidth(String text, Font textRenderer, Style style) {
      return textRenderer.width(Component.literal(text).setStyle(style));
   }

   private static class StyledTextSegment {
      public final String text;
      public final Style style;

      public StyledTextSegment(String text, Style style) {
         this.text = text;
         this.style = style;
      }
   }
}
