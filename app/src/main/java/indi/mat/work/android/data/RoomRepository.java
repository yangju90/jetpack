package indi.mat.work.android.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import indi.mat.work.android.dao.WordDataBase;
import indi.mat.work.android.model.entity.Word;
import indi.mat.work.android.utilities.MainApplication;
import indi.mat.work.android.utilities.ToastHolder;
import io.reactivex.functions.Action;

public class RoomRepository {
    private static WordDataBase wordDataBase = null;



    public static RoomRepository getInstance() {
        wordDataBase = WordDataBase.getInstance(MainApplication.getInstance());
        return new RoomRepository();
    }

    public void add(Word... words){
        wordDataBase.addDisposable(wordDataBase.getWordDao().insertWords(words), () ->{
            ToastHolder.showToast("Insert Success" + Thread.currentThread().getName());
        });
    }

    public void update(Word... words){
        wordDataBase.getWordDao().updateWords(words);
    }

    public void delete(Word... words){
        wordDataBase.getWordDao().deleteWords(words);
    }
    public void clear(){
        wordDataBase.addDisposable(wordDataBase.getWordDao().deleteAllWords(), () ->{
            ToastHolder.showToast("Clear Success" + Thread.currentThread().getName());
        });
    }

//    public LiveData<List<Word>> getAllWords(){
//        return wordDataBase.getWordDao().getAllWords();
//    }


    public MutableLiveData<List<Word>> getAllWords(){
        MutableLiveData<List<Word>> word = new MutableLiveData<>();

        wordDataBase.addDisposable(wordDataBase.getWordDao().getAllWords(), (List<Word> list) -> {
            word.setValue(list);
        });

        return word;
    }
}
