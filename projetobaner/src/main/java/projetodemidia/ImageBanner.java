package projetodemidia;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ImageBanner extends JFrame {
    private int imageIndex = 0;
    private String[] imagePaths = {
            "C:\\Users\\Samuel\\Downloads\\WhatsApp Image 2023-09-17 at 14.27.42.jpeg",
            "C:\\Users\\Samuel\\Downloads\\Comprovante_2023-09-16T08_30_53.260915.png",
            "C:\\Users\\Samuel\\Downloads\\Little Dark Age - MGMT【TRADUÇÃO】 (1)....mp4"
            // adicione mais caminhos de imagens conforme necessário
    };
    private ImageBannerDAO dao;

    public ImageBanner() {
        this.dao = new ImageBannerDAO();

        // Define o layout do quadro
        this.setLayout(new FlowLayout());

        // Define o tamanho e o título do quadro
        this.setSize(new Dimension(800, 600));
        this.setTitle("Image Banner");

        // Cria um novo objeto Timer que mudará a imagem a cada 5 segundos
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    BufferedImage image = ImageIO.read(new File(imagePaths[imageIndex]));
                    ImagePanel panel = new ImagePanel(image);
                    SwingUtilities.invokeLater(() -> {
                        // Remove o painel de imagem anterior do quadro
                        getContentPane().removeAll();

                        // Adiciona o novo painel de imagens ao quadro
                        getContentPane().add(panel);
                        getContentPane().validate();
                        getContentPane().repaint();
                    });

                    imageIndex = (imageIndex + 1) % imagePaths.length; // Move para a próxima imagem
                    dao.saveState(imageIndex); // Salva o índice da imagem atual
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }, 0, 5000); // altera a imagem a cada 5.000 milissegundos (5 segundos)

        // Carrega e exibe a primeira imagem
        try {
            // Carrega o índice da última imagem exibida
            imageIndex = dao.loadState();
            BufferedImage image = ImageIO.read(new File(imagePaths[imageIndex]));
            this.add(new ImagePanel(image));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Mostra o quadro
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageBanner();
        });
    }
}
