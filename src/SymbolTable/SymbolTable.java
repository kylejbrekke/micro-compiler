package SymbolTable;

import java.util.ArrayList;

public class SymbolTable {

    protected ArrayList<Symbol> entries = new ArrayList<>();
    protected String id;

    public SymbolTable(String id) {
        this.id = id;
    }

    public void addEntry(Symbol entry) {
        entries.add(entry);
    }

    public Symbol getEntry(String id) {
        for (Symbol entry: entries) {
            if (entry.id.equals(id)) {
                return entry;
            }
        }
        return null;
    }

    public boolean contains(String id) {
        return getEntry(id) != null;
    }

    public ArrayList<Symbol> getEntries() {
        return entries;
    }
}
