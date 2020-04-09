package com.demo.entity.TableView;

import com.demo.entity.BookType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookTypeInfo {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty typeName = new SimpleStringProperty("");

    public BookTypeInfo(BookType type){
        setId(type.getId());
        setTypeName(type.getTypeName());
    }

    @Override
    public String toString() {
        return typeName.get();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTypeName() {
        return typeName.get();
    }

    public SimpleStringProperty typeNameProperty() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName.set(typeName);
    }
}
