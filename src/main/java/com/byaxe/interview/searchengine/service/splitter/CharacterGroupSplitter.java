package com.byaxe.interview.searchengine.service.splitter;

import com.byaxe.interview.searchengine.core.enums.Language;
import com.byaxe.interview.searchengine.service.api.ISplitter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CharacterGroupSplitter implements ISplitter {

    /**
     * Basic split on divided character groups,
     * NOT necessarily logical words
     *
     * @param string incoming sentence as string
     * @return list of divided character groups
     */
    @Override
    public List<String> splitString(String string) {
        final String[] strings = string.split("[\\s@&.?$+-]+");
        return Arrays.asList(strings);
    }

    @Override
    public Language getLanguage() {
        return Language.NONE;
    }
}
