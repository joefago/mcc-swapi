package edu.mcc.swgen;

import java.util.List;

public class SwApiCharacterList {
    private Integer count;
    private List<SwApiCharacter> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<SwApiCharacter> getResults() {
        return results;
    }

    public void setResults(List<SwApiCharacter> results) {
        this.results = results;
    }
}
