package indi.mat.work.android.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "english_word")
    private String word;

    @ColumnInfo(name = "chinese_word")
    private String  chineseMeaning;

    @ColumnInfo(name = "chinese_invisible")
    private Boolean chineseInvisible;

    public Word(String word, String chineseMeaning, Boolean chineseInvisible) {
        this.word = word;
        this.chineseMeaning = chineseMeaning;
        this.chineseInvisible = chineseInvisible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChineseMeaning() {
        return chineseMeaning;
    }

    public void setChineseMeaning(String chineseMeaning) {
        this.chineseMeaning = chineseMeaning;
    }

    public Boolean getChineseInvisible() {
        return chineseInvisible;
    }

    public void setChineseInvisible(Boolean chineseInvisible) {
        this.chineseInvisible = chineseInvisible;
    }
}
