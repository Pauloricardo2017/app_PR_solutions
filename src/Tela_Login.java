
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Tela_Login extends javax.swing.JFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public Tela_Login() {
        initComponents ();
        conexao = ModuloConexao.conector();
        System.out.println(conexao);
        setTitle("PR_solutions   Tela de Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Paleta de cores suaves
        Color corDeFundo = new Color(100, 152, 250); // Azul suave
        Color corTexto = new Color(255, 255, 255); // Branco para o texto
        Color corBordaCampo = new Color(65, 115, 217); // Azul médio para a borda do campo
        Color corFundoCampo = new Color(255, 255, 255); // Azul claro para o fundo do campo
        Color corBotaoFundo = new Color(85, 142, 197); // Azul médio para o fundo do botão
        Color corBotaoTexto = Color.WHITE; // Texto branco para o botão



        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());
        painel.setBackground(corDeFundo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Rótulo e campo de usuário
        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        labelUsuario.setForeground(corTexto);
        campoUsuario = new JTextField(20);
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        campoUsuario.setBackground(corFundoCampo);
        campoUsuario.setBorder(createRoundedBorder(corBordaCampo, 10)); // Borda arredondada
        painel.add(labelUsuario, gbc);
        gbc.gridy = 1;
        painel.add(campoUsuario, gbc);

        // Rótulo e campo de senha
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        labelSenha.setForeground(corTexto);
        campoSenha = new JPasswordField(20);
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        campoSenha.setBackground(corFundoCampo);
        campoSenha.setBorder(createRoundedBorder(corBordaCampo, 10)); // Borda arredondada
        gbc.gridy = 2;
        painel.add(labelSenha, gbc);
        gbc.gridy = 3;
        painel.add(campoSenha, gbc);

        // Botão de login
        JButton botaoLogin = new JButton("Login");
        botaoLogin.setFont(new Font("Arial", Font.BOLD, 18));
        botaoLogin.setBackground(corBotaoFundo);
        botaoLogin.setForeground(corBotaoTexto);
        botaoLogin.setBorder(createRoundedBorder(corBordaCampo, 10)); // Borda arredondada
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        painel.add(botaoLogin, gbc);

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });

        add(painel);

        setVisible(true);
    }

    private void initComponents() {
    }

    private void realizarLogin() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        // Adicione a lógica de autenticação aqui

        if (usuario.equals("admin") && senha.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido");
        } else {
            JOptionPane.showMessageDialog(this, "Login falhou. Tente novamente.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Tela_Login();
            }
        });
    }

    // Método para criar uma borda arredondada com um raio específico
    private Border createRoundedBorder(Color color, int radius) {
        return BorderFactory.createCompoundBorder(
                new LineBorder(color, 2, true), // Borda interna
                new EmptyBorder(radius, radius, radius, radius) // Espaçamento interno
        );
    }
}













