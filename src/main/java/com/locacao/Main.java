package com.locacao;

import com.locacao.util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Carregar FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            Parent root = loader.load();
            
            // Configurar cena
            Scene scene = new Scene(root, 1200, 800);
            
            // Configurar stage
            primaryStage.setTitle("Sistema de Locação de Veículos");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
            
        } catch (Exception e) {
            System.err.println("Erro ao iniciar aplicação: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public void stop() {
        // Fechar SessionFactory ao encerrar aplicação
        HibernateUtil.shutdown();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
