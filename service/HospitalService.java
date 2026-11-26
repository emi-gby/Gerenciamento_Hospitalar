package service;

import exception.EstoqueSangueInsuficienteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Administrador;
import model.BancoDeSangue;
import model.Cirurgia;
import model.Consulta;
import model.Funcionario;
import model.Medico;
import model.Paciente;
import model.Pessoa;
import model.Recepcionista;
import model.Prontuario;
import arquivos.ArquivoManager;

public class HospitalService {
    private List<Funcionario> funcionarios;
    private List<Paciente> pacientes;
    private List<Consulta> consultas;
    private List<Cirurgia> cirurgias;
    private BancoDeSangue bancoDeSangue;
    private ArquivoManager arquivoManager;

    private int proximoIdProntuario;
    private int proximoIdConsulta;
    private int proximoIdCirurgia;

    public HospitalService() {
        this.funcionarios = new ArrayList<>();
        this.pacientes = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.cirurgias = new ArrayList<>();
        this.bancoDeSangue = new BancoDeSangue();
        this.arquivoManager = new ArquivoManager();

        carregarDados();

    }

    // --- CARREGAR DADOS DO SISTEMA ---
    public void carregarDados() {
        funcionarios = arquivoManager.carregarFuncionarios();
        pacientes = arquivoManager.carregarPacientes();
        bancoDeSangue = arquivoManager.carregarBancoDeSangue();
        consultas = arquivoManager.carregarConsultas();
        cirurgias = arquivoManager.carregarCirurgias();

        atualizarContadoresId();
    }

    public void atualizarContadoresId() {
        // recalcula os contadores de id a partir dos maiores valores de id dos dados
        int maxProntuario = 0;
        for (Paciente p : pacientes) {
            if(p != null && p.getProntuario().getIdProntuario() > maxProntuario) {
                maxProntuario = p.getProntuario().getIdProntuario();
            }
        }
        this.proximoIdProntuario = maxProntuario + 1;

        int maxConsulta = 0;
        for (Consulta c : consultas) {
            if (c.getIdConsulta() > maxConsulta) {
                maxConsulta = c.getIdConsulta();
            }
        }
        this.proximoIdConsulta = maxConsulta + 1;

        int maxCirurgia = 0;
        for (Cirurgia cir : cirurgias) {
            if (cir.getIdCirurgia() > maxCirurgia) {
                maxCirurgia = cir.getIdCirurgia();
            }
        }
        this.proximoIdCirurgia = maxCirurgia + 1;
    }

    // --- AUTENTICAÇÃO ---
    public Funcionario autenticar(String login, String senha) {
        for (Funcionario func : funcionarios) {
            if (func != null && func.autenticar(login, senha)) {
                return func;
            }
        }
        return null;
    }

    // --- CADASTROS E LISTAGENS (PARA TABELAS / TELAS) ---

    // (Recepcionista)
    public void cadastrarPaciente(String nome, String cpf, String dataNascimento,
                                  String telefone, String tipoSanguineo, String convenio) {

        // Checagem de CPF duplicado
        for (Paciente pac : pacientes) {
            if (pac.getCpf().equals(cpf)) {
                throw new IllegalArgumentException("CPF já cadastrado no sistema.");
            }
        }
        // Instacia novo prontuário para o paciente
        Prontuario prontuario = new Prontuario(proximoIdProntuario);
        proximoIdProntuario++;

        // Instancia Paciente e adiciona em pacientes
        Paciente paciente = new Paciente(nome, cpf, dataNascimento, telefone, tipoSanguineo, convenio, prontuario);
        pacientes.add(paciente);

        // Salva para a Persistência
        arquivoManager.salvarPacientes(pacientes);
    }

    // (Administrador)
    public void cadastrarMedico(String nome, String cpf, String dataNascimento,
                                String telefone, String tipoSanguineo, String matricula,
                                String login, String senha, String crm, String especialidade) {
        // Instancia Medico e adiciona em funcionarios
        Medico medico = new Medico(nome, cpf, dataNascimento, telefone, tipoSanguineo, matricula, login, senha, crm, especialidade);
        funcionarios.add(medico);

        // Salva para a Persistência
        arquivoManager.salvarFuncionarios(funcionarios);
    }

    // (Administrador)
    public void cadastrarRecepcionista(String nome, String cpf, String dataNascimento,
                                       String telefone, String tipoSanguineo, String matricula,
                                       String login, String senha, String turno) {
        // Instancia Recepcionista e adiciona em funcionarios
        Recepcionista recepcionista = new Recepcionista(nome, cpf, dataNascimento, telefone, tipoSanguineo, matricula, login, senha, turno);
        funcionarios.add(recepcionista);

        // Salva para a Persistência
        arquivoManager.salvarFuncionarios(funcionarios);
    }

    // (Administrador)
    public void cadastrarAdministrador(String nome, String cpf, String dataNascimento,
                                       String telefone, String tipoSanguineo, String matricula,
                                       String login, String senha, int nivelAcesso) {
        // Instancia Administrador e adiciona em funcionarios
        Administrador administrador = new Administrador(nome, cpf, dataNascimento, telefone, tipoSanguineo, matricula, login, senha, nivelAcesso);
        funcionarios.add(administrador);

        // Salva para a Persistência
        arquivoManager.salvarFuncionarios(funcionarios);
    }

    // (Administrador)
    public void atualizarFuncionario(String matricula, String nome, String telefone,
                                     String tipoSanguineo, String login, String senha,
                                     String crm, String especialidade,
                                     String turno, Integer nivelAcesso) {
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula não informada.");
        }
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula().equals(matricula)) {
                if (nome != null && !nome.isBlank()) {
                    funcionario.setNome(nome.trim());
                }
                if (telefone != null && !telefone.isBlank()) {
                    funcionario.setTelefone(telefone.trim());
                }
                if (tipoSanguineo != null && !tipoSanguineo.isBlank()) {
                    funcionario.setTipoSanguineo(tipoSanguineo.trim());
                }
                if (login != null && !login.isBlank()) {
                    funcionario.setLogin(login.trim());
                }

                if (funcionario instanceof Medico) {
                    Medico m = (Medico) funcionario;
                    if (especialidade != null && !especialidade.isBlank()) {
                        m.setEspecialidade(especialidade.trim());
                    }
                } else if (funcionario instanceof Recepcionista) {
                    Recepcionista r = (Recepcionista) funcionario;
                    if (turno != null && !turno.isBlank()) {
                        r.setTurno(turno.trim());
                    }
                } else if (funcionario instanceof Administrador) {
                    Administrador a = (Administrador) funcionario;
                    if (nivelAcesso != null) {
                        a.setNivelAcesso(nivelAcesso);
                    }
                }

                arquivoManager.salvarFuncionarios(funcionarios);
                return;
            }
        }
        throw new IllegalArgumentException("Funcionário não encontrado.");

    }

    // (Administrador)
    public void removerFuncionario(String matricula, Funcionario usuario) {
        if(!(usuario instanceof Administrador)) {
            throw new SecurityException("Apenas administradores com nível de acesso 2 podem remover funcionários.");
        }
        Administrador adm = (Administrador) usuario;
        if (adm.getNivelAcesso() == 1) {
            throw new SecurityException("Apenas administradores com nível de acesso 2 podem remover funcionários.");
        }

        if (adm.getMatricula().equals(matricula)) {
            throw new IllegalArgumentException("Não é possível remover o usuário atual do sistema.");
        }
        for (Funcionario funcionario : funcionarios) {
            if(funcionario.getMatricula().equals(matricula)) {
                funcionarios.remove(funcionario);

                // Salva para a Persistência
                arquivoManager.salvarFuncionarios(funcionarios);
                return;
            }
        }
        throw new IllegalArgumentException("Funcionário não encontrado.");
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    public List<Paciente> listarPacientes() {
        return pacientes;
    }

    public List<Consulta> listarConsultas() {
        return consultas;
    }

    public List<Cirurgia> listarCirurgias() {
        return cirurgias;
    }

    // --- BUSCAS SIMPLES ---
    public Paciente buscarPacientePorCpf(String cpf) {

        for(Paciente paciente : pacientes) {
            if(paciente.getCpf().equals(cpf)) {
                return paciente;
            }
        }
        return null;
    }

    public Medico buscarMedicoPorMatricula(String matricula) {

        for(Funcionario funcionario : funcionarios) {
            if(funcionario instanceof Medico && funcionario.getMatricula().equals(matricula)) {
                return (Medico) funcionario;
            }
        }
        return null;
    }

    // --- CONSULTAS, CIRURGIAS E BANCO DE SANGUE ---
    // (Recepcionista)
    public void agendarConsulta(String dataHora, String cpfPaciente, String matriculaMedico) {
        // Localizar Paciente e Medico
        Paciente paciente = buscarPacientePorCpf(cpfPaciente);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não encontrado.");
        }
        Medico medico = buscarMedicoPorMatricula(matriculaMedico);
        if(medico == null) {
            throw new IllegalArgumentException("Médico não encontrado.");
        }
        // Cria consulta
        Consulta consulta = new Consulta(proximoIdConsulta, dataHora, paciente, medico);
        proximoIdConsulta++;
        // Adiciona consulta em consultas
        consultas.add(consulta);

        // Salva para a Persistência
        arquivoManager.salvarConsultas(consultas);
    }

    // (Recepcionista)
    public void cancelarConsulta(int idConsulta) {
        for (Consulta consulta : consultas) {
            if (consulta.getIdConsulta() == idConsulta) {
                consulta.cancelar();
                // Salva para a Persistência
                arquivoManager.salvarConsultas(consultas);
                return;
            }
        }
        throw new IllegalArgumentException("Consulta não encontrada.");
    }

    // (Medico)
    public void concluirConsulta(int idConsulta) {
        for (Consulta consulta : consultas) {
            if (consulta.getIdConsulta() == idConsulta) {
                consulta.concluir();
                // Salva para a Persistência
                arquivoManager.salvarConsultas(consultas);
                return;
            }
        }
        throw new IllegalArgumentException("Consulta não encontrada.");
    }

    // (Medico)
    public void adicionarRegistroProntuario(Medico medico, String cpfPaciente, String descricao, String prescricao) {
        Paciente paciente = buscarPacientePorCpf(cpfPaciente);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não encontrado.");
        }
        Prontuario prontuario = paciente.getProntuario();
        prontuario.adicionarRegistro(medico, descricao, prescricao);
        arquivoManager.salvarPacientes(pacientes);
    }

    // (Medico)
    public void adicionarRegistroProntuario(Medico medico, String cpfPaciente, String descricao) {
        Paciente paciente = buscarPacientePorCpf(cpfPaciente);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não encontrado.");
        }
        Prontuario prontuario = paciente.getProntuario();
        prontuario.adicionarRegistro(medico, descricao);
        arquivoManager.salvarPacientes(pacientes);
    }

    // (Medico)
    public void adicionarAlergiaProntuario(String cpfPaciente, String alergia) {
        Paciente paciente = buscarPacientePorCpf(cpfPaciente);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não encontrado.");
        }
        Prontuario prontuario = paciente.getProntuario();
        prontuario.adicionarAlergia(alergia);
        arquivoManager.salvarPacientes(pacientes);
    }

    // (Medico)
    public List<Consulta>  listarConsultasPorMedico(Medico medico) {
        List<Consulta> medicoConsultas = new ArrayList<>();

        for (Consulta consulta : consultas) {
            if (consulta != && medico != null &&
                    medico.getMatricula().equals(consulta.getMedico().getMatricula()) {
                medicoConsultas.add(consulta);
            }
        }

        return medicoConsultas;
    }

    // (Recepcionista)
    public void solicitarCirurgia(String cpfPaciente, String matriculaMedico, String dataHora,
                                  String tipoCirurgia, boolean isEmergencia, int bolsasSangueNecessarias) {
        // Localizar Paciente e Medico
        Paciente paciente = buscarPacientePorCpf(cpfPaciente);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente não encontrado.");
        }
        Medico medico = buscarMedicoPorMatricula(matriculaMedico);
        if(medico == null) {
            throw new IllegalArgumentException("Médico não encontrado.");
        }

        // Cria cirurgia
        Cirurgia cirurgia = new Cirurgia(proximoIdCirurgia, paciente, medico, dataHora, tipoCirurgia, isEmergencia, bolsasSangueNecessarias);
        proximoIdCirurgia++;
        // Adiciona cirurgia em cirurgias
        cirurgias.add(cirurgia);

        // Salva para a Persistência
        arquivoManager.salvarCirurgias(cirurgias);
    }

    // (Medico)
    public void realizarCirurgia(int idCirurgia) throws EstoqueSangueInsuficienteException {
        // Localizar Cirurgia, autorizar e concluir
        for(Cirurgia cirurgia : cirurgias) {
            if(cirurgia.getIdCirurgia() == idCirurgia) {
                cirurgia.autorizarCirurgia(bancoDeSangue);
                cirurgia.concluirCirurgia(bancoDeSangue);

                // Salva para a Persistência
                arquivoManager.salvarCirurgias(cirurgias);
                arquivoManager.salvarBancoDeSangue(bancoDeSangue);
                return;
            }
        }
        // Erro quando idCirurgia não foi encontrado em cirurgias
        throw new IllegalArgumentException("Cirurgia não encontrada.");

    }

    // (Recepcionista)
    public void registrarDoacao(Pessoa doador, int quantidadeBolsas) {
        bancoDeSangue.registrarDoacao(doador, quantidadeBolsas);

        // Salva para a Persistência
        arquivoManager.salvarBancoDeSangue(bancoDeSangue);
    }

    public BancoDeSangue getBancoDeSangue() {
        return bancoDeSangue;
    }

    // (Recepcionista)
    public Map<String, Integer> consultarEstoqueDeSangue() {
        return bancoDeSangue.consultarEstoque();
    }

    // (Recepcionista)
    public int consultarEstoqueDeSangue(String tipoSanguineo) {
        return bancoDeSangue.consultarEstoque(tipoSanguineo);
    }
}