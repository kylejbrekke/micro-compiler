package SymbolTable;

public class Symbol {

    public String id;
    public String typeId;
    public String value;

    public Symbol(String id, String typeId) {
        this.id = id;
        this.typeId = typeId;
        this.value = null;
    }

    public Symbol(String id, String typeId, String value) {
        this.id = id;
        this.typeId = typeId;
        this.value = value;
    }

    public String getTypeId() {
        return typeId;
    }

}
