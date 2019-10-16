package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);

        int numeroDeContas= 10;
        int numeroDeComunidades= 10;

        String[][] conta= new String[numeroDeContas][6];//login, senha, apelído, sexo, idade, nacionalidade
        String[][] mensagens= new String[numeroDeContas][numeroDeContas];
        String[][] comunidade= new String[numeroDeComunidades][3];//O nome da comunidade, descrição e dono por comunidade criada
        String[][] comunidadeMembros= new String[numeroDeComunidades][numeroDeContas];//membros da comunidade
        int opcao;
        int[] num= new int[1];
        int[] numComunidade= new int[1];
        int[] numComunidadeMembros= new int[numeroDeComunidades];
        int[][] amizades= new int[numeroDeContas][numeroDeContas];
        int id;

        while(true){
            System.out.println("Selecione\n" +
                    "1 : Criação de Conta\n" +
                    "2 : Entrar\n" +
                    "0 : Sair");
            opcao= input.nextInt();
            switch(opcao){
                case 1://Criação de Conta
                    criarConta(conta, num, numeroDeContas);
                    break;
                case 2://login
                    id= entrar(conta, numeroDeContas);
                    if(id == -1){
                        break;
                    }else{
                        menu(conta, amizades, mensagens, comunidade, comunidadeMembros, num, numComunidade, numeroDeContas, numeroDeComunidades, numComunidadeMembros, id);
                    }
                    break;
                case 0://sair
                    break;
                default:
                    System.out.println("opção selecionada inexistente");
            }
            if(opcao == 0){
                System.out.println("Saindo");
                break;
            }
        }
    }

    private static void menu(String[][] conta, int[][] amizades, String[][] mensagens, String[][] comunidade, String[][] comunidadeMembros, int[] num, int[] numComunidade, int numeroDeContas, int numeroDeComunidades, int[] numComunidadeMembros, int id){
        Scanner input= new Scanner(System.in);

        int opcao;

        while(true){
            System.out.println("Selecione\n" +
                    "1 : Editar Perfil\n" +
                    "2 : Adicionar Amigo\n" +
                    "3 : Enviar Mensagem\n" +
                    "4 : Enviar Mensagem para Comunidade\n" +
                    "5 : Criar Comunidade\n" +
                    "6 : Adicionar Membros\n" +
                    "7 : Recuperar Informações Sobre um Determinado Usuário\n" +
                    "8 : Remover de Conta\n" +
                    "0 : sair");
            opcao= input.nextInt();
            switch(opcao){
                case 1://Edição de Perfil
                    editarPerfil(conta, numeroDeContas, id);//concluído
                    break;
                case 2://Adição de Amigos
                    adicionarAmigos(conta, numeroDeContas, amizades, id);//concluído
                    break;
                case 3://Envio de Mensagens
                    envioDeMensagens(conta, numeroDeContas, mensagens, id);
                    break;
                case 4://Enviar Mensagem para Comunidade

                    break;
                case 5://Criação de Comunidades
                    criarComunidades(conta, comunidade, comunidadeMembros, numComunidade, numeroDeComunidades, id);//concluído
                    break;
                case 6://Adição de Membros
                    adicionarMembrosDeComunidade(conta, comunidade, comunidadeMembros, numComunidadeMembros, numeroDeComunidades, numeroDeContas, id);
                    break;
                case 7://Recuperar Informações Sobre um Determinado Usuário
                    recuperarInformacoes(conta, mensagens, comunidade, comunidadeMembros, amizades, numeroDeComunidades, numeroDeContas, id);
                    break;
                case 8://Remoção de Conta
                    removerConta();
                    break;
                case 0://sair
                    break;
                default:
                    System.out.println("opção selecionada inexistente");
            }
            if(opcao == 0){
                System.out.println("Saindo");
                break;
            }
        }
    }

    private static int entrar(String[][] conta, int numeroDeContas){
        Scanner input= new Scanner(System.in);
        String login;
        String senha;
        boolean flag, opcao;
        int id;
        do {
            flag= false;
            System.out.println("digite seu login");
            login= input.next();
            System.out.println("digite sua senha");
            senha= input.next();

            for (id = 0; id < numeroDeContas; id++) {
                if (login.equals(conta[id][0]) && senha.equals(conta[id][1])) {
                    flag = true;
                    break;
                }
            }

            if(!flag){
                System.out.println("login ou senha incorreta");
                System.out.println("caso não possua conta ou deseja sair, digite true, senão false");
                opcao= input.nextBoolean();
                if (opcao) {
                    return -1;
                }
            }
        }while(!flag);
        return id;
    }

    private static void criarConta(String[][] conta, int[] num, int numeroDeContas){
        Scanner input= new Scanner(System.in);

        boolean flag;
        while(conta[num[0]][0] != null && num[0] < numeroDeContas){

            if(num[0] == numeroDeContas-1){
                System.out.println("número de contas excedida");
                return;
            }
            num[0]++;
        }
        System.out.println("digite seu login");
        conta[num[0]][0]= input.next();
        do {
            flag= true;
            for (int i = 0; i < numeroDeContas; i++) {
                if (conta[num[0]][0].equals(conta[i][0])) {
                    if(i != num[0]) {
                        flag = false;
                        System.out.println("login já está em uso");
                        conta[num[0]][0] = input.next();
                        break;
                    }
                }
            }
        }while(!flag);
        System.out.println("digite sua senha");
        conta[num[0]][1]= input.next();
        System.out.println("digite seu apelído");
        conta[num[0]][2]= input.next();
        do {
            flag= true;
            for (int j = 0; j < numeroDeContas; j++) {
                if (conta[num[0]][2].equals(conta[j][2])) {
                    if(j != num[0]) {
                        flag = false;
                        System.out.println("apelído já está em uso");
                        conta[num[0]][2] = input.next();
                        break;
                    }
                }
            }
        }while(!flag);

    }

    private static void editarPerfil(String[][] conta, int numeroDeContas, int id){
        Scanner input= new Scanner(System.in);

        boolean flag;

        System.out.println("coloque seu novo login");
        conta[id][0]= input.next();
        do {
            flag= true;
            for (int j = 0; j < numeroDeContas; j++) {
                if (conta[id][0].equals(conta[j][0])) {
                    if(j != id) {
                        flag = false;
                        System.out.println("login já está em uso");
                        conta[id][0] = input.next();
                        break;
                    }
                }
            }
        }while(!flag);

        System.out.println("coloque sua nova senha");
        conta[id][1]= input.next();
        System.out.println("coloque seu novo apelído");
        conta[id][2]= input.next();
        do {
            flag= true;
            for (int j = 0; j < numeroDeContas; j++) {
                if (conta[id][2].equals(conta[j][2])) {
                    if(j != id) {
                        flag = false;
                        System.out.println("apelído já está em uso");
                        conta[id][2] = input.next();
                        break;
                    }
                }
            }
        }while(!flag);

        System.out.println("coloque seu sexo");
        conta[id][3]= input.next();
        System.out.println("coloque sua idade");
        conta[id][4]= input.next();
        System.out.println("coloque sua nacionalidade");
        conta[id][5]= input.next();

    }

    private static void adicionarAmigos(String[][] conta, int numeroDeContas, int[][] amizades, int id){
        Scanner input= new Scanner(System.in);

        String apelido;
        boolean flag, opcao;

        System.out.println("digite o apelído do usúario que deseja adicionar");
        apelido= input.next();
        do {
            flag= true;
            for (int j = 0; j < numeroDeContas; j++) {
                if (apelido.equals(conta[j][2])) {
                    if(id == j){
                        System.out.println("você não pode se adicionar como amigo");
                        break;
                    }else{
                        flag = false;
                    }
                    if(amizades[id][j] == 1 && amizades[j][id] == 1){
                        System.out.println("você já é amigo de "+ apelido);
                    }else if(amizades[id][j] == 0 && amizades[j][id] == 1){
                        amizades[id][j]= 1;
                        System.out.println("você acaba de aceitar o pedido de amizade do "+ apelido);
                    }else if(amizades[id][j] == 1 && amizades[j][id] == 0){
                        System.out.println("você já solicitou o pedido de amizade, aguarde até que ele seja aceito");
                    }else if(amizades[id][j] == 0 && amizades[j][id] == 0){
                        amizades[id][j]= 1;
                        System.out.println("você acaba de fazer uma solicitação de amizade, aguarde até que ele seja aceito");
                    }
                    break;
                }
            }
            if(flag){
                System.out.println("digite um apelído válido");
                //apelido= input.next();
                System.out.println("caso deseja sair, digite true, senão false");
                opcao= input.nextBoolean();

                if (opcao) {
                    return;
                }

                System.out.println("digite novamente o apelído de quem vc deseja adicionar");

                apelido= input.next();
            }
        }while(flag);

    }
    /*
    uma mensagem pode ser enviada de uma conta "i" para uma conta "j".
    */
    private static void envioDeMensagens(String[][] conta, int numeroDeContas, String[][] mensagens, int id){
        Scanner input= new Scanner(System.in);
        Scanner input2= new Scanner(System.in);

        String apelido;
        boolean flag, opcao;

        System.out.println("digite o apelído do usúario que deseja enviar mensagem");
        apelido= input.next();
        do {
            flag= true;
            for (int j = 0; j < numeroDeContas; j++) {
                if (apelido.equals(conta[j][2])) {
                    if(id == j){
                        System.out.println("você não pode enviar mensagem para sí mesmo");
                        break;
                    }else{
                        flag = false;
                    }
                    if(mensagens[id][j] == null){
                        System.out.println("digite a mensagem que deseja enviar para "+ apelido);
                        mensagens[id][j]= input2.nextLine();
                    }else{
                        System.out.println("você já enviou uma mensagem para "+ apelido + " \"" + mensagens[id][j] + "\", deseja sobrescreve-la?");
                        System.out.println("digite true para sobrescrever e false para não");
                        opcao= input.nextBoolean();
                        if(opcao){
                            System.out.println("digite a mensagem que deseja enviar para "+ apelido);
                            mensagens[id][j]= input2.nextLine();
                        }else{
                            System.out.println("A mensagem permanece a mesma");
                            return;
                        }
                    }
                    break;
                }
            }
            if(flag){
                System.out.println("digite um apelído válido");
                //apelido= input.next();
                System.out.println("caso deseja sair, digite true, senão false");
                opcao= input.nextBoolean();
                if (opcao) {
                    return;
                }
                System.out.println("digite novamente o apelído");
                apelido= input.next();
            }
        }while(flag);
    }

    private static void criarComunidades(String[][] conta, String[][] comunidade, String[][] comunidadeMembros, int[] numComunidade, int numeroDeComunidades, int id){
        Scanner input= new Scanner(System.in);
        Scanner input2= new Scanner(System.in);

        boolean flag;
        while(comunidade[numComunidade[0]][0] != null && numComunidade[0] < numeroDeComunidades){

            if(numComunidade[0] == numeroDeComunidades-1){
                System.out.println("número de comunidades excedida");
                return;
            }
            numComunidade[0]++;
        }

        System.out.println("digite o nome da comunidade");
        comunidade[numComunidade[0]][0]= input.next();
        do {
            flag= true;
            for (int i = 0; i < numeroDeComunidades; i++) {
                if (comunidade[numComunidade[0]][0].equals(comunidade[i][0])) {
                    if(i != numComunidade[0]) {
                        flag = false;
                        System.out.println("esse nome já está em uso");
                        comunidade[numComunidade[0]][0] = input.next();
                        break;
                    }
                }
            }
        }while(!flag);

        System.out.println("coloque a descrição da comunidade");
        comunidade[numComunidade[0]][1]= input2.nextLine();
        comunidade[numComunidade[0]][2]= conta[id][2];

        comunidadeMembros[numComunidade[0]][0]= conta[id][2];

    }

    private static void adicionarMembrosDeComunidade(String[][] conta, String[][] comunidade, String[][] comunidadeMembros, int[] numComunidadeMembros, int numeroDeComunidades, int numeroDeContas, int id){
        Scanner input= new Scanner(System.in);

        String nome, apelido;
        boolean flag= true, opcao;
        int i;

        System.out.println("lista de comunidades que você é dono :");
        for(i = 0; i < numeroDeComunidades; i++){
            if(conta[id][2].equals(comunidade[i][2])){
                System.out.println(comunidade[i][0]);
            }
        }

        do {
            System.out.println("digite o nome da sua comunidade");
            nome = input.next();
            for(i = 0; i < numeroDeComunidades; i++){
                if(nome.equals(comunidade[i][0]) && conta[id][2].equals(comunidade[i][2])){
                    flag= false;
                    break;
                }
            }
            if(flag){
                System.out.println("digite um nome válido");

                System.out.println("caso deseja sair, digite true, senão false");
                opcao= input.nextBoolean();

                if (opcao) {
                    return;
                }
            }
        }while(flag);

        while(comunidade[numComunidadeMembros[i]][0] != null && numComunidadeMembros[i] < numeroDeComunidades){

            if(numComunidadeMembros[i] == numeroDeComunidades-1){
                System.out.println("número de membros da comunidade excedida");
                return;
            }
            numComunidadeMembros[i]++;
        }

        System.out.println("digite o apelído de quem deseja adicionar à comunidade");
        apelido= input.next();
        do {
            flag= true;
            for (int j = 0; j < numeroDeContas; j++) {
                if (apelido.equals(conta[j][2])) {
                    if(id == j){
                        System.out.println("você já é membro");
                        break;
                    }else{
                        flag = false;
                    }
                    break;
                }
            }
            if(flag){
                System.out.println("digite um apelído válido");
                //apelido= input.next();
                System.out.println("caso deseja sair, digite true, senão false");
                opcao= input.nextBoolean();
                if (opcao) {
                    return;
                }
                System.out.println("digite novamente o apelído de quem vc deseja adicionar");
                apelido= input.next();
            }
        }while(flag);

        comunidadeMembros[i][numComunidadeMembros[i]]= apelido;

    }

    private static void recuperarInformacoes(String[][] conta, String[][] mensagens, String[][] comunidade, String[][] comunidadeMembros, int[][] amizades, int numeroDeComunidades, int numeroDeContas,int id){

        //perfil
        System.out.println("perfil :");
        System.out.println("login : " + conta[id][0] + "\nsenha : " + conta[id][1] + "\napelído : " + conta[id][2] + "\nsexo : " + conta[id][3] + "\nidade : " + conta[id][4] + "\nnacionalidade : " + conta[id][5] + "\n");
        //comunidades
        System.out.println("comunidades que o usuário possui :");
        for(int i = 0; i < numeroDeComunidades; i++){
            if(conta[id][2].equals(comunidade[i][2])){
                System.out.println("nome : " + comunidade[i][0] + "\ndescrição : " + comunidade[i][1]);
                System.out.println("membros :");
                for(int j = 0; j < numeroDeContas; j++){
                    if(comunidadeMembros[i][j] != null) {
                        System.out.println(comunidadeMembros[i][j]);
                    }
                }
            }
        }
        System.out.println();
        //amigos
        System.out.println("amizades :");
        for(int i = 0; i < numeroDeContas; i++){
            if(amizades[id][i] == 1 && amizades[i][id] == 1){
                System.out.println(conta[i][2]);
            }
        }
        System.out.println();
        //mensagens//dos usuários e das comunidades que é dono
        System.out.println("mensagens recebidas :");
        for(int j = 0; j < numeroDeContas; j++){
            if(mensagens[j][id] != null){
                System.out.println(conta[j][2] + " : " + mensagens[j][id]);
            }
        }
    }

    private static void removerConta(){

    }

}
//falta mensagens de recuperarinformações**, falta removerConta***, arrumar adicionarMembrosDeComunidade//colocar para enviar solicitação*, falta aumentar a quantidade de mensagens*