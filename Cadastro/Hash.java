package Cadastro;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hash {

    private Pessoa[][] list;//Variavel de vetor de vetor de objetos do tipo Pessoa

    public Hash(Pessoa[][] lista){
        //Criando e colocando os valores na classe
        this.list=lista;
    }
    //Get do vetor de vetor de Pessoa da Classe
    public Pessoa[][] getLista(){
        return this.list;
    }

    //Criação da tabela Hash
    public Hash criaHash(int tam1, int tam2){

        Pessoa[][] lista = new Pessoa[tam1][tam2];//Tamanho do vetor, quantidade de linhas

        for(int i = 0;i<tam1;i++) {//For para criar os objetos e colocar os valores, onde tamanho1 é o resultado da quantidade de números aleatorios possiveis dividido por 100
            Pessoa[] valores = new Pessoa[tam2];//Tamanho do vetor daquela posição do vetor principal, ou seja, quantidade de colunas

            for(int y =0; y < valores.length;y++){//For para gerar valores vazios que será igual a tam2, que é o valor em que o random foi dividido, na maioria dos casos 100
                Pessoa aux = new Pessoa("","",0,-1);//Criar uma variavel pessoa vazia
                valores[y] = aux;//Adicionar esse cadastro vazio dentro do vetor
            }

            lista[i]=valores;//Adicionar este vetor cheio de cadastros vazios no vetor principal
        }

        Hash h = new Hash(lista);//Criar um objeto Hash, ou seja, a tabela
        return h;//Retornar a tabela criada
    }
    //Set do vetor de vetor de Pessoa da Classe
    public void setLista(Pessoa[][] lista){
        this.list=lista;
    }

    //Imprimir somente os IDs de acordo com suas posições
    public void PrintID(int tamanho2){

        for (int i = 0; i < this.getLista().length; i++) {//For que percorre as linhas
            if (this.getLista()[i][0].getID() > -1) {//Condição para entrar nesta linha somente se o primeiro valor não for nulo
                System.out.println("\n" + ((i + 1) * tamanho2));//Imprimir o teto desta posição, por exemplo se o teto for 100 ira do 0 até o 99, se for o 300 irá do 200 até 299.
                System.out.print("( ");
                for (int y = 0; y < this.getLista()[i].length; y++) {//For que percorre as colunas da linha em que se encontra
                    if (this.getLista()[i][y].getID() > -1)//Condição para imprimir somente se o valor não for vazio
                        System.out.print(this.getLista()[i][y].getID() + " ");
                    else//Existindo um cadastro vazio, é interrompido o percorrimento das colunas desta linha
                        break;
                }
                System.out.print(")\n");
            }
        }
    }
    //Imprimir somente os valores do cadastro
    public void Print(int tamanho2){

        for (int i = 0; i < this.getLista().length; i++) {//For que percorre as linhas
            if (this.getLista()[i][0].getID() > -1) {//Condição para entrar nesta linha somente se o primeiro valor não for nulo
                for (int y = 0; y < this.getLista()[i].length; y++) {//For que percorre as colunas da linha em que se encontra
                    if (this.getLista()[i][y].getID() > -1)//Condição para imprimir somente se o valor não for vazio
                        System.out.println("\nNome: " + this.getLista()[i][y].getNome() + "\nE-mail: " + this.getLista()[i][y].getEmail() + "\nIdade: " + this.getLista()[i][y].getIdade() + " anos\nID: " + this.getLista()[i][y].getID());
                    else//Existindo um cadastro vazio, é interrompido o percorrimento das colunas desta linha
                        break;
                }
            }
        }
    }

    public Hash GerarID(int rand, Pessoa pp, int tam2, Hash Tabela){//Método para gerar um ID aleatorio que ainda não foi cadastrado
        Random gerador =new Random();//Gerador de numeros aleatorios
        boolean resp=false;//Resposta se o valor foi ou não utilizado
        int cont = -1;//Contador de procuras feitas para
        do {
            cont++;//Contador é incrementado em +1
            int ID;//Variavel para armazenar o ID
            ID=gerador.nextInt(rand);//Gerando ID com o valor aleatorio definido no Menu
            int p=ID/tam2;//Posição da linha em que este será adicionado

            if(p>=Tabela.getLista().length)//A posição sendo maior que a quantidade de linhas é diminuido 1 do valor
                p=p-1;

            for (int y = 0; y < Tabela.getLista()[p].length; y++) {//For para percorrer a linha escolhida
                if(cont == (10*(Tabela.getLista()[0].length * Tabela.getLista().length))){//O contador valendo 10 vezes a quantidade de números possiveis a procura por uma posição para o cadastro é cancelada pois não existe mais posições disponiveis
                    System.out.println("Todos os IDS foram cadastrados!");
                    return Tabela;
                }

                if (ID == Tabela.getLista()[p][y].getID()) {//Existindo um valor igual ao ID gerado é feito um novo ID e recomeça
                    resp = false;
                    break;
                }

                if (Tabela.getLista()[p][y].getID() <0) {//A posição tendo um ID menor que 0, ou seja, um cadastro vazio, o cadastro é adicionado nessa posição
                    pp.setID(ID);//Define o ID do cadastro como o ID gerado
                    Tabela.getLista()[p][y]=pp;//Adiciona ta tabela o cadastro
                    resp= true;//ID foi utilizado
                    System.out.println("\nID: "+ID);//Imprime o ID que foi utilizado
                    break;
                }
            }
        }while (resp==false);//Manter o do até a resposta ser diferente de falsa, ou seja, ate ser encontrado uma posição para o cadastro
        return Tabela;
    }

    public Hash Adicionar(Pessoa pp, int tam2, Hash Tabela){//Função utilizada para adicionar diretamente na posição na tabela quando é feito a leitura do arquivo

        int p=pp.getID()/tam2;//Posição da linha em que este será adicionado, sendo a quantidade que existe de valores por linha, ou seja, a quantidade de colunas

        if(p>=Tabela.getLista().length)//A posição sendo maior que a quantidade de linhas é diminuido 1 do valor
            p=p-1;

        for (int y = 0; y < Tabela.getLista()[p].length; y++) {//Procura uma posição nas colunas que esteja livre para adicionar o cadastro
            if (Tabela.getLista()[p][y].getID() <0) {//A posição sendo menor que 0 o cadastrado é colocado nesta posição
                Tabela.getLista()[p][y] = pp;
                Tabela.getLista()[p][y].setID(pp.getID());
                break;
            }
        }
        return Tabela;
    }



    public int BuscaID(Hash Tabela, int tam2) {//Metodo para buscar um cadastro pelo ID

        System.out.println("\nDigite o ID que seja buscar:");
        Scanner ler = new Scanner(System.in);
        int ID = ler.nextInt();//O ID digitado pelo usuário é lido e é calculado a posição

        int p = ID / tam2;//Posição da linha em que o ID se encontra
        if (p >= Tabela.getLista().length)//A posição sendo maior que a quantidade de linhas é diminuido 1 do valor
            p = p - 1;

        for (int y = 0; y < Tabela.getLista()[p].length; y++) {//For para encontrar a coluna em que o cadastro se localiza na linha
            if (ID == Tabela.getLista()[p][y].getID()) {//O cadastro sendo encontra é mostrado os dados na tela e é retornado o valor do ID
                System.out.println("\nNome: " + Tabela.getLista()[p][y].getNome() + "\nE-mail: " + Tabela.getLista()[p][y].getEmail() + "\nIdade: " + Tabela.getLista()[p][y].getIdade() + " anos\nID: " + Tabela.getLista()[p][y].getID());
                return Tabela.getLista()[p][y].getID();
            }
        }

        System.out.println("O cadastro não foi encontrado!");//Cadastro não sendo encontrado é retornado esta mensagem e -1
        return -1;
    }

    public int BuscaString(Hash Tabela, int tam2, boolean t) {//Metodo para buscar o cadastro por nome ou por e-mail

        if(t==true){//T sendo igual a true o tipo de busca é pelo nome do cadastro

            System.out.println("\nDigite o nome que seja buscar:");
            Scanner ler = new Scanner(System.in);
            String Nome = ler.nextLine();//O nome do cadastro digitado pelo usuário é lido

            for(int i = 0; i<Tabela.getLista().length;i++){//For para percorrer as linhas da tabela Hash
                if(Tabela.getLista()[i][0].getID()>-1) {//Condição para entrar nesta linha somente se o primeiro valor não for nulo
                    for (int y = 0; y < Tabela.getLista()[i].length; y++) {//For que percorre as colunas da linha em que se encontra
                        if(Tabela.getLista()[i][y].getID()<0)//Existindo um cadastro vazio, é interrompido o percorrimento das colunas desta linha
                            break;
                        if (Nome.equals((Tabela.getLista()[i][y].getNome())) == true) {//O nome sendo igual no cadastro atual é mostrado e perguntado se era este a pessoa que o usuário buscava
                            System.out.println("\nNome: " + Tabela.getLista()[i][y].getNome() + "\nE-mail: " + Tabela.getLista()[i][y].getEmail() + "\nIdade: " + Tabela.getLista()[i][y].getIdade() + " anos\nID: " + Tabela.getLista()[i][y].getID());
                            System.out.println("Essa era a pessoa desejada? 1 - Sim ou 2 - Nao");
                            int op = ler.nextInt();//Ele respondendo que era a pessoa a busca termina e retorna o ID, respondendo não a busca continua

                            if (op == 1)
                                return Tabela.getLista()[i][y].getID();//Retorna o ID deste cadastro encontrado
                        }
                    }
                }
            }
        }

        if(t==false){//t sendo falso será uma busca por e-mail, sendo a mesma coisa que a busca por nome somente mudando que agora o campo é o e-mail
            System.out.println("\nDigite o email que seja buscar:");
            Scanner ler = new Scanner(System.in);
            String Email = ler.nextLine();

            for(int i = 0; i<Tabela.getLista().length;i++){
                if(Tabela.getLista()[i][0].getID()>-1) {
                    for (int y = 0; y < Tabela.getLista()[i].length; y++) {
                        if (Tabela.getLista()[i][y].getID() < 0)
                            break;
                        if (Email.equals(Tabela.getLista()[i][y].getEmail()) == true) {
                            System.out.println("\nNome: " + Tabela.getLista()[i][y].getNome() + "\nE-mail: " + Tabela.getLista()[i][y].getEmail() + "\nIdade: " + Tabela.getLista()[i][y].getIdade() + " anos\nID: " + Tabela.getLista()[i][y].getID());
                            System.out.println("Essa era a pessoa desejada? 1 - Sim ou 2 - Nao");
                            int op = ler.nextInt();

                            if (op == 1)
                                return Tabela.getLista()[i][y].getID();
                        }
                    }
                }
            }
        }

        System.out.println("O cadastro não foi encontrado!");//Não sendo encontrado o cadastro é mostrado essa mensagem e retorna -1
        return -1;
    }

    public int BuscaIdade(Hash Tabela, int tam2) {//Método para buscar o cadastro por meio da idade da pessoa
        //Mesma busca que a por nome e por e-mail, só mudando que agora é uma busca por um valor inteiro e não por uma String
        System.out.println("\nDigite a idade que seja buscar:");
        Scanner ler = new Scanner(System.in);
        int idade = ler.nextInt();

        for(int i = 0; i<Tabela.getLista().length;i++){
            if(Tabela.getLista()[i][0].getID()>-1) {
                for (int y = 0; y < Tabela.getLista()[i].length; y++) {
                    if (Tabela.getLista()[i][y].getID() < 0)
                        break;
                    if (idade == Tabela.getLista()[i][y].getIdade()) {
                        System.out.println("\nNome: " + Tabela.getLista()[i][y].getNome() + "\nE-mail: " + Tabela.getLista()[i][y].getEmail() + "\nIdade: " + Tabela.getLista()[i][y].getIdade() + " anos\nID: " + Tabela.getLista()[i][y].getID());
                        System.out.println("Essa era a pessoa desejada? 1 - Sim ou 2 - Nao");
                        int op = ler.nextInt();

                        if (op == 1)
                            return Tabela.getLista()[i][y].getID();
                    }
                }
            }
        }

        System.out.println("O cadastro não foi encontrado!");
        return -1;
    }


    public Hash Excluir(Hash Tabela, int ID, int tam2){//Metodo para excluir um valor da tabela
        //É calculado a linha que o cadastro se encontra por meio do ID
        int p=ID/tam2;//Posição da linha em que ele se encontra

        if(p>=Tabela.getLista().length)//A posição sendo maior que a quantidade de linhas é diminuido 1 do valor
            p=p-1;

        for (int y = 0; y < Tabela.getLista()[p].length; y++) {//For para percorrer os valores daquela linha

            if (ID == Tabela.getLista()[p][y].getID()) {//Encontrando o cadastro que possui este ID
                Pessoa aux = new Pessoa("","",0,-1);//É criado um objeto vazio
                Tabela.getLista()[p][y] = aux;//A posição recebe esta posição vazia, assim ela pode ser ocupada por um novo cadastro
                System.out.println("\nO cadastro foi excluido!");
                return Tabela;//A tabela sem o cadastro é retornada
            }
        }

        System.out.println("\nO cadastro não foi encontrado!");//Não sendo encontrado o cadastro tabela é retornada sem mudança e é mostrada essa mensagem
        return Tabela;
    }

    //Metodo para adicionar ao arquivo Cadastro.txt os cadastros feitos
    public void adicionarArquivo(File arquivo, Hash tabela, int random, FileWriter fw, BufferedWriter bw){

        try{
            //A sequencia de dados será: Quantidade possivel de cadastros, ID, Nome, E-Mail e Idade. A quantidade possivel de cadastros será escrita só uma vez, e será na primeira linha do arquivo
            String idd;//String que ira receber os valores inteiros transformados em String
            idd=Integer.toString(random);//Transformar o valor de número de IDs possiveis em String para ser adicionado ao arquivo
            bw.write(idd);//idd, quantidade de possivel de ID, é adicionado ao arquivo
            bw.newLine();//Pula para a proxima linha

            for(int i = 0; i < tabela.getLista().length;i++) {//For para percorrer as linhas da tabela
                if (tabela.getLista()[i][0].getID() > -1) {//Entra na linha somente se o primeiro cadastro não for vazio
                    for (int y = 0; y < tabela.getLista()[i].length; y++) {//For para percorrer os valores dentro da linha, ou seja, percorrer as colunas
                        if (tabela.getLista()[i][y].getID() > -1) {//Somente é escrito no arquivo se não for um cadastro vazio
                            idd = Integer.toString(tabela.getLista()[i][y].getID());//O ID é transformado em String para ser adicionado no arquivo
                            bw.write(idd);//idd, ID do cadastro, é adicionado ao arquivo
                            bw.newLine();//Pula para a proxima linha

                            bw.write(tabela.getLista()[i][y].getNome());//O nome do cadastro é adicionado ao arquivo
                            bw.newLine();//Pula para a proxima linha

                            bw.write(tabela.getLista()[i][y].getEmail());//O e-mail do cadastro adicionado ao arquivo
                            bw.newLine();//Pula para a proxima linha

                            idd = Integer.toString(tabela.getLista()[i][y].getIdade());//A idade é transformada em String para ser adicionado no arquivo

                            bw.write(idd);//idd, idade do cadastro, é adicionado ao arquivo
                            bw.newLine();//Pula para a proxima linha
                        }
                    }
                }
            }
        }

        catch(IOException e){//Erro na escrita no arquivo
            System.out.println("Erro: "+e.toString());
        }
    }

    //Metodo para ler o Arquivo "Cadastro.txt"
    public Hash lerArquivo(File arquivo, Hash tabela){

        try{
            //Cria o FileReader e o BufferedReader para a leitura do arquivo
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            //Criando uma ArrayList String para pegar todas as linhas do arquivo
            ArrayList<String> S = new ArrayList<String>();

            //Vai repetir até que não tenha mais linhas no arquivo
            while (br.ready()){
                //Le a linha e adiciona na Lista
                String linha = br.readLine();
                S.add(linha);
            }

            //O primeiro valor da lista sera a quantidade de IDs possiveis, ou seja , o valor do random
            int random=Integer.parseInt(S.get(0));//A string é transformada em inteiro para o random receber o valor

            int tamanho2 = 100;//Quantidade de colunas. Tamanho2 sempre sera 100, a menos que tamanho1 seja 100 ou menor.
            int tamanho1 = (random - 1) / tamanho2;//Quantidade de linhas

            if (tamanho1 <= 1) {//O valor de random sendo 100 ou menor, entra nessa condição para o tamanho da tabela ser mais condizente
                if (tamanho1 == 1)//Random sendo igual a 100
                    tamanho2 = tamanho1 = 10;

                else {//Random sendo igual a 10 ou 1
                    tamanho2 = 10;
                    tamanho1 = 1;
                }
            }

            tabela = tabela.criaHash(tamanho1, tamanho2);//Criando a tabela Hash com as dimensões corretas

            //Vai repetir até que z seja do mesmo tamanho da Lista
            for(int z=1;z<S.size();z++){//For começa do 1, pois a primeira posição ja foi lida

                //Cria um objeto Pessoa vazio para receber os valores lidos do arquivo
                Pessoa P = new Pessoa("","", 0, -1);

                int valor=Integer.parseInt(S.get(z));//A posição é transformada para inteiro para a variavel receber o valor do ID

                P.setID(valor);//O ID do cadastro é definido
                z++;//Passa para a proxima posição da lista

                P.setNome(S.get(z));//O nome do cadastro é definido
                z++;//Passa para a proxima posição da lista

                P.setEmail(S.get(z));//O e-mail do cadastro é definido
                z++;//Passa para a proxima posição da lista

                valor=Integer.parseInt(S.get(z));//A posição é transformada para inteiro para a variavel receber o valor da idade

                P.setIdade(valor);//A idade do cadastro é definido

                tabela = tabela.Adicionar(P,tamanho2,tabela);//É adicionado na tabela o cadastro
            }

            //Depois de todos os cadastros terem sido colocadas na tabela o FileReader e o BufferedReader são fechados
            br.close();
            fr.close();
        }


        catch(IOException e){//Erro na leitura do arquvio
            System.out.println("Erro: "+e.toString());
        }

        //O metodo depois de tudo retorna a tabela hash para o menu
        return tabela;
    }

}

