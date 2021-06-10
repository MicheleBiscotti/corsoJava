package com.company;

public class Tecnico {

    public String nome;
    public String cognome;
    public int Matricola;
    public Stato state;
    public int numRipTec;

    public int getNumRipTec() {
        return numRipTec;
    }

    public void setNumRipTec(int numRipTec) {
        this.numRipTec = numRipTec;
    }

    public Stato getState() {
        return state;
    }

    public void setState(Stato state) {
        this.state = state;
    }

    public Tecnico(String nome, String cognome, int matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.Matricola = matricola;
        this.state=Stato.disponibile;
    }

    public Tecnico(String nome, String cognome, int matricola, Stato state) {
        this.nome = nome;
        this.cognome = cognome;
        this.Matricola = matricola;
        this.state=state;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getMatricola() {
        return Matricola;
    }

    public void setMatricola(int matricola) {
        Matricola = matricola;
    }
}

enum Stato {
    disponibile, assegnato, ferie;
}
