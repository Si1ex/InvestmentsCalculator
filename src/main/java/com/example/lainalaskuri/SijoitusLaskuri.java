package com.example.lainalaskuri;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SijoitusLaskuri extends Application {
    private TextField sijoituksenMaara = new TextField();       //Luodaan tekstikentät
    private TextField vuosienMaara = new TextField();
    private TextField vuosikorko = new TextField();
    private TextField tuottoOdotus = new TextField();
    private Button laske = new Button("Laske");

    public void start(Stage stage) {
        GridPane paneeli = new GridPane();  //Luodaan paneeli
        paneeli.setHgap(5);     //Lisätään tilaa solujen välille
        paneeli.setVgap(5);

        paneeli.add(new Label("Sijoituksen määrä: "), 0,0);     //Lisätään solmut
        paneeli.add(sijoituksenMaara, 1,0);
        paneeli.add(new Label("Vuosien määrä: "), 0, 1);
        paneeli.add(vuosienMaara, 1,1);
        paneeli.add(new Label("Vuosikorko: "), 0, 2);
        paneeli.add(vuosikorko, 1,2);
        paneeli.add(new Label("Tuotto odotus: "), 0,3);
        paneeli.add(tuottoOdotus, 1,3);
        paneeli.add(laske,1,4);

        paneeli.setAlignment(Pos.CENTER);       //Määritellään asettelua
        sijoituksenMaara.setAlignment(Pos.BOTTOM_RIGHT);
        vuosienMaara.setAlignment(Pos.BOTTOM_RIGHT);
        vuosikorko.setAlignment(Pos.BOTTOM_RIGHT);
        tuottoOdotus.setAlignment(Pos.BOTTOM_RIGHT);

        tuottoOdotus.setEditable(false);      //Estetään kentän muokkaaminen
        laske.setOnAction(e -> LaskeTuottoOdotus());    //Tapahtuman käsittely

        Scene scene = new Scene(paneeli, 320, 240);
        stage.setTitle("LainaLaskuri");
        stage.setScene(scene);
        stage.show();
    }
    private void LaskeTuottoOdotus() {
        double SijoituksenMaaraluku = Double.parseDouble(sijoituksenMaara.getText());   //Luetaan arvot kentistä
        int VuosienMaaraluku = Integer.parseInt(vuosienMaara.getText());
        double VuosiKorkoluku = Double.parseDouble(vuosikorko.getText());

        Sijoitus sijoitus = new Sijoitus(SijoituksenMaaraluku, VuosienMaaraluku, VuosiKorkoluku);   //Luodaan olio
        tuottoOdotus.setText(String.format("%.2f", sijoitus.TuottoOdotus()));

    }

    public static void main(String[] args) {
        launch();
    }

    public class Sijoitus {
        private double sijoituksenMaara;
        private int vuosienMaara;
        private double vuosiKorko;

        public Sijoitus(double sijoituksenMaara, int vuosienMaara, double vuosiKorko) {
            this.sijoituksenMaara = sijoituksenMaara;
            this.vuosienMaara = vuosienMaara;
            this.vuosiKorko = vuosiKorko;
        }

        public double getSijoituksenMaara() {
            return this.sijoituksenMaara;
        }

        public void setSijoituksenMaara(double sijoituksenMaara) {
            this.sijoituksenMaara = sijoituksenMaara;
        }

        public double getVuosiKorko() {
            return this.vuosiKorko;
        }

        public void setVuosiKorko(double vuosiKorko) {
            this.vuosiKorko = vuosiKorko;
        }

        public int getVuosienMaara() {
            return this.vuosienMaara;
        }

        public void setVuosienMaara(int vuosienMaara) {
            this.vuosienMaara = vuosienMaara;
        }

        public double TuottoOdotus() {
            double a;
            a = (1+vuosiKorko/100);
            double tuottoOdotus = sijoituksenMaara * Math.pow(a, vuosienMaara);
            return tuottoOdotus;
        }
    }
}
