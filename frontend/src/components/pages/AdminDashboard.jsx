import { useState, useEffect } from 'react';
import { Container, Card, Tabs, Tab, Table, Button, Badge, Alert, Form, Row, Col, Modal } from 'react-bootstrap';

export default function AdminDashboard() {
  const [abaAtiva, setAbaAtiva] = useState('livros');
  const [mensagem, setMensagem] = useState(null);

  // ESTADO DE USUÁRIOS (Lendo do nosso banco temporário do localStorage!)
  const [usuarios, setUsuarios] = useState([]);
  
  // ESTADO DE TODOS OS EMPRÉSTIMOS DO SISTEMA
  const [todosEmprestimos, setTodosEmprestimos] = useState([
    { id: 501, leitor: 'Usuário Estudante', livro: 'Engenharia de Software Moderna', data: '01/07/2026', prazo: '15/07/2026', status: 'atrasado', multa: 'R$ 12,00' },
    { id: 502, leitor: 'João da Silva', livro: 'Clean Code', data: '10/07/2026', prazo: '25/07/2026', status: 'em_dia', multa: 'R$ 0,00' },
    { id: 503, leitor: 'Maria Souza', livro: 'Java: Como Programar', data: '05/06/2026', prazo: '20/06/2026', status: 'devolvido', multa: 'R$ 4,00 (Paga)' }
  ]);

  // ESTADO DO MODAL DE CADASTRAR LIVRO
  const [showModalLivro, setShowModalLivro] = useState(false);
  const [novoLivro, setNovoLivro] = useState({ titulo: '', autor: '', categoria: '' });

  // Ao carregar a página, busca a lista de usuários no localStorage
  useEffect(() => {
    const listaUsuarios = JSON.parse(localStorage.getItem('@Biblioteca:usuarios') || '[]');
    setUsuarios(listaUsuarios);
  }, []);

  // CRUD USUÁRIOS: Promover a Admin
  const handlePromoverAdmin = (id) => {
    const listaAtualizada = usuarios.map(u => {
      if (u.id === id) return { ...u, role: 'ADMIN' };
      return u;
    });
    setUsuarios(listaAtualizada);
    localStorage.setItem('@Biblioteca:usuarios', JSON.stringify(listaAtualizada));
    setMensagem({ tipo: 'success', texto: '👑 Usuário promovido a Administrador com sucesso!' });
  };

  // CRUD USUÁRIOS: Excluir Usuário
  const handleExcluirUsuario = (id) => {
    if (window.confirm('Tem certeza que deseja excluir este usuário do sistema?')) {
      const listaAtualizada = usuarios.filter(u => u.id !== id);
      setUsuarios(listaAtualizada);
      localStorage.setItem('@Biblioteca:usuarios', JSON.stringify(listaAtualizada));
      setMensagem({ tipo: 'warning', texto: '🗑️ Usuário removido do sistema!' });
    }
  };

  // CADASTRO SIMULADO DE LIVRO VIA MODAL
  const handleSalvarLivro = (e) => {
    e.preventDefault();
    setShowModalLivro(false);
    setMensagem({ tipo: 'success', texto: `📚 O livro "${novoLivro.titulo}" foi cadastrado no acervo com sucesso!` });
    setNovoLivro({ titulo: '', autor: '', categoria: '' });
  };

  return (
    <Container className="mt-4 mb-5">
      
      {/* Cabeçalho */}
      <div className="d-flex justify-content-between align-items-center mb-4 flex-wrap">
        <div>
          <h2 className="fw-bold text-dark mb-0">
            <i className="bi bi-shield-lock-fill text-warning me-2"></i>
            Painel de Gestão Bibliotecária
          </h2>
          <p className="text-muted small mb-0">Área exclusiva para controle de acervo, usuários e monitoramento de multas.</p>
        </div>

        <Button variant="success" className="fw-bold shadow-sm" onClick={() => setShowModalLivro(true)}>
          <i className="bi bi-plus-circle me-1"></i> + Novo Livro no Acervo
        </Button>
      </div>

      {mensagem && (
        <Alert variant={mensagem.tipo} onClose={() => setMensagem(null)} dismissible className="shadow-sm">
          {mensagem.texto}
        </Alert>
      )}

      {/* ABAS DO PAINEL ADMIN */}
      <Card className="shadow-sm border-0">
        <Card.Body className="p-4">
          <Tabs activeKey={abaAtiva} onSelect={(k) => { setAbaAtiva(k); setMensagem(null); }} className="mb-4">
            
            {/* ================= ABA 1: CRUD DE LIVROS ================= */}
            <Tab eventKey="livros" title={<span><i className="bi bi-book me-1"></i> Gestão de Livros</span>}>
              <Alert variant="info" className="small">
                💡 <strong>Dica de Arquitetura:</strong> A exclusão e edição dos livros individuais acontece direto na página do <strong>Acervo</strong>, onde os botões vermelhos e amarelos foram liberados exclusivamente para o seu usuário Administrador!
              </Alert>
              <div className="text-center py-4">
                <Button variant="outline-primary" onClick={() => window.location.href = '/acervo'}>
                  Ir para o Acervo Gerenciar Exemplares <i className="bi bi-arrow-right ms-1"></i>
                </Button>
              </div>
            </Tab>

            {/* ================= ABA 2: CRUD DE USUÁRIOS ================= */}
            <Tab eventKey="usuarios" title={<span><i className="bi bi-people me-1"></i> Usuários ({usuarios.length})</span>}>
              <div className="table-responsive">
                <Table hover className="align-middle">
                  <thead className="table-light">
                    <tr>
                      <th>Nome / E-mail</th>
                      <th>CPF</th>
                      <th>Cargo (Role)</th>
                      <th className="text-end">Ações de Admin</th>
                    </tr>
                  </thead>
                  <tbody>
                    {usuarios.map((u) => (
                      <tr key={u.id}>
                        <td>
                          <strong>{u.nome}</strong><br/>
                          <small className="text-muted">{u.email}</small>
                        </td>
                        <td>{u.cpf || '000.000.000-00'}</td>
                        <td>
                          <Badge bg={u.role === 'ADMIN' ? 'danger' : 'secondary'} className="px-2 py-1">
                            {u.role}
                          </Badge>
                        </td>
                        <td className="text-end">
                          {u.role !== 'ADMIN' && (
                            <Button 
                              variant="outline-warning" 
                              size="sm" 
                              className="me-2 text-dark fw-bold"
                              onClick={() => handlePromoverAdmin(u.id)}
                            >
                              <i className="bi bi-arrow-up-circle me-1"></i> Promover a Admin
                            </Button>
                          )}
                          <Button 
                            variant="outline-danger" 
                            size="sm" 
                            disabled={u.email.includes('admin@biblioteca')} // Não deixa excluir o Admin Padrão!
                            onClick={() => handleExcluirUsuario(u.id)}
                          >
                            <i className="bi bi-trash"></i> Excluir
                          </Button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </Table>
              </div>
            </Tab>

            {/* ================= ABA 3: EMPRÉSTIMOS DO SISTEMA ================= */}
            <Tab eventKey="emprestimos" title={<span><i className="bi bi-arrow-left-right me-1"></i> Movimentações</span>}>
              <div className="table-responsive">
                <Table hover className="align-middle">
                  <thead className="table-light">
                    <tr>
                      <th>Leitor</th>
                      <th>Livro</th>
                      <th>Data / Prazo</th>
                      <th>Status Geral</th>
                    </tr>
                  </thead>
                  <tbody>
                    {todosEmprestimos.map((emp) => (
                      <tr key={emp.id}>
                        <td><strong>{emp.leitor}</strong></td>
                        <td>{emp.livro}</td>
                        <td><small>{emp.data} até {emp.prazo}</small></td>
                        <td>
                          {emp.status === 'atrasado' && <Badge bg="danger">Atrasado</Badge>}
                          {emp.status === 'em_dia' && <Badge bg="success">Em leitura</Badge>}
                          {emp.status === 'devolvido' && <Badge bg="secondary">Devolvido</Badge>}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </Table>
              </div>
            </Tab>

            {/* ================= ABA 4: MULTAS E ATRASOS ================= */}
            <Tab eventKey="multas" title={<span><i className="bi bi-exclamation-octagon me-1"></i> Atrasos e Multas</span>}>
              <Alert variant="danger">
                <strong><i className="bi bi-exclamation-triangle-fill me-1"></i> Alerta de Prazos Vencidos:</strong> Abaixo estão listados os leitores com atraso registrado e o valor de multa pendente para cobrança.
              </Alert>
              
              <Table bordered hover className="align-middle">
                <thead className="table-danger">
                  <tr>
                    <th>Leitor em Atraso</th>
                    <th>Livro Retido</th>
                    <th>Prazo Expirado em</th>
                    <th>Valor da Multa</th>
                    <th className="text-center">Ação</th>
                  </tr>
                </thead>
                <tbody>
                  {todosEmprestimos.filter(e => e.status === 'atrasado').map((emp) => (
                    <tr key={emp.id}>
                      <td><strong className="text-danger">{emp.leitor}</strong></td>
                      <td>{emp.livro}</td>
                      <td><span className="fw-bold">{emp.prazo}</span></td>
                      <td><span className="fs-6 fw-bold text-danger">{emp.multa}</span></td>
                      <td className="text-center">
                        <Button variant="danger" size="sm" onClick={() => alert(`Notificação de cobrança enviada para ${emp.leitor}!`)}>
                          <i className="bi bi-envelope-exclamation me-1"></i> Cobrar Leitor
                        </Button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            </Tab>

          </Tabs>
        </Card.Body>
      </Card>

      {/* MODAL DE CADASTRAR NOVO LIVRO */}
      <Modal show={showModalLivro} onHide={() => setShowModalLivro(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title className="fw-bold"><i className="bi bi-book-half me-2 text-success"></i>Cadastrar Novo Livro</Modal.Title>
        </Modal.Header>
        <Form onSubmit={handleSalvarLivro}>
          <Modal.Body>
            <Form.Group className="mb-3">
              <Form.Label>Título do Livro</Form.Label>
              <Form.Control type="text" placeholder="Ex: Padrões de Projeto" required value={novoLivro.titulo} onChange={e => setNovoLivro({...novoLivro, titulo: e.target.value})} />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Autor</Form.Label>
              <Form.Control type="text" placeholder="Ex: Erich Gamma" required value={novoLivro.autor} onChange={e => setNovoLivro({...novoLivro, autor: e.target.value})} />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Categoria</Form.Label>
              <Form.Control type="text" placeholder="Ex: Engenharia de Software" required value={novoLivro.categoria} onChange={e => setNovoLivro({...novoLivro, categoria: e.target.value})} />
            </Form.Group>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowModalLivro(false)}>Cancelar</Button>
            <Button variant="success" type="submit" className="fw-bold">Salvar no Catálogo</Button>
          </Modal.Footer>
        </Form>
      </Modal>

    </Container>
  );
}