package indi.mat.work.android.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import indi.mat.work.android.model.entity.Word;
import indi.mat.work.android.net.base.BaseDataBase;
import indi.mat.work.android.utilities.Constants;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDataBase extends BaseDataBase {

    public abstract WordDao getWordDao();

    private static WordDataBase wordDataBase;


    public static synchronized WordDataBase getInstance(Context context){
        if(wordDataBase == null){
            wordDataBase = Room.databaseBuilder(context, WordDataBase.class, Constants.DATABASE_NAME)
//                    .allowMainThreadQueries()
                     //.fallbackToDestructiveMigration()    // 现有数据全部清空，数据库迁移
                    .addMigrations(MIGRATION_2_3, MIGRATION_3_4)
                    .build();
        }

        return wordDataBase;
    }

    // 添加字段
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN bar_data INTEGER NOT NULL DEFAULT 1");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE word_temp (id INTEGER PRIMARY KEY NOT NULL, " +
                    "english_word TEXT, chinese_word TEXT)");
            database.execSQL("INSERT INTO word_temp (id, english_word, chinese_word) " +
                    "SELECT id, english_word, chinese_word FROM word");
            database.execSQL("DROP TABLE word");
            database.execSQL("ALTER TABLE word_temp RENAME TO word");
        }
    };
}
