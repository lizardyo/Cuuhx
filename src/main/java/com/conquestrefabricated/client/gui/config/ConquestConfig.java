package com.conquestrefabricated.client.gui.config;

import com.conquestrefabricated.client.gui.config.forge.ConquestConfigImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap.Entry;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConquestConfig {
   protected static final Logger LOGGER = LoggerFactory.getLogger("Conquest Config");
   protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   public static ConquestConfig INSTANCE = init();
   protected final File file;
   protected final Object2ObjectLinkedOpenHashMap<String, Option<?>> optionMap = new Object2ObjectLinkedOpenHashMap();
   protected final Map<String, Option<?>> optionMapView = Collections.unmodifiableMap(this.optionMap);
   public final Option.BooleanOption plantSlowness = this.addOption(new Option.BooleanOption("plant_slowness", true));
   public final Option.BooleanOption plantBreaking = this.addOption(new Option.BooleanOption("plant_breaking", true));
   public final Option.BooleanOption passThroughLeaves = this.addOption(new Option.BooleanOption("pass_through_leaves", true));
   public final Option.BooleanOption ignore_intro = this.addOption(new Option.BooleanOption("ignore_intro", false));
   public final Option.BooleanOption ignore_dependencies = this.addOption(new Option.BooleanOption("ignore_dependencies", false));
   public final Option.BooleanOption using_modpack = this.addOption(new Option.BooleanOption("using_modpack", false));

   public ConquestConfig(File file) {
      this.file = file;
   }

   public static ConquestConfig init() {
      return ConquestConfigImpl.init();
   }

   public void load() {
      if (this.file.exists()) {
         try (FileReader reader = new FileReader(this.file)) {
            this.fromJson(JsonParser.parseReader(reader));
         } catch (Exception e) {
            LOGGER.error("Could not load config from file '" + this.file.getAbsolutePath() + "'", e);
         }
      }

      this.save();
   }

   public void save() {
      try (FileWriter writer = new FileWriter(this.file)) {
         GSON.toJson(this.toJson(), writer);
      } catch (Exception e) {
         LOGGER.error("Could not save config to file '" + this.file.getAbsolutePath() + "'", e);
      }
   }

   protected void fromJson(JsonElement json) throws JsonParseException {
      if (json.isJsonObject()) {
         JsonObject object = json.getAsJsonObject();
         ObjectBidirectionalIterator<Entry<String, Option<?>>> iterator = this.optionMap.object2ObjectEntrySet().fastIterator();

         while (iterator.hasNext()) {
            Entry<String, Option<?>> entry = (Entry<String, Option<?>>)iterator.next();
            JsonElement element = object.get((String)entry.getKey());
            if (element != null) {
               try {
                  ((Option)entry.getValue()).fromJson(element);
               } catch (JsonParseException e) {
                  LOGGER.error("Could not read option '" + (String)entry.getKey() + "'", e);
               }
            }
         }
      } else {
         throw new JsonParseException("Json must be an object");
      }
   }

   protected JsonElement toJson() {
      JsonObject object = new JsonObject();
      ObjectBidirectionalIterator<Entry<String, Option<?>>> iterator = this.optionMap.object2ObjectEntrySet().fastIterator();

      while (iterator.hasNext()) {
         Entry<String, Option<?>> entry = (Entry<String, Option<?>>)iterator.next();
         object.add((String)entry.getKey(), ((Option)entry.getValue()).toJson());
      }

      return object;
   }

   protected <T extends Option<?>> T addOption(T option) {
      Option<?> old = (Option<?>)this.optionMap.put(option.getKey(), option);
      if (old != null) {
         LOGGER.warn("Option with key '" + old.getKey() + "' was overridden");
      }

      return option;
   }

   @Nullable
   public Option<?> getOption(String key) {
      return (Option<?>)this.optionMap.get(key);
   }

   public Map<String, Option<?>> getOptionMapView() {
      return this.optionMapView;
   }

   static {
      INSTANCE.load();
   }
}
