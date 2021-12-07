public class Warehouse implements Serializable {
    private int id;
    private String name;
    private String nameNo;

    public Warehouse(int id, String name, String nameNo) {
        this.id = id;
        this.name = name;
        this.nameNo = nameNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameNo() {
        return nameNo;
    }

    public void setNameNo(String nameNo) {
        this.nameNo = nameNo;
    }
}
