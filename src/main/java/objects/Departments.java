package objects;


public enum Departments {
    RnD ("R&D"),
    MARKETING("Marketing"),
    CUSTOMER("Customer"),
    SUCCESS("Success"),
    PRODUCT("Product"),
    SALES("Sales"),
    GnA("G&A");

    private final String depName;
    Departments(String name) {
        depName = name;
    }

    public String getDepartmentName() {
        return depName;
    }
}
