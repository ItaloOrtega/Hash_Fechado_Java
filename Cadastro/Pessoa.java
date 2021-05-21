package Cadastro;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Pessoa {

    private String name,email;//Variavies Nome e E-mail da classe
    private int age, ID;//Variaveis Idade e ID da classe

    public Pessoa(String nome,String email,int idade, int cod){
        //Criando e colocando os valores na classe

        this.name=nome;
        this.email=email;
        this.age=idade;
        this.ID=cod;
    }
    //Set do Nome da Pessoa
    public void setNome(String nome){
        this.name=nome;
    }

    //Set do E-Mail da Pessoa
    public void setEmail(String email){
        this.email=email;
    }

    //Set da Idade da Pessoa
    public void setIdade(int idade){
        this.age=idade;
    }

    //Set do ID da Pessoa
    public void setID(int id){
        this.ID=id;
    }

    //Get do Nome da Pessoa
    public String getNome(){
        return this.name;
    }

    //Get do E-Mail da Pessoa
    public String getEmail(){

        return this.email;
    }

    //Get da Idade da Pessoa
    public int getIdade(){

        return this.age;
    }

    //Get do ID da Pessoa
    public int getID(){
        return this.ID;
    }

}

