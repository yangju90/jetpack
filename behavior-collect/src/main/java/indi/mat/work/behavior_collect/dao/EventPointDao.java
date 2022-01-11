package indi.mat.work.behavior_collect.dao;

@Dao
public interface EventPointDao {
      @Query("SELECT * FROM event_point")
    List<EventPoint> getAll();

    @Insert
    void insert(EventPoint eventPoint);
    @Delete
    void delete(EventPoint eventPoint);

    @Query("SELECT COUNT(id) FROM event_point")
    Integer getCount();

    @Query("DELETE FROM event_point;")
    void clear();

    @Query("UPDATE sqlite_sequence SET seq = 0 where name ='event_point'")
    void resetSequence();

    @Query("SELECT * FROM event_point LIMIT :pageSize OFFSET :index")
    List<EventPoint> getpageByIndex(int pageSize,int index);

    @Query("SELECT * FROM event_point ORDER BY time ASC")
    PagingSource<Integer,EventPoint> getAllByPage();
}
