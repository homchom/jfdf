package net.jfdf.jfdf.blocks;

import net.jfdf.jfdf.values.CodeValue;
import net.jfdf.jfdf.values.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CallProcessBlock implements CodeBlock {
    private List<CodeValue> items = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private final String processName;

    public CallProcessBlock(String processName) {
        this.processName = processName;
    }

    public CallProcessBlock SetItems(final CodeValue... items) {
        this.items = Arrays.asList(items);

        return this;
    }

    public CallProcessBlock SetItems(List<CodeValue> items) {
        this.items = items;

        return this;
    }

    public CallProcessBlock SetTags(final Tag... tags) {
        this.tags = Arrays.asList(tags);

        for (final Tag tag : tags) {
            tag.setAction("dynamic");
            tag.setBlock("start_process");
        }

        return this;
    }

    public String asJSON() {
        String json = "{\"id\":\"block\",\"block\":\"start_process\",\"args\":{\"items\":[";

        if(items.size() >= 1 || tags.size() >= 1) {
            final List<String> itemsJSON = new ArrayList<>();

            if(tags.size() > 9) tags = tags.subList(0, 8);
            if(items.size() > (27 - tags.size())) items = items.subList(0, 26 - tags.size());

            for (int i = 0; i < items.size(); i++) {
                final CodeValue codeValue = items.get(i);
                itemsJSON.add("{\"item\":" + codeValue.asJSON() + ",\"slot\":" + i + "}");
            }

            for (int i = 26; i >= 27 - tags.size(); i--) {
                Tag tag = tags.get(26 - i);
                itemsJSON.add("{\"item\":" + tag.asJSON() + ",\"slot\":" + i + "}");
            }

            json += String.join(",", itemsJSON);
        }
        json += "]},\"data\":\"" + processName + "\"}";

        return json;
    }
}