

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Tela_Login extends JFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public Tela_Login() {
        initComponents();
        conexao = ModuloConexao.conector();

        setTitle("PR_solutions   Login");
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

        // Label e ícone de status de conexão
        JLabel labelStatus = new JLabel("Status da Conexão:");
        labelStatus.setFont(new Font("Arial", Font.PLAIN, 18));
        labelStatus.setForeground(corTexto);
        gbc.gridy = 5;
        painel.add(labelStatus, gbc);

        JLabel labelIconeStatus = new JLabel();
        gbc.gridy = 6;
        painel.add(labelIconeStatus, gbc);

        // Adiciona ActionListener ao botão de login
        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logar();
            }
        });

        // Define o ícone de status com base na conexão
        if (conexao != null) {
            labelIconeStatus.setIcon(new ImageIcon(getClass().getResource("/icons/dbok.png")));
        } else {
            labelIconeStatus.setIcon(new ImageIcon(getClass().getResource("/icons/dberror.png")));
        }

        add(painel);

        setVisible(true);
    }

    private void initComponents() {
    }

    private Border createRoundedBorder(Color color, int radius) {
        return BorderFactory.createCompoundBorder(
                new LineBorder(color, 2, true), // Borda interna
                new EmptyBorder(radius, radius, radius, radius) // Espaçamento interno
        );
    }

    private boolean verificarCredenciais(String usuario, String senha) {
        String sql = "SELECT * FROM tbusuario WHERE login = ? AND senha = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, senha);

            rs = pst.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void logar() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        // Verifica as credenciais no banco de dados
        if (verificarCredenciais(usuario, senha)) {
            // Adiciona a lógica para abrir a tela principal após o login bem-sucedido.
            // Substitua 'TelaPrincipal' pelo nome da sua classe de tela principal.
            new TelaPrincipal().setVisible(true);

            // Fecha a tela de login
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorreta. Tente novamente.");
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
}



















