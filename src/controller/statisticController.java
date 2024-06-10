package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class statisticController {

    public void ghiFileStatistic(String statisticPath, JTable table) {
        TableModel model = table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();

        try (PrintWriter writer = new PrintWriter(statisticPath)) {
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    writer.print(model.getValueAt(row, col));
                    if (col < colCount - 1) {
                        writer.print(",");
                    }
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
