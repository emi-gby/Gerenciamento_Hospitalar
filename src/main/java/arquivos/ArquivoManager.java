package arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.BancoDeSangue;
import model.Cirurgia;
import model.Consulta;
import model.Funcionario;
import model.Paciente;

public class ArquivoManager {
    private static final String ARQUIVO_PACIENTES = "dados/pacientes.txt";
    private static final String ARQUIVO_FUNCIONARIOS = "dados/funcionarios.txt";
    private static final String ARQUIVO_CONSULTAS = "dados/consultas.txt";
    private static final String ARQUIVO_CIRURGIAS = "dados/cirurgias.txt";
    private static final String ARQUIVO_SANGUE = "dados/sangue.txt";
    private static final String SEPARADOR = ";";

    // Metodos = salvar e carregar
    // Metodos para Funcionarios já implementados
    // Falta : Concluir metodos para consultas e Implementar métodos para consultas / pacientes / banco de sangue

    public void salvarCirurgias(List<Cirurgia> cirurgias) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CIRURGIAS))) {
            for(Cirugia c : cirurgias) {
                // TODO: salvar dados da cirurgia + cpfPaciente + matriculaMedico + status
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar cirurgias: " + e.getMessage(), e);
        }
    }
    public List<Cirurgia> carregarCirurgias(List<Paciente> pacientes, List<Funcionario> funcionarios) {
        List<Cirurgia> cirurgias = new ArrayList<>();
        // TODO: ler arquivo de cirurgias e reconectar Paciente + Medico pelas listas já carregadas
        return cirurgias;
    }

    // Mesma lógica de funcionarios vale para cirurgias / consultas / pacientes / banco de sangue
    // Salvar dados no arquivos com base nos atributos especificos de cada classe
    // Carregar dados dos arquivos usando a mesma ideia de salvar com base nos atributos especificos
    // Dica para quando for carregar os dados: particionar a String usando "String[] partes = linha.split(SEPARADOR, -1);"
    // cada elemento da lista de String será um atributo da classe :)

    // !! Quando for carregar ou salvar dados de 'pacientes', salve também junto com os dados referentes
    // aos atributos de Prontuario


    public void salvarFuncionarios(List<Funcionario> funcionarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_FUNCIONARIOS))) {
            for (Funcionario f : funcionarios) {
                String linha = montarLinhaFuncionario(f);
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar funcionários: " + e.getMessage(), e);
        }
    }

    public List<Funcionario> carregarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        File arquivo = new File(ARQUIVO_FUNCIONARIOS);

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // divide a linha pelo SEPARADOR e instancia o Funcionario correto para colocar dentro da lista de funcionarios
                Funcionario f = dividirFuncionario(linha);
                if (f != null) {
                    funcionarios.add(f);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar funcionários: " + e.getMessage(), e);
        }
        return funcionarios;
    }


    // Metodos Auxiliares

    // Cria uma string base para todo funcionário a partir dos getters e verifica o tipo de Funcionario
    // para concatenar a String resultado com o tipo, a base e os atributos especificos de cada Funcionario
    // O retorno é uma string especifica para cada tipo, com os atributos separados pelo SEPARADOR
    private String montarLinhaFuncionario(Funcionario f) {
        String base = String.join(SEPARADOR,
                f.getNome(),
                f.getCpf(),
                f.getDataNascimento(),
                f.getTelefone(),
                f.getTipoSanguineo(),
                f.getMatricula(),
                f.getLogin(),
                f.getSenhaParaPersistencia()
        );
    }

    if (f instanceof Medico) {
        Medico m = (Medico) f;
        return "MEDICO" + SEPARADOR + base + SEPARADOR
                + m.getCrm() + SEPARADOR
                + m.getEspecialidade();
    } else if (f instanceof Recepcionista) {
        Recepcionista r = (Recepcionista) f;
        return "RECEPCIONISTA" + SEPARADOR + base + SEPARADOR
                + r.getTurno();
    } else if (f instanceof Administrador) {
        Administrador a = (Administrador) f;
        return "ADMINISTRADOR" + SEPARADOR + base + SEPARADOR
                + a.getNivelAcesso();
    }

    // Particiona a String recebida para que cada parte separada pelo SEPARADOR seja uma nova variável auxiliar que faz referência
    // aos atributos base da classe Funcionario e, depois de verificar o tipo de Funcionario, também referencia os atributos
    // especificos de cada um deles. De forma que o retorno seja um novo Objeto Funcionario que é definido pelo tipo.
    private Funcionario dividirFuncionario(String linha) {
        String[] partes = linha.split(SEPARADOR, -1);

        String tipo = partes[0];
        String nome = partes[1];
        String cpf = partes[2];
        String dataNascimento = partes[3];
        String telefone = partes[4];
        String tipoSanguineo = partes[5];
        String matricula = partes[6];
        String login = partes[7];
        String senha = partes[8];

        switch (tipo) {
            case "MEDICO":
                return new Medico(nome, cpf, dataNascimento, telefone, tipoSanguineo,
                        matricula, login, senha, partes[9], partes[10]);

            case "RECEPCIONISTA":
                return new Recepcionista(nome, cpf, dataNascimento, telefone, tipoSanguineo,
                        matricula, login, senha, partes[9]);

            case "ADMINISTRADOR":
                int nivelAcesso = Integer.parseInt(partes[9].trim());
                return new Administrador(nome, cpf, dataNascimento, telefone, tipoSanguineo,
                        matricula, login, senha, nivelAcesso);

            default:
                return null;
        }
    }
}
