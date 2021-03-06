/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.phesus.facturatron.presentation.mvc.model;

import facturatron.Main;
import java.awt.Component;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Octavio
 */
public class FacturaTableModel extends AbstractTableModel {

    //Algunos procesos pueden depender del orden de éstas columnas. Por ejemplo añadir tickets desde omoikane
    String[] columnNames = {"Cantidad",
                        "Código",
                        "Clave Producto SAT",
                        "Descripción",
                        "Unidad",
                        "Clave Unidad SAT",
                        "PU",
                        "Exento",
                        "IVA Tasa 0%",
                        "% IEPS",
                        "Descuento",
                        "Importe"};
    Class[] columnClasses = {
        BigDecimal.class,
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        BigDecimal.class,
        Boolean.class,
        Boolean.class,
        BigDecimal.class,
        BigDecimal.class,
        BigDecimal.class
    };
    private ArrayList<RenglonModel> data = new ArrayList<RenglonModel>();
    private MathContext mc = MathContext.DECIMAL64;
    
    public FacturaTableModel() {
        
    }

    public void addRow() {
        getData().add(new RenglonModel());
        fireTableRowsInserted(getData().size()-1, getData().size()-1);   
        
    }
    
    public void removeRow(int row) {
        getData().remove(row);
        fireTableRowsDeleted(row, row);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        
        return (col != 10);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return getData().size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        RenglonModel fila = getData().get(row);
        switch(col) {
            case 0:
                return fila.getCantidad();
            case 1:
                return fila.getNoIdentificacion();
            case 2:
                return fila.getClaveProductoSat();
            case 3:
                return fila.getDescripcion();
            case 4:
                return fila.getUnidad();
            case 5:
                return fila.getClaveUnidadSat();
            case 6:
                return fila.getValorUnitario();
            case 7:
                return fila.isExento();
            case 8:
                return fila.getTasa0();
            case 9:
                return fila.getTasaIEPS().multiply(new BigDecimal("100.00"), mc);
            case 10:
                return fila.getDescuento();
            case 11:
                return fila.getImporte();
        }
        return null;
    }

    @Override
    public Class getColumnClass(int c) {
        return columnClasses[c];
    }


    
    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        RenglonModel fila = getData().get(row);
        switch(col) {
            case 0:
                fila.setCantidad((BigDecimal)value);
                //updateImporte(fila);
                break;
            case 1:
                fila.setNoIdentificacion((String) value);
                break;
            case 2:
                fila.setClaveProductoSat((String) value);
                break;
            case 3:
                fila.setDescripcion((String) value);
                break;
            case 4:
                fila.setUnidad((String) value);
                break;
            case 5:
                fila.setClaveUnidadSat((String) value);
                break;
            case 6:
                fila.setValorUnitario((BigDecimal)value);
                //updateImporte(fila);
                break;
            case 7:
                fila.setExento((Boolean)value);
                break;
            case 8:
                fila.setTasa0((Boolean) value);
                break;
            case 9:
                fila.setTasaIEPS((BigDecimal) value);
                break;
            case 10:
                fila.setDescuento((BigDecimal) value);
                //updateImporte(fila);
                break;
        }
        if(row == getRowCount()-1 && col == getColumnCount()-6) { //Crear nueva fila cuando se esté en la columna PU de la última fila
            addRow();
        }
        fireTableCellUpdated(row, col);
    }
    /* Esto ya se hace en renglón 
    public void updateImporte(RenglonModel renglon) {
        BigDecimal cantidad  = renglon.getCantidad();
        BigDecimal valor     = renglon.getValorUnitario();
        BigDecimal descuento = renglon.getDescuento();
        BigDecimal subtotal  = cantidad.multiply(valor).subtract(descuento).add(renglon.getIEPS()).add(renglon.getIVA());
        //BigDecimal importe   = subtotal.subtract(descuento);
        BigDecimal importe   = cantidad.multiply(valor); //Resulta que para el SAT el Importe en realidad es el subtotal (sin descuento).
        renglon.setSubtotal(subtotal);
        renglon.setImporte(importe);
    }*/

    /**
     * @return the data
     */
    public ArrayList<RenglonModel> getData() {
        return data;
    }

    /**
     * @param data the data to set
     * 
     */
    public void setData(ArrayList<RenglonModel> data) {
        
        //This block is only used in testing environment
        if(Main.isTest){
            RenglonModel demo = new RenglonModel();
        demo.setCantidad(new BigDecimal("1.212121212"));
        demo.setClaveProductoSat("01010101");
        demo.setClaveUnidadSat("H87");
        demo.setTasaIEPS(new BigDecimal("25.0"));
        demo.setValorUnitario(new BigDecimal("2.323232323232"));
        demo.setDescripcion("Prueba");
        demo.setUnidad("Pza");
        demo.setNoIdentificacion("1");
        demo.setDescuento(new BigDecimal("0.2323232323"));
        data.add(demo);
        demo.updateRenglon();
        }
        
        this.data = data;
    }


    public static class DecimalFormatRenderer extends DefaultTableCellRenderer {
        private static final DecimalFormat formatter = new DecimalFormat( "#,##0.00" );

        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // First format the cell value as required
            setHorizontalAlignment(RIGHT);
            value = formatter.format((Number)value);

            // And pass it on to parent class

            return super.getTableCellRendererComponent(
				table, value, isSelected, hasFocus, row, column );
        }
    }
}