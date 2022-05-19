import java.sql.Connection;
import java.sql.PreparedStatement;
public class Pessoa {
    private int codigo;
    private String nome;
    private String fone;
    private String email;

    public void inserir() throws Exception{
        //1. Definir o comando SQL
        String sql = "INSERT INTO tb_pessoa (nome, fone, email) VALUES (?, ?, ?)";
        //2. Abrir uma conexão com o MySQL Server
        ConnectionFactory factory = new ConnectionFactory();
        Connection conexao = factory.getConnection();
        //3. Preparar o comando (solicitar ao MySQL Server que compile o comando SQL previamente)
        PreparedStatement ps = conexao.prepareStatement(sql);
        //4. Substituir os eventuais placeholders
        ps.setString(1, nome);
        ps.setString(2, fone);
        ps.setString(3, email);
        //5. Executar o comando
        ps.execute();
        //6. Fechar os recursos (conexão e o comando preparado)
        ps.close();
        conexao.close();
    }

    public void atualizar() throws Exception{
        //1. Especificar o comando SQL(UPTADE)
        String sql = "UPTADE tb_pessoa SET nome = ?, fone = ?, email = ? Where cod_pessoa = ?";
        //2. Abrir conexao
        try(
            Connection conexao = ConnectionFactory.getConnection(); 
            PreparedStatement ps = conexao.prepareStatement(sql);
            ){
                //4. Subustituir os placeholders
                ps.setString(1, nome);
                ps.setString(2, fone);
                ps.setString(3, email);
                ps.setInt(4, codigo);
                //5. Executar o comando
                ps.execute();
        }  
    }
    public void apagar() throws Exception{
        String sql = "DELETE FROM tb_pessoa WHERE cod_pessoa = ?";
        try(
            Connection conexao = ConnectionFactory.getConnection(); 
            PreparedStatement ps = conexao.prepareStatement(sql);
        ){
            ps.setInt(1, codigo);
            ps.execute();
        }
    }
    public static void listar() throws Exception{
        String sql = "Select * from tb_pessoa";
        try(
            Connection conexao = ConnectionFactory.getConnection(); 
            PreparedStatement ps = conexao.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){
                int codigo = rs.getInt("cod_pessoa");
                String nome = rs.getString("nome");
                String fone = rs.getString("fone");
                String email = rs.getString("email");
            
                System.out.printf("código: %d, nome: %s, fone: %s, email: %s", codigo, nome, fone, email);
            
            }
        }   
    }

    public Pessoa (String nome, String fone, String email){
        this(0, nome, fone, email);
    }
    public Pessoa(int codigo, String nome, String fone, String email){
        setCodigo(codigo);
        setNome(nome);
        setFone(fone);
        setEmail(email);
        
    }
    public Pessoa(int codigo){
        this(codigo, null, null, null);
    }
    public int getCodigo() {
        return codigo;
    }

    public String getEmail() {
        return email;
    }
    public String getFone() {
        return fone;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}