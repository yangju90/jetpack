package indi.mat.work.android.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import indi.mat.work.android.model.entity.Word;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import kotlin.jvm.internal.BooleanSpreadBuilder;

@Dao
public interface WordDao {

//    @Insert
//    void insertWords(Word... words);
//
//    @Update
//    void updateWords(Word... words);
//
//    @Delete
//    void deleteWords(Word... words);
//
//    @Query("DELETE FROM WORD")
//    void deleteAllWords();

    @Insert
    Completable insertWords(Word... words);

    @Update
    Completable updateWords(Word... words);

    @Delete
    Completable deleteWords(Word... words);

    @Query("DELETE FROM WORD")
    Completable deleteAllWords();
//
//    @Query("SELECT * FROM WORD ORDER BY  ID DESC")
//    LiveData<List<Word>> getAllWords();

    @Query("SELECT * FROM WORD ORDER BY  ID DESC")
    Flowable<List<Word>> getAllWords();
}
