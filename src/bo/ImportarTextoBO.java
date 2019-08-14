/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import pst.MarmitexGeralPST;
import pst.MarmitexPST;
import to.MarmitexTO;

/**
 *
 * @author anderson
 */
public class ImportarTextoBO {

    public ImportarTextoBO() {
    }

    public void importar() {

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_hh-mm");
            Date date = new Date();

            FileReader arqLeitura = new FileReader("C:\\ServComNET\\REFEITORIO\\SAIDA\\REFEITORIO.TXT.txt");
//            FileReader arqLeitura = new FileReader("C:\\ServComNET\\REFEITORIO\\SAIDA\\REFEITORIO_bkp_07-07-2019_03-00.txt");
            FileWriter arqImpressao = new FileWriter("C:\\ServComNET\\REFEITORIO\\SAIDA\\REFEITORIO_bkp_" + dateFormat.format(date) + ".txt");
            BufferedReader lerArq = new BufferedReader(arqLeitura);
            PrintWriter gravarArq = new PrintWriter(arqImpressao);

            String linha = lerArq.readLine();
            int cracha_ant_catraca1 = 0;
            int cracha_atual_catraca1 = 0;
            int cracha_ant_catraca2 = 0;
            int cracha_atual_catraca2 = 0;
            while (linha != null) {

                MarmitexTO marmitexTO = new MarmitexTO();
                System.out.printf("Linha inteira = " + linha + "\n");
                gravarArq.printf(linha + "%n");
                if (linha.substring(0, 4).trim().equals("0050")) {
                    marmitexTO.setPonto("1");
                } else if (linha.substring(0, 4).trim().equals("0051")) {
                    marmitexTO.setPonto("2");
                }

                marmitexTO.setCracha(linha.substring(4, 24).trim());

                String dtStr = linha.substring(24, 32).trim();
                String diaStr = dtStr.substring(0, 2);
                String mesStr = dtStr.substring(2, 4);
                String anoStr = dtStr.substring(4);

                marmitexTO.setData(diaStr + "/" + mesStr + "/" + anoStr);

                String hrStr = linha.substring(32).trim();
                String horaStr = hrStr.substring(0, 2);
                String minStr = hrStr.substring(2, 4);

                marmitexTO.setHora(horaStr + ":" + minStr);

                marmitexTO.setHoraComp(Integer.parseInt(horaStr));

                if (marmitexTO.getHoraComp() < 7) {
                    marmitexTO.setTipo(3);
                } else {
                    marmitexTO.setTipo(1);
                }

                if (Integer.parseInt(marmitexTO.getCracha()) < 10000) {

                    MarmitexPST marmitexPST = new MarmitexPST();
                    marmitexPST.addMarmitex(marmitexTO);

                } else {

                    if (Integer.parseInt(marmitexTO.getPonto()) == 1) {

                        cracha_atual_catraca1 = Integer.parseInt(marmitexTO.getCracha());

                        if (cracha_ant_catraca1 != cracha_atual_catraca1) {
                            MarmitexPST marmitexPST = new MarmitexPST();
                            marmitexPST.addMarmitex(marmitexTO);
                        }

                        cracha_ant_catraca1 = cracha_atual_catraca1;

                    } else if (Integer.parseInt(marmitexTO.getPonto()) == 2) {

                        cracha_atual_catraca2 = Integer.parseInt(marmitexTO.getCracha());

                        if (cracha_ant_catraca2 != cracha_atual_catraca2) {
                            MarmitexPST marmitexPST = new MarmitexPST();
                            marmitexPST.addMarmitex(marmitexTO);
                        }

                        cracha_ant_catraca2 = cracha_atual_catraca2;

                    }

                }

                MarmitexGeralPST marmitexGeralPST = new MarmitexGeralPST();
                marmitexGeralPST.addMarmitex(marmitexTO);

                linha = lerArq.readLine();
            }

            arqLeitura.close();
            arqImpressao.close();

            FileWriter arqLimpar = new FileWriter("C:\\ServComNET\\REFEITORIO\\SAIDA\\REFEITORIO.TXT.txt");//
            arqLimpar.close();//

        } catch (Exception e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

    }

}
