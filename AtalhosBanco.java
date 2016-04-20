/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;
import java.sql.*;
/**
 *
 * @author Knout
 */
public class AtalhosBanco {
    /*Variaveis globais para acessar o banco*/
    //Endereco do banco
    private String url = "jdbc:postgresql://localhost:5432/sinatran";
    private String usuario = "felipe";
    private String senha = "felipe";
    
    public void InserirCidadao(String nome, String data_nasc, char sexo, String endereco, String telefone, int rg, int cnh, String cpf){
        String duHast;
        try{
            //iniciando conexao com o banco.
            Connection ichTuDirWeh = DriverManager.getConnection(url, usuario, senha);
            java.sql.Statement tutMirNichtLeid = ichTuDirWeh.createStatement();
            //query de insercao
            tutMirNichtLeid.executeUpdate("INSERT INTO cidadao(nome, data_nasc, sexo, endereco, telefone, rg, cnh, cpf) VALUES ('"
                    + nome+"','"+data_nasc+"','"+sexo+"','"+endereco+"','"+telefone+"','"
                    +rg+"','"+cnh+"','"+cpf+"')");
            
            duHast = nome+" inserido com sucesso";
        }catch(Exception e){
            duHast = e.getMessage();
        }
        
    }
    
    
    public String ConsultarCidadao(String cpf){
        String cidadao = "";
        try{
            Connection c = DriverManager.getConnection(url, usuario, senha);
            java.sql.Statement b = c.createStatement();
            // consultar documentacao do jdbc do postgres...
            
            ResultSet t = b.executeQuery("SELECT (nome, sexo, endereco) FROM cidadao WHERE cpf='"+cpf+"'");
            // enquanto tiver resultados da consulta, sempre ira para o proximo...
            while(t.next()){
                // recebe apenas 1...
                cidadao += t.getString(1);
            }
        }catch (Exception e){
            cidadao = e.getMessage();
        }
        return cidadao;
    }
    
    public void ExcluirCidadao(String cpf){
        try{
            Connection c = DriverManager.getConnection(url, usuario, senha);
            java.sql.Statement b = c.createStatement();
            b.executeQuery("DELETE FROM cidadao WHERE cpf ='"+cpf+"'"); 
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void InserirAutomovel(String tipo, String modelo, String placa, String chassi){
        try{
            Connection c = DriverManager.getConnection(url, usuario, senha);
            java.sql.Statement b = c.createStatement();
            b.executeQuery("INSERT INTO automovel(tipo, modelo, placa, chassi) VALUES "
                    + "('"+tipo+"','"+modelo+"','"+placa+"','"+chassi+"')"); 
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public String PesquisarAutomovel(String placa){
        String p = "";
        try{
            Connection c = DriverManager.getConnection(url, usuario, senha);
            java.sql.Statement b = c.createStatement();
            ResultSet a = b.executeQuery("INSERT INTO automovel(tipo, modelo, placa, ");
            while(a.next()){
                p += a.getString(1);
            }
        }catch(Exception e){
            e.getMessage();
        }
        return p;
    }
    
    public void ExcluirAutomovel(String placa){
        try{
            Connection c = DriverManager.getConnection(url, usuario, senha);
            java.sql.Statement b = c.createStatement();
            b.executeQuery("DELETE FROM automovel WHERE placa='"+placa+"'");
            
        }catch(Exception e){
            e.getMessage();
        }
    }
    
   
}
