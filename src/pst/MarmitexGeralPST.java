/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import to.MarmitexTO;

/**
 *
 * @author anderson
 */
public class MarmitexGeralPST {

    private ArrayList pesagens;

    public MarmitexGeralPST() {
    }

    public void addMarmitex(MarmitexTO marmitexTO){

        String sql = "";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rSet = null;

        try
        {

            System.gc();
            
            conn = ConnectionPST.getInstance().getConnection();
            stmt = conn.createStatement();
            sql = "INSERT INTO "
                        + " CPD.CM_MOVQNT_CATRACA_GERAL (CD_COLAB, HORA_MOV, PONTO, TIPO_REFEICAO)"
                    + " VALUES "
                        + " ( " + marmitexTO.getCracha() +" "
                        + " , TO_DATE('"+ marmitexTO.getData() +" " + marmitexTO.getHora() + "','DD/MM/YYYY HH24:MI') "
                        + " , " + marmitexTO.getPonto() +" "
                        + " , " + marmitexTO.getTipo() +" "
                        + " )";
            System.out.println("SQL = " + sql);
            stmt.executeUpdate(sql);

            try {
                if (rSet != null)
                    rSet.close();
                if (stmt != null)
                    stmt.close();
//                if (conn != null)
//                    conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }catch(Exception e){
            System.out.println("Erro2 = " + e);

        } 

    }

    
}
