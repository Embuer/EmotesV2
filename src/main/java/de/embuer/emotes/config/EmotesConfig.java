package de.embuer.emotes.config;

import com.google.common.collect.ImmutableMap;
import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import de.embuer.emotes.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class EmotesConfig {

    private final HashMap<String, String> emotes;
    private final HashMap<String, String> packlink;

    public EmotesConfig() {
        this.emotes = Utils.hashMapOf(ImmutableMap.of(
                "4Head","\uE001",
                "5Head","\uE002",
                "BRUH","\uE003",
                "FeelsBirthdayMan","\uE004",
                "FeelsStrongMan","\uE005"
        ));
        this.packlink = Utils.hashMapOf(ImmutableMap.of(
                "packlink", "https://dl.dropboxusercontent.com/s/7q9vm5n1i17fsr6/EmotePack.zip?dl=0"
        ));
    }

    public HashMap<String, String> getEmotes() {
        return emotes;
    }
    public HashMap<String, String> getPacklink() {
        return packlink;
    }

    public EmotesConfig load(Path dataDirectory) {
        File file = dataDirectory.toAbsolutePath().resolve("hglabor-emotes.toml").toFile();
        if (!dataDirectory.toFile().exists()) {
            dataDirectory.toFile().mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                writeFile(file);
                return applyToClass(file);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            return applyToClass(file);
        }
        return new EmotesConfig();
    }

    private void writeFile(File file) {
        try {
            TomlWriter tomlWriter = new TomlWriter.Builder().build();
            Map<String, Object> map = new HashMap<>();
            map.put("emotes", emotes);
            map.put("packlink", packlink);
            tomlWriter.write(map, file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EmotesConfig applyToClass(File file) {
        Toml toml = new Toml().read(file);
        return toml.to(EmotesConfig.class);
    }

}
