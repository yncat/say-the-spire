package sayTheSpire;

import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import com.moandjiezana.toml.Toml;
import com.evacipated.cardcrawl.modthespire.lib.ConfigUtils;
import com.moandjiezana.toml.TomlWriter;


public class STSConfig {

    private Toml toml;

    public STSConfig() {
        File dir = new File(getDirectoryPath());
        dir.mkdirs();
        File file = new File(getFilePath());
        HashMap<String, Object> defaults = this.getDefaults();
        try {
            HashMap<String, Object> fileSettings = (HashMap<String, Object>)new Toml().read(file).toMap();
            merge(defaults, fileSettings);
            this.toml = new Toml().read(new TomlWriter().write(defaults));
                System.out.println("STSConfig: Config loaded from existing file.");
        }catch (Exception e) {
            System.out.println("STSConfig: No config file found, using defaults.");
            this.toml = new Toml().read(new TomlWriter().write(defaults));
        }
            }

            public static void merge(HashMap<String, Object> base, HashMap<String, Object>merger) {
                for (Map.Entry<String, Object> entry:merger.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (base.containsKey(key) && base.get(key) instanceof HashMap && value instanceof HashMap) {
                        merge((HashMap<String, Object>)base.get(key), (HashMap<String, Object>)value);
                    } else {
                        base.put(key, value);
                    }
                }
            }

    public void save() throws IOException {
        TomlWriter writer = new TomlWriter();
        File file = new File(getFilePath());
        writer.write(this.toml.toMap(), file);
    }

    public static HashMap<String, Object> getDefaults() {
        HashMap<String, Object> defaults = new HashMap();
        HashMap<String, Object> uiDefaults = new HashMap();
        uiDefaults.put("read_positions", true);
        uiDefaults.put("read_banner_text", true);
        uiDefaults.put("read_proceed_text", true);
        HashMap<String, Object> mapDefaults = new HashMap();
        mapDefaults.put("read_reversed_paths", true);

        HashMap<String, Object> combatDefaults = new HashMap();
        combatDefaults.put("block_text", true);
        combatDefaults.put("buff_debuff_text", true);
                defaults.put("ui", uiDefaults);
        defaults.put("map", mapDefaults);
        defaults.put("combat", combatDefaults);
        return defaults;
    }

    public static String getDirectoryPath() {
        return ConfigUtils.CONFIG_DIR + File.separator + "sayTheSpire" + File.separator;
    }

    public static String getFilePath() {
        return getDirectoryPath() + "settings.ini";
    }

    public String getString(String key) {
        return this.toml.getString(key);
    }

    public String getString(String key, String defaultValue) {
        return this.toml.getString(key, defaultValue);
    }

    public Long getLong(String key) {
        return this.toml.getLong(key);
    }

    public Long getLong(String key, Long defaultValue) {
        return this.toml.getLong(key, defaultValue);
    }

    public Boolean getBoolean(String key) {
        return this.toml.getBoolean(key);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return this.toml.getBoolean(key, defaultValue);
    }

    public Double getDouble(String key) {
        return this.toml.getDouble(key);
    }

    public Double getDouble(String key, Double defaultValue) {
        return this.toml.getDouble(key, defaultValue);
    }
}
