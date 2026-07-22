import { useState, useContext, useEffect } from 'react';
import { Container, Card, Table, Badge, Button, Tabs, Tab, Alert } from 'react-bootstrap';
import { AuthContext } from '../../contexts/AuthContext';

export default function Emprestimos() {
  const { user } = useContext(AuthContext);
  const [abaAtiva, setAbaAtiva] = useState('ativos');
  const [mensagem, setMensagem] = useState(null);

  const [emprestimosAtivos, setEmprestimosAtivos] = useState([]);
  const [historico, setHistorico] = useState([]);

  // 1. CARREGA AS LISTAS VAZIAS SE FOR A PRIMEIRA VEZ (SEM EMPRÉSTIMOS PRENSADOS)
  useEffect(() => {
    const salvos = localStorage.getItem('@Biblioteca:emprestimosAtivos');
    
    if (salvos) {
      setEmprestimosAtivos(JSON.parse(salvos));
    } else {
      const iniciais = []; // Lista limpa! Só entra livro aqui quando você clicar em "Solicitar Empréstimo".
      setEmprestimosAtivos(iniciais);
      localStorage.setItem('@Biblioteca:emprestimosAtivos', JSON.stringify(iniciais));
    }

    const historicoSalvo = localStorage.getItem('@Biblioteca:historico');
    if (historicoSalvo) {
      setHistorico(JSON.parse(historicoSalvo));
    } else {
      const historicoInicial = []; // Histórico limpo!
      setHistorico(historicoInicial);
      localStorage.setItem('@Biblioteca:historico', JSON.stringify(historicoInicial));
    }
  }, []);

  // 2. DEVOLUÇÃO INTEGRADA COM O ACERVO
  const handleDevolver = (itemParaDevolver) => {
    if (window.confirm(`Deseja confirmar a solicitação de devolução para "${itemParaDevolver.titulo}"?`)) {
      const novosAtivos = emprestimosAtivos.filter(item => item.id !== itemParaDevolver.id);
      setEmprestimosAtivos(novosAtivos);
      localStorage.setItem('@Biblioteca:emprestimosAtivos', JSON.stringify(novosAtivos));

      const novoHistorico = {
        id: itemParaDevolver.id,
        titulo: itemParaDevolver.titulo,
        dataEmprestimo: itemParaDevolver.dataEmprestimo,
        dataDevolucao: new Date().toLocaleDateString('pt-BR'),
        status: itemParaDevolver.atrasado ? `Devolvido com atraso (Multa gerada: ${itemParaDevolver.multa})` : 'Devolvido no prazo'
      };

      const novaListaHistorico = [novoHistorico, ...historico];
      setHistorico(novaListaHistorico);
      localStorage.setItem('@Biblioteca:historico', JSON.stringify(novaListaHistorico));

      const livrosDoAcervo = JSON.parse(localStorage.getItem('@Biblioteca:livros') || '[]');
      const acervoAtualizado = livrosDoAcervo.map(l => {
        if (Number(l.id) === Number(itemParaDevolver.id)) {
          return { ...l, disponivel: true };
        }
        return l;
      });
      localStorage.setItem('@Biblioteca:livros', JSON.stringify(acervoAtualizado));

      setMensagem({
        tipo: 'success',
        texto: `✅ Devolução registrada! O livro "${itemParaDevolver.titulo}" voltou a ficar disponível no Acervo.`
      });
      setTimeout(() => setMensagem(null), 6000);
    }
  };
  
  return (
    <Container className="mt-4 mb-5">
      <div className="mb-4">
        <h2 className="fw-bold"><i className="bi bi-journal-check text-primary me-2"></i>Meus Empréstimos</h2>
        <p className="text-muted small">
          Área do leitor <strong>{user?.nome}</strong> — Acompanhe seus prazos de devolução, pendências e multas.
        </p>
      </div>

      {mensagem && (
        <Alert variant={mensagem.tipo} onClose={() => setMensagem(null)} dismissible className="shadow-sm">
          {mensagem.texto}
        </Alert>
      )}

      <Card className="shadow-sm border-0">
        <Card.Body className="p-4">
          <Tabs activeKey={abaAtiva} onSelect={(k) => { setAbaAtiva(k); setMensagem(null); }} className="mb-4">
            
            <Tab eventKey="ativos" title={<span><i className="bi bi-book me-1"></i> Empréstimos Ativos <Badge bg="primary" pill>{emprestimosAtivos.length}</Badge></span>}>
              {emprestimosAtivos.length === 0 ? (
                <Alert variant="light" className="text-center py-5 border">
                  <i className="bi bi-journal-x text-secondary" style={{ fontSize: '3rem' }}></i>
                  <h5 className="mt-3">Você não possui empréstimos ativos no momento!</h5>
                  <p className="text-muted mb-0">Vá até a página do Acervo para solicitar novos livros para leitura.</p>
                </Alert>
              ) : (
                <div className="table-responsive">
                  <Table hover className="align-middle mb-0">
                    <thead className="table-light">
                      <tr>
                        <th>Livro / ID</th>
                        <th>Data Empréstimo</th>
                        <th>Prazo Devolução</th>
                        <th>Situação</th>
                        <th>Multa</th>
                        <th className="text-end">Ação</th>
                      </tr>
                    </thead>
                    <tbody>
                      {emprestimosAtivos.map((item) => (
                        <tr key={item.id} className={item.atrasado ? 'table-danger' : ''}>
                          <td>
                            <strong>{item.titulo}</strong><br/>
                            <small className="text-muted">ID: #{item.id}</small>
                          </td>
                          <td>{item.dataEmprestimo}</td>
                          <td><span className="fw-bold">{item.prazoDevolucao}</span></td>
                          <td>
                            {item.atrasado ? (
                              <Badge bg="danger" className="p-2"><i className="bi bi-exclamation-triangle-fill me-1"></i>Atrasado {item.diasAtraso} dias</Badge>
                            ) : (
                              <Badge bg="success" className="p-2"><i className="bi bi-check-circle-fill me-1"></i>Em dia</Badge>
                            )}
                          </td>
                          <td><span className={item.atrasado ? 'text-danger fw-bold' : 'text-muted'}>{item.multa}</span></td>
                          <td className="text-end">
                            <Button variant={item.atrasado ? 'danger' : 'outline-primary'} size="sm" className="fw-bold" onClick={() => handleDevolver(item)}>
                              <i className="bi bi-arrow-return-left me-1"></i> Devolver Livro
                            </Button>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
                </div>
              )}
            </Tab>

            <Tab eventKey="historico" title={<span><i className="bi bi-clock-history me-1"></i> Histórico de Leitura <Badge bg="secondary" pill>{historico.length}</Badge></span>}>
              <div className="table-responsive">
                <Table hover className="align-middle mb-0">
                  <thead className="table-light">
                    <tr>
                      <th>Livro / ID</th>
                      <th>Data Empréstimo</th>
                      <th>Data Devolução</th>
                      <th>Situação Final</th>
                    </tr>
                  </thead>
                  <tbody>
                    {historico.map((item) => (
                      <tr key={item.id}>
                        <td>
                          <strong>{item.titulo}</strong><br/>
                          <small className="text-muted">ID: #{item.id}</small>
                        </td>
                        <td>{item.dataEmprestimo}</td>
                        <td><span className="fw-bold text-success">{item.dataDevolucao}</span></td>
                        <td>
                          <Badge bg={item.status.includes('atraso') ? 'warning' : 'success'} text={item.status.includes('atraso') ? 'dark' : 'light'} className="p-2">
                            {item.status}
                          </Badge>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </Table>
              </div>
            </Tab>
          </Tabs>
        </Card.Body>
      </Card>
    </Container>
  );
}