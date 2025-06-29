package wednesday.language;

public class Language {
    private String name;
    private int id;

    public Language() {
    }

    public Language(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%-45s %s ", "\nName:", name) +
                String.format("%-45s %d ", "\nId:", id);
    }
}

