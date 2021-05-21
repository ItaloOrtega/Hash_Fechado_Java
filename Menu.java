import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Cadastro.*;

public class Menu {

    public static void main(String[] args) {

        try {
            File arquivo = new File("Cadastro.txt");//Criação do arquivo Cadastro.txt que vai ser guardado as informações
            Hash tabela = new Hash(null);//Criação do objeto HASH
            int random = 1;//Quantidade total de IDs, ou seja, cadastros possiveis, sempre sera um multiplo de 10 somado com 1;
            for(int i = 0; i< 4;i++){
                random = random * 10;
            }
            random = random+1;
            int tamanho2 = 100;//Tamanho do vetor da posição, ou seja, quantidade de colunas da tabela
            int tamanho1 = (random - 1) / tamanho2;//Tamanho do vetor, ou seja, quantidade de linhas da tabela

            if (tamanho1 <= 1) {//O valor de random sendo 100 ou menor, entra nessa condição para o tamanho da tabela ser mais condizente
                if (tamanho1 == 1)//Random sendo igual a 100
                    tamanho2 = tamanho1 = 10;

                else {//Random sendo igual a 10 ou 1
                    tamanho2 = 10;
                    tamanho1 = 1;
                }
            }
            tabela = tabela.criaHash(tamanho1, tamanho2);//Criar a tabela com o tamanho correto

            if (arquivo.createNewFile() == false) {//Existindo um arquivo Cadastro.txt entra nessa condição

                tabela = tabela.lerArquivo(arquivo, tabela);//É feita a leitura do arquivo
                tamanho1 = tabela.getLista().length;//tamanho1 recebe a quantidade de linhas da tabela lida
                tamanho2 = tabela.getLista()[0].length;//tamanho2 recebe a quantidade de colunas da tabela lida
                if (tamanho1 == 10 && tamanho2 == 10)//tamanho1 e tamanho2 sendo iguais o random é igual a 101
                    random = 101;
                if (tamanho2 == 10 && tamanho1 == 1)//tamanho1 = 10 tamanho2 = 1 sendo iguais o random é igual a 11
                    random = 11;
                else
                    random = (tamanho1 * tamanho2) + 1;//Sendo diferente o random é calculado multiplicando os tamanhos e somando 1
            }

            //Criação das variaveis(Nome, E-mail, Idade e Operação escolhida pelo usuário)
            String nome, email;
            int idade, op;

            do {//Será repetido até o usuário escolher uma opção igual ou maior a 6
                System.out.println("\nMENU\n1 - Adicionar\n2 - Printar\n3 - Buscar\n4 - Excluir\n5 - Excluir Tabela\n6 - Sair\nEscreva a sua opção:");//Menu do programa
                Scanner ler = new Scanner(System.in);//Scaner criado para ler a opção do usuário
                op = ler.nextInt();
                switch (op) {//Switch case para iniciar a opção escolhida pelo usuário
                    case 1://Adicionar um novo cadastro
                        if (tabela == null) {//Tabela Hash não existindo é criada novamente para poder ser inserido os novos cadastros
                            Hash aux = new Hash(null);
                            tabela = aux.criaHash(tamanho1, tamanho2);
                            aux = null;
                        }
                        //Criação de cadastro
                        System.out.println("\nDigite seu nome:");
                        Scanner lern = new Scanner(System.in);//É feito um novo Scaner para que sejá possivel o usuário escrever o nome completo dele
                        nome = lern.nextLine();

                        System.out.println("\nDigite sua idade:");
                        idade = ler.nextInt();

                        System.out.println("\nDigite seu email:");
                        email = lern.nextLine();

                        Pessoa P = new Pessoa(nome, email, idade, 0);//Criado um objeto pessoas com as informações digitadas, mas sem o ID gerado aleatoriamente

                        tabela = tabela.GerarID(random, P, tamanho2, tabela);//Será adicionado na tabela hash o novo cadastro com um ID gerado aleatoriamente que se encaixara na tabela
                        break;

                    case 2://Imprimir os valores da tabela hash
                        if (tabela == null) {//Não existindo nenhuma tabela, nada é impresso
                            System.out.println("Nao existe tabela Hash!");
                        }

                        if (tabela != null) {//Existindo uma tabela Hash
                            System.out.println("\nTIPOS DE IMPRESSAO\n1 - Imprimir dados\n2 - Imprimir posição dos IDs na tabela\n3 - Imprimir ambos\nDigite a opcao desejada:");//Tipos de impressão de dados possiveis
                            op = ler.nextInt();

                            switch (op) {
                                case 1:
                                    tabela.Print(tamanho2);//Imprimir somente os dados
                                    break;
                                case 2:
                                    tabela.PrintID(tamanho2);//Imprimir os IDs e onde se encontram na tabela
                                    break;
                                case 3://Imprimir os dados e a localização dos IDs
                                    tabela.Print(tamanho2);
                                    tabela.PrintID(tamanho2);
                                    break;
                            }
                        }
                        op = 2;
                        break;

                    case 3://Buscar dados pela tabela

                        if (tabela == null) {//Não existindo nenhuma tabela, nada é buscado
                            System.out.println("Nao existe tabela Hash!");
                        }

                        if (tabela != null) {//Existindo uma tabela Hash
                            System.out.println("\nTIPOS DE BUSCA\n1 - Busca por nome\n2 - Busca por e-mail\n3 - Busca por idade\n4 - Busca por ID\nDigite a opcao desejada:");//Tipos de busca disponiveis
                            op = ler.nextInt();
                            switch (op) {
                                case 1://Busca feita por meio do nome do cadastro
                                    tabela.BuscaString(tabela, tamanho2, true);
                                    break;
                                case 2://Busca feita por meio do e-mail do cadastro
                                    tabela.BuscaString(tabela, tamanho2, false);
                                    break;
                                case 3://Busca por meio da idade do cadastro
                                    tabela.BuscaIdade(tabela, tamanho2);
                                    break;
                                case 4://Busca por meio do ID
                                    tabela.BuscaID(tabela, tamanho2);
                                    break;
                            }
                            op = 3;
                        }
                        break;

                    case 4://Exclusão de um cadastro da tabela

                        if (tabela == null) {//Não existindo nenhuma tabela, nada é buscado
                            System.out.println("Nao existe tabela Hash!");
                        }

                        if (tabela != null) {//Existindo uma tabela
                            int ex = -1;//Valor do ID do cadastro encontrado
                            System.out.println("\nTIPOS DE EXCLUSAO\n1 - Excluir por nome\n2 - Excluir por e-mail\n3 - Excluir por idade\n4 - Excluir por ID\nDigite a opcao desejada:");//Excluir um cadastro por meio de uma busca
                            op = ler.nextInt();

                            switch (op) {
                                case 1://Buscar um cadastro por uma busca de nome
                                    ex = tabela.BuscaString(tabela, tamanho2, true);
                                    break;
                                case 2://Buscar um cadastro por uma busca de e-mail
                                    ex = tabela.BuscaString(tabela, tamanho2, false);
                                    break;
                                case 3://Buscar um cadastro por uma busca de idade
                                    ex = tabela.BuscaIdade(tabela, tamanho2);
                                    break;
                                case 4://Buscar um cadastro por uma busca de ID
                                    ex = tabela.BuscaID(tabela, tamanho2);
                                    break;
                            }

                            if (ex != -1)//O ID sendo diferente de -1, foi encontrado o cadastro para ser excluido
                                tabela = tabela.Excluir(tabela, ex, tamanho2);

                            op = 4;
                        }
                        break;

                    case 5://Excluir a tabela Hash
                        tabela = null;//Excluir em Java é ser igual a null, pois sendo nulo o espaço de memória é utilizado novamente
                        System.out.println("Tabela Hash foi excluida!");
                        break;

                }
            } while (op < 6);

                if (tabela != null) {//Tabela sendo diferente de null será guardado no arquivo os cadastros feitos
                    FileWriter fw = new FileWriter(arquivo, false);
                    BufferedWriter bw = new BufferedWriter(fw);

                    tabela.adicionarArquivo(arquivo, tabela, random, fw, bw);

                    //O filewritter e bufferedwriter são fechados
                    bw.close();
                    fw.close();
                }

                if(tabela == null){//A tabela sendo igual a null, nada é salvo e o arquivo é deletado se existir
                    arquivo.delete();
                }

            }

        catch(IOException e){//Erro de criação do arquivo
                System.out.println("Erro: " + e.toString());
            }
        }
    }
