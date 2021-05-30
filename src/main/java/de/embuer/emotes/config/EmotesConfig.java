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

    public EmotesConfig() {
        this.emotes = Utils.hashMapOf(ImmutableMap.of(
           "D:", "\uE003",
           "PauseChamp", "\uE004",
           "PepeLaugh", "\uE006",
           "Sadge", "\uE009",
           "WeirdChamp", "\uE011"
        ));
    }

    public HashMap<String, String> getEmotes() {
        return emotes;
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
