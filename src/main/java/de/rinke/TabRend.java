/*
 * Datei	: Todo3TableCellRenderer.java
 * Package	: todo3.table
 * Projekt	: Todo3
 *
 */
package de.rinke;

import de.rinke.rbc.table.TableCellRendererRBC;

import javax.swing.*;
import java.awt.*;

public class TabRend extends TableCellRendererRBC {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        return this;
    }
}
