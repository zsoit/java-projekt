package app.controller;

import app.model.MyTableModel;
import app.view.TableView;

public class MyTableController {
    private MyTableModel model;
    private TableView view;

    public MyTableController(MyTableModel model, TableView view) {
        this.model = model;
        this.view = view;
        view.getTable().setModel(model);
    }

    // Metody do manipulowania danymi w modelu
    public void setValueAt(Object value, int row, int column) {
        model.setValueAt(value, row, column);
    }

    public Object getValueAt(int row, int column) {
        return model.getValueAt(row, column);
    }
}
