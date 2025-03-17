package UI;

import Domain.Tort;
import Exceptions.RepoException;
import Services.ServiceComanda;
import Services.ServiceTort;


import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UInterface {
    ServiceComanda sc;
    ServiceTort st;

    public UInterface(ServiceComanda sc, ServiceTort st) {
        this.sc = sc;
        this.st = st;
    }

    public void GeneralMenu() {
        System.out.println("""
                1.Adaugare tort
                2.Citire torturi
                3.Actualizare tort
                4.Stergere tort
                5.Adaugare comanda
                6.Citire comenzi
                7.Actualizare comanda
                8.Stegere comanda
                9.Iesire
                10.NrTorturiPeZi
                11.NrTorturiPeLuna
                12.CeleMaiComandateTorturi""");
    }

    public void UpdateComanda() {
        System.out.println("1.Actualizare lista de torturi\n" +
                "2.Actualizare data\n" +
                "3.Iesire");
    }

    public void run() {
        while (true) {
            try {
                GeneralMenu();
                System.out.println("Alege optiunea:");
                Scanner scan = new Scanner(System.in);
                int option = scan.nextInt();

                if (option == 1) {
                    try {
                        System.out.println("Tipul tortului:");
                        String CakeType = scan.next();
                        Tort t = new Tort(CakeType);
                        st.ServiceAddTort(t);
                    } catch (IOException e) {
                        System.out.println("Eroare!");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }

                if (option == 2) {
                    System.out.println(st.getRepoTort());
                }
                if (option == 3) {
                    try {
                        System.out.println("Alegeti ID-ul tortului:");
                        int id = scan.nextInt();
                        System.out.println("Alegeti cu ce tip sa fie tortul actualizat:");
                        String CakeType = scan.next();
                        st.ServiceUpdateTort(id, CakeType);
                    } catch (RepoException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                if (option == 4) {
                    try {
                        System.out.println("Alegeti ID-ul tortului:");
                        int id = scan.nextInt();
                        st.ServiceDeleteTort(id);
                    } catch (RepoException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (option == 5) {
                    try {
                        System.out.println("Alegeti nr de torturi:");
                        int nr = scan.nextInt();
                        List<String> types = new ArrayList<>();
                        int k = 0;
                        while (k != nr) {
                            System.out.println("Alegeti tipul tortului:");
                            String CakeType = scan.next();
                            types.add(CakeType);
                            k++;
                        }
                        System.out.println("Alegeti luna:");
                        int luna = scan.nextInt();
                        System.out.println("Alegeti ziua:");
                        int ziua = scan.nextInt();
                        sc.ServiceAddComanda(nr, types, luna, ziua);
                    } catch (IOException | RepoException e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (option == 6) {
                    System.out.println(sc.getRepoComanda());
                }
                if (option == 7) {

                    while (true) {
                        UpdateComanda();
                        System.out.println("Alegeti ce vreti sa actualizati:");
                        int option2 = scan.nextInt();
                        if (option2 == 1) {
                            try {
                                System.out.println("Alegeti ID-ul comenzii:");
                                int id = scan.nextInt();
                                System.out.println("Alegeti nr de torturi:");
                                List<String> types = new ArrayList<>();
                                int nr = scan.nextInt();
                                int k = 0;
                                while (k != nr) {
                                    System.out.println("Alegeti tipul tortului:");
                                    String CakeType = scan.next();
                                    types.add(CakeType);
                                    k++;

                                }
                                sc.ServiceUpdateComandaTorturi(id, nr, types);
                            } catch (RepoException | IOException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        }
                        if (option2 == 2) {
                            try {
                                System.out.println("Alegeti ID-ul comenzii:");
                                int id = scan.nextInt();
                                System.out.println("Alegeti luna:");
                                int luna = scan.nextInt();
                                System.out.println("Alegeti ziua:");
                                int ziua = scan.nextInt();
                                sc.ServiceUpdateComandaData(id, luna, ziua);
                            } catch (RepoException e) {
                                System.out.println(e.getMessage());
                            }

                        }
                        if (option2 == 3)
                            break;

                    }
                }
                if (option == 8) {
                    try {
                        System.out.println("Alegeti ID-ul comenzii:");
                        int id = scan.nextInt();
                        sc.ServiceRemoveComanda(id);
                    } catch (RepoException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);

                    }
                }

                if (option == 9)
                    break;

                if (option == 10) {
                    System.out.println(sc.NrTorturiPeZi());
                }
                if (option == 11) {
                    System.out.println(sc.NrTorturiPeLuna());
                }
                if (option == 12) {
                    System.out.println(sc.CeleMaiDesComandateTorturi());
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid option!");
            }
        }

    }
}
