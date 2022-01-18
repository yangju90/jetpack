package indi.mat.work.android.model.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import indi.mat.work.android.base.BaseViewModel;
import indi.mat.work.android.data.RoomRepository;
import indi.mat.work.android.model.entity.Word;

public class RoomViewModel extends BaseViewModel {

    private RoomRepository roomRepository = null;

    private LiveData<List<Word>> listLiveDataWords = null;

    public RoomViewModel() {
        roomRepository = RoomRepository.getInstance();
        listLiveDataWords = new MutableLiveData<>();

        listLiveDataWords = getAllWords();
    }

    public void add(Word... words){
        roomRepository.add(words);
    }

    public void update(Word... words){
        roomRepository.update(words);
    }

    public void delete(Word... words){
        roomRepository.delete(words);
    }
    public void clear(){
        roomRepository.clear();
    }

    public LiveData<List<Word>> getAllWords(){
        return roomRepository.getAllWords();
    }

    public LiveData<List<Word>> getListLiveDataWords() {
        return listLiveDataWords;
    }
}
