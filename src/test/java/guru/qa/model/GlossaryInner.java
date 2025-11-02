package guru.qa.model;

import com.google.gson.annotations.SerializedName;

public class GlossaryInner {

    @SerializedName("SortAs")
    private  String sortAs;

    @SerializedName("GlossTerm")
    private  String glossTerm;

    public String getGlossTerm() {
        return glossTerm;
    }

    public void setGlossTerm(String glossTerm) {
        this.glossTerm = glossTerm;
    }

    public String getSortAs() {
        return sortAs;
    }

    public void setSortAs(String sortAs) {
        this.sortAs = sortAs;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @SerializedName("Acronym")
    private String acronym;
    //        "glossary": {
//        "SortAs": "SGML",
//                "GlossTerm": "Standard Generalized Markup Language",
//                "Acronym": "Kokola"
//    }
}
