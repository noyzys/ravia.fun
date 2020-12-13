package fun.ravia.common.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.Charsets;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;

public final class ConfigurationLoader {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    public static <T> T loadConfiguration(@NotNull final File dataFolder, final Class<T> clazz, final String name) {
        try {
            dataFolder.mkdirs();

            final File file = new File(dataFolder, name + ".json");

            if (!file.exists()) saveConfiguration(file, clazz.newInstance());

            final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            return GSON.fromJson(bufferedReader, clazz);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void saveConfiguration(@NotNull final File file, final Object object) {
        try {
            final String json = GSON.toJson(object);

            Files.write(file.toPath(), json.getBytes(Charsets.UTF_8));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void saveConfiguration() {

    }
}

