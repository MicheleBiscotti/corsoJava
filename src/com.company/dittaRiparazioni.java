package com.company;

public class dittaRiparazioni {

    Tecnico[] tecnici;
    Riparazioni[] riparazioni;
    int numeroEffettivoDiRiparazioni;
    int NumeroEffettivoDiTecnici;

    public dittaRiparazioni(Tecnico[] tecnici, Riparazioni[] riparazioni) {
        this.tecnici = tecnici;
        this.riparazioni = riparazioni;

        for(int i=0; i<riparazioni.length; i++) {
            if (riparazioni[i] != null) {
                numeroEffettivoDiRiparazioni++;
            }
        }

        for(int i=0; i<tecnici.length; i++) {
            if (tecnici[i] != null) {
                NumeroEffettivoDiTecnici++;
            }
        }
    }

    public void addRiparazione (Riparazioni riparazione) {
        if(numeroEffettivoDiRiparazioni < riparazioni.length) {
            riparazioni[numeroEffettivoDiRiparazioni] = riparazione;
            numeroEffettivoDiRiparazioni++;
        }
    }

    public void addTecnico (Tecnico tecnico) {
        boolean possibile = true;

        for (int i=0; i<NumeroEffettivoDiTecnici; i++) {
            if (tecnico.getNome().equals(tecnici[i].getNome()) && tecnico.getCognome().equals(tecnici[i].getCognome())) {
                System.out.println("Esiste già un tecnico con questo nome e cognome.");
                possibile=false;
                break;
            }
        }

        if (possibile && NumeroEffettivoDiTecnici < tecnici.length) {
            tecnici[NumeroEffettivoDiTecnici]=tecnico;
            NumeroEffettivoDiTecnici++;
        }
    }

    public void getRiparazioni () {
        for ( int i=0; i<numeroEffettivoDiRiparazioni; i++) {
            if (riparazioni[i].getState()==StatoRiparazione.wait) {
                System.out.println("riparazione in via: " + riparazioni[i].address +
                        "\n Con priorità: " + riparazioni[i].getPriority() +
                "\n in stato: " + riparazioni[i].getState());
            }
        }
    }

    public void getRiparazione (int numrip) {
        boolean assegnazione = false;
        for ( int i=0; i<numeroEffettivoDiRiparazioni; i++) {
            if (riparazioni[i].getNumrip()==numrip) {
                System.out.println("riparazione in via: " + riparazioni[i].address +
                        "\n Con priorità: " + riparazioni[i].getPriority() +
                        "\n in stato: " + riparazioni[i].getState());
                assegnazione=true;
                break;
            }
        }
        //Se non trova il numero di riparazione, segnala la mancanza di quest'ultimo
        if (!assegnazione) {
            System.out.println("Numero di riparazione non assegnato.");
        }

    }

    public void ordinareRiparazioni () {
        for (int i=0; i<numeroEffettivoDiRiparazioni-1; i++) {
            for (int j=i+1; j<numeroEffettivoDiRiparazioni; j++) {
                if (riparazioni[i].priority<riparazioni[j].priority) {
                    Riparazioni temp = new Riparazioni();
                    temp=riparazioni[i];
                    riparazioni[i]=riparazioni[j];
                    riparazioni[j]=temp;
                }
            }
        }
    }

    public void getMaxPriorityRip (){

        int i=0;
        Riparazioni temp = new Riparazioni();
        temp=riparazioni[i];

        for (i=0; i<numeroEffettivoDiRiparazioni-1; i++) {
            for (int j=i+1; j<numeroEffettivoDiRiparazioni; j++) {
                if (temp.priority<riparazioni[j].priority) {
                    temp=riparazioni[j];
                }
            }
        }

        System.out.println("La prossima riparazione con maggiore priorità è in: " + temp.address + ", in stato di: " + temp.state);
    }

    public void ripComplete (Riparazioni riparazione) {
        riparazione.setState(StatoRiparazione.done);
        riparazione.setPriority(0);
    }

    public static void main(String[] args) {
        Tecnico[] tecnici = new Tecnico[5];
        tecnici[0]=new Tecnico("Alfredo" , "Di Giorgio" , 537295);
        tecnici[1]=new Tecnico("Mario" , "Rossi" , 547862);
        tecnici[2]=new Tecnico("Dario" , "Bianchini" , 235689);
        tecnici[3]=new Tecnico("Vito" , "Verdi" , 255847);

        Riparazioni[] riparazioni=new Riparazioni[6];
        riparazioni[0]=new Riparazioni();
        riparazioni[0].setAddress("Via umberto");
        riparazioni[0].setPriority(5);
        riparazioni[1]=new Riparazioni();
        riparazioni[1].setAddress("Via napoli");
        riparazioni[2]=new Riparazioni();
        riparazioni[2].setAddress("Via rossi");
        riparazioni[3]=new Riparazioni();
        riparazioni[3].setAddress("Via petronio");
        riparazioni[3].setPriority(5);
        riparazioni[4]=new Riparazioni();
        riparazioni[4].setAddress("Via roberto");


        dittaRiparazioni ditta = new dittaRiparazioni(tecnici, riparazioni);

        Riparazioni newrip=new Riparazioni("via andrea da bari", 2, StatoRiparazione.wait, 62);
        ditta.addRiparazione(newrip);

        //due modi di fare la stessa cosa: uno riordina in base alla priorità e poi prende il primo elemento
        ditta.ordinareRiparazioni();
        System.out.println("Prossima riparazione da effettuare con maggior priorità è in: " + riparazioni[0].address +
                ", in stato di: " + riparazioni[0].state);

        //Questo scorre il vettore fino a che non trova l'elemento con maggior priorità
        ditta.getMaxPriorityRip();

        ditta.getRiparazioni();

        //Setta come completata la riparazione
        System.out.println();
        ditta.ripComplete(riparazioni[3]);
        System.out.println("riparazione: " + riparazioni[3].getAddress() + " " + riparazioni[3].getState() +
                " " + riparazioni[3].getPriority());
        ditta.getRiparazioni();


        //aggiunge un tecnico
        System.out.println();
        Tecnico newTec = new Tecnico("Michele", "Biscotti", 568093);
        ditta.addTecnico(newTec);
        ditta.getTecnici();

        //Manda in Ferie
        System.out.println();
        ditta.setFerie(tecnici[2]);
        ditta.getTecnici();

        //Manda in Ferie per Cognome
        System.out.println();
        String[] Cognomi = {"Biscotti", "Verdi"};
        ditta.setFerieperNome(Cognomi);
        ditta.getTecnici();

        //Assegnazione riparazione
        System.out.println();
        ditta.setAssegnazioneTecnico(62, tecnici[0]);
        System.out.println("Al tecnico " + tecnici[0].getNome() + " " + tecnici[0].getCognome() +
                ", Matricola " + tecnici[0].getMatricola() + ", è stato assegnato il numero di riparazione: "
        + tecnici[0].getNumRipTec());
        ditta.getRiparazione(62);

        //aggiunge un tecnico che esiste già --> dà errore!
        System.out.println();
        Tecnico newTec1 = new Tecnico("Mario", "Rossi", 568093);
        ditta.addTecnico(newTec1);
    }

    public void getTecnici () {
        for ( int i=0; i<NumeroEffettivoDiTecnici; i++) {
            System.out.println((i+1) +"° tecnico: " + tecnici[i].getNome() + " " + tecnici[i].getCognome() +
                    " " + tecnici[i].getMatricola() + " " + tecnici[i].getState() );
        }
    }

    public void setFerie (Tecnico tecnico) {
        tecnico.setState(Stato.ferie);
        System.out.println("Il tecnico " + tecnico.getNome() + " " + tecnico.getCognome() +
                ", Matricola " + tecnico.getMatricola() + ", è stato mandato in ferie!" );
    }

    public void setFerieperNome (String[] Cognomi) {
        for (int i=0; i<NumeroEffettivoDiTecnici; i++) {
            for (int j=0; j< Cognomi.length; j++) {
                if (tecnici[i].getCognome().equals(Cognomi[j])) {
                    tecnici[i].setState(Stato.ferie);
                    System.out.println("Il tecnico " + tecnici[i].getNome() + " " + tecnici[i].getCognome() +
                            ", Matricola " + tecnici[i].getMatricola() + ", è stato mandato in ferie!" );
                }
            }
        }
    }

    public void setAssegnazioneTecnico (int numrip, Tecnico tecnico) {
        boolean assegnazione=false;
        for (int i=0; i<numeroEffettivoDiRiparazioni; i++) {
            for (int j=0; j<NumeroEffettivoDiTecnici; j++) {
                if (riparazioni[i].numrip == numrip && tecnico.getState().equals(Stato.disponibile) && !assegnazione) {
                    tecnico.setNumRipTec(numrip);
                    assegnazione=true;
                    break;
                }
            }
            if (assegnazione) {
                break;
            }
        }
    }

}
