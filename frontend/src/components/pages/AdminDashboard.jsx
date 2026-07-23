import { useCallback, useEffect, useMemo, useState } from 'react';
import { Alert, Badge, Button, Card, Col, Container, Form, Modal, Row, Spinner, Tab, Table, Tabs } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import api, { getApiErrorMessage } from '../../services/api';

const emptyBook = { titulo: '', autor: '', isbn: '', quantidadeTotal: 1 };
const emptyLoan = { usuarioId: '', livroId: '' };
const formatDate = (date) => date ? new Intl.DateTimeFormat('pt-BR').format(new Date(`${date}T00:00:00`)) : '—';
const formatMoney = (value) => new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(value || 0);

export default function AdminDashboard() {
  const [abaAtiva, setAbaAtiva] = useState('livros');
  const [mensagem, setMensagem] = useState(null);
  const [usuarios, setUsuarios] = useState([]);
  const [livros, setLivros] = useState([]);
  const [emprestimos, setEmprestimos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [salvando, setSalvando] = useState(false);
  const [showModalLivro, setShowModalLivro] = useState(false);
  const [showModalUsuario, setShowModalUsuario] = useState(false);
  const [showModalEmprestimo, setShowModalEmprestimo] = useState(false);
  const [novoLivro, setNovoLivro] = useState(emptyBook);
  const [usuarioEditando, setUsuarioEditando] = useState(null);
  const [formUsuario, setFormUsuario] = useState({ nome: '', email: '', role: 'USER' });
  const [novoEmprestimo, setNovoEmprestimo] = useState(emptyLoan);
  const [emailBusca, setEmailBusca] = useState('');
  const [resultadoBusca, setResultadoBusca] = useState(null);

  const carregarDados = useCallback(async () => {
    try {
      setLoading(true);
      const [usersResponse, booksResponse, loansResponse] = await Promise.all([
        api.get('/api/v1/usuarios'),
        api.get('/api/v1/livros', { params: { page: 0, size: 100 } }),
        api.get('/api/v1/emprestimos'),
      ]);
      setUsuarios(usersResponse.data);
      setLivros(booksResponse.data.content || []);
      setEmprestimos(loansResponse.data);
    } catch (error) {
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível carregar o painel administrativo.') });
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { Promise.resolve().then(carregarDados); }, [carregarDados]);

  const usuariosPorId = useMemo(() => new Map(usuarios.map((usuario) => [usuario.id, usuario])), [usuarios]);
  const livrosPorId = useMemo(() => new Map(livros.map((livro) => [livro.id, livro])), [livros]);

  const executar = async (operation, successMessage) => {
    try {
      setSalvando(true);
      await operation();
      setMensagem({ tipo: 'success', texto: successMessage });
      await carregarDados();
      return true;
    } catch (error) {
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error) });
      return false;
    } finally {
      setSalvando(false);
    }
  };

  const salvarLivro = async (event) => {
    event.preventDefault();
    const saved = await executar(
      () => api.post('/api/v1/livros', { ...novoLivro, quantidadeTotal: Number(novoLivro.quantidadeTotal) }),
      `Livro "${novoLivro.titulo}" cadastrado com sucesso no acervo.`,
    );
    if (saved) { setShowModalLivro(false); setNovoLivro(emptyBook); }
  };

  const abrirEdicaoUsuario = (usuario) => {
    setUsuarioEditando(usuario);
    setFormUsuario({ nome: usuario.nome, email: usuario.email, role: usuario.role });
    setShowModalUsuario(true);
  };

  const salvarUsuario = async (event) => {
    event.preventDefault();
    const saved = await executar(
      () => api.put(`/api/v1/usuarios/${usuarioEditando.id}`, formUsuario),
      'Dados do usuário atualizados com sucesso.',
    );
    if (saved) setShowModalUsuario(false);
  };

  const promoverUsuario = async (usuario) => {
    await executar(
      () => api.put(`/api/v1/usuarios/${usuario.id}`, { nome: usuario.nome, email: usuario.email, role: 'ADMIN' }),
      'Usuário promovido a Administrador com sucesso.',
    );
  };

  const excluirUsuario = async (usuario) => {
    if (!window.confirm(`Tem certeza que deseja excluir ${usuario.nome}?`)) return;
    await executar(() => api.delete(`/api/v1/usuarios/${usuario.id}`), 'Usuário removido do sistema.');
  };

  const buscarPorEmail = async (event) => {
    event.preventDefault();
    if (!emailBusca.trim()) return;
    try {
      const { data } = await api.get('/api/v1/usuarios/busca-email', { params: { email: emailBusca.trim() } });
      setResultadoBusca(data);
    } catch (error) {
      setResultadoBusca(null);
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível localizar o usuário.') });
    }
  };

  const salvarEmprestimo = async (event) => {
    event.preventDefault();
    const saved = await executar(
      () => api.post('/api/v1/emprestimos', { usuarioId: Number(novoEmprestimo.usuarioId), livroId: Number(novoEmprestimo.livroId) }),
      'Empréstimo registrado com sucesso.',
    );
    if (saved) { setShowModalEmprestimo(false); setNovoEmprestimo(emptyLoan); }
  };

  const devolver = async (emprestimo) => {
    if (!window.confirm(`Confirmar devolução do empréstimo #${emprestimo.id}?`)) return;
    await executar(() => api.post(`/api/v1/emprestimos/${emprestimo.id}/devolucao`), 'Devolução registrada com sucesso.');
  };

  const cancelar = async (emprestimo) => {
    if (!window.confirm(`Cancelar o empréstimo #${emprestimo.id}?`)) return;
    await executar(() => api.post(`/api/v1/emprestimos/${emprestimo.id}/cancelamento`), 'Empréstimo cancelado com sucesso.');
  };

  const emprestimosAtrasados = emprestimos.filter((item) => item.status === 'ATRASADO');

  return (
    <Container className="mt-4 mb-5">
      <div className="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-2"><div><h2 className="fw-bold text-dark mb-0"><i className="bi bi-shield-lock-fill text-warning me-2" />Painel de Gestão Bibliotecária</h2><p className="text-muted small mb-0">Área exclusiva para controle de acervo, usuários e monitoramento de multas.</p></div><Button variant="success" className="fw-bold shadow-sm" onClick={() => setShowModalLivro(true)}><i className="bi bi-plus-circle me-1" /> + Novo Livro no Acervo</Button></div>
      {mensagem && <Alert variant={mensagem.tipo} dismissible onClose={() => setMensagem(null)} className="shadow-sm">{mensagem.texto}</Alert>}
      {loading ? <div className="text-center py-5"><Spinner animation="border" variant="primary" /><p className="text-muted mt-3">Carregando dados administrativos...</p></div> : <Card className="shadow-sm border-0"><Card.Body className="p-4"><Tabs activeKey={abaAtiva} onSelect={(key) => { setAbaAtiva(key); setMensagem(null); }} className="mb-4">
        <Tab eventKey="livros" title={<span><i className="bi bi-book me-1" /> Gestão de Livros</span>}><Alert variant="info" className="small">A edição e exclusão de livros estão disponíveis no Acervo, com os mesmos dados persistidos no microsserviço.</Alert><div className="text-center py-4"><Button as={Link} to="/acervo" variant="outline-primary">Ir para o Acervo Gerenciar Exemplares <i className="bi bi-arrow-right ms-1" /></Button></div></Tab>
        <Tab eventKey="usuarios" title={<span><i className="bi bi-people me-1" /> Usuários ({usuarios.length})</span>}><Form className="mb-3" onSubmit={buscarPorEmail}><Row className="g-2"><Col md={7}><Form.Control type="email" placeholder="Buscar usuário por e-mail" value={emailBusca} onChange={(event) => setEmailBusca(event.target.value)} /></Col><Col md="auto"><Button type="submit" variant="outline-primary">Buscar</Button>{resultadoBusca && <Button type="button" className="ms-2" variant="outline-secondary" onClick={() => { setResultadoBusca(null); setEmailBusca(''); }}>Limpar</Button>}</Col></Row></Form><div className="table-responsive"><Table hover className="align-middle"><thead className="table-light"><tr><th>Nome / E-mail</th><th>CPF</th><th>Cargo</th><th className="text-end">Ações de Admin</th></tr></thead><tbody>{(resultadoBusca ? [resultadoBusca] : usuarios).map((usuario) => <tr key={usuario.id}><td><strong>{usuario.nome}</strong><br /><small className="text-muted">{usuario.email}</small></td><td>{usuario.cpf}</td><td><Badge bg={usuario.role === 'ADMIN' ? 'danger' : 'secondary'}>{usuario.role}</Badge></td><td className="text-end"><Button variant="outline-primary" size="sm" className="me-2" onClick={() => abrirEdicaoUsuario(usuario)}><i className="bi bi-pencil" /> Editar</Button>{usuario.role !== 'ADMIN' && <Button variant="outline-warning" size="sm" className="me-2 text-dark" onClick={() => promoverUsuario(usuario)}><i className="bi bi-arrow-up-circle" /> Promover</Button>}<Button variant="outline-danger" size="sm" onClick={() => excluirUsuario(usuario)}><i className="bi bi-trash" /> Excluir</Button></td></tr>)}</tbody></Table></div></Tab>
        <Tab eventKey="emprestimos" title={<span><i className="bi bi-arrow-left-right me-1" /> Movimentações</span>}><div className="d-flex justify-content-end mb-3"><Button variant="primary" onClick={() => setShowModalEmprestimo(true)}><i className="bi bi-journal-plus me-1" /> Registrar Empréstimo</Button></div><div className="table-responsive"><Table hover className="align-middle"><thead className="table-light"><tr><th>Leitor</th><th>Livro</th><th>Data / Prazo</th><th>Status</th><th className="text-end">Ações</th></tr></thead><tbody>{emprestimos.map((item) => <tr key={item.id}><td><strong>{usuariosPorId.get(item.usuarioId)?.nome || `Usuário #${item.usuarioId}`}</strong></td><td>{livrosPorId.get(item.livroId)?.titulo || `Livro #${item.livroId}`}</td><td><small>{formatDate(item.dataEmprestimo)} até {formatDate(item.dataPrevistaDevolucao)}</small></td><td><Badge bg={item.status === 'ATRASADO' ? 'danger' : item.status === 'ATIVO' ? 'success' : 'secondary'}>{item.status}</Badge></td><td className="text-end">{['ATIVO', 'ATRASADO'].includes(item.status) && <><Button variant="outline-primary" size="sm" className="me-2" onClick={() => devolver(item)}>Devolver</Button><Button variant="outline-danger" size="sm" onClick={() => cancelar(item)}>Cancelar</Button></>}</td></tr>)}</tbody></Table></div></Tab>
        <Tab eventKey="multas" title={<span><i className="bi bi-exclamation-octagon me-1" /> Atrasos e Multas</span>}><Alert variant="danger"><strong><i className="bi bi-exclamation-triangle-fill me-1" /> Alerta de Prazos Vencidos:</strong> Empréstimos em atraso e suas multas calculadas pelo backend.</Alert><Table bordered hover className="align-middle"><thead className="table-danger"><tr><th>Leitor em Atraso</th><th>Livro Retido</th><th>Prazo</th><th>Valor da Multa</th><th className="text-center">Ação</th></tr></thead><tbody>{emprestimosAtrasados.map((item) => <tr key={item.id}><td><strong className="text-danger">{usuariosPorId.get(item.usuarioId)?.nome || `Usuário #${item.usuarioId}`}</strong></td><td>{livrosPorId.get(item.livroId)?.titulo || `Livro #${item.livroId}`}</td><td><span className="fw-bold">{formatDate(item.dataPrevistaDevolucao)}</span></td><td><span className="fs-6 fw-bold text-danger">{formatMoney(item.valorMulta)}</span></td><td className="text-center"><Button variant="danger" size="sm" onClick={() => devolver(item)}><i className="bi bi-arrow-return-left me-1" /> Registrar Devolução</Button></td></tr>)}</tbody></Table></Tab>
      </Tabs></Card.Body></Card>}

      <Modal show={showModalLivro} onHide={() => setShowModalLivro(false)} centered><Modal.Header closeButton><Modal.Title className="fw-bold"><i className="bi bi-book-half me-2 text-success" />Cadastrar Novo Livro</Modal.Title></Modal.Header><Form onSubmit={salvarLivro}><Modal.Body><Form.Group className="mb-3"><Form.Label>Título</Form.Label><Form.Control required minLength={3} value={novoLivro.titulo} onChange={(event) => setNovoLivro({ ...novoLivro, titulo: event.target.value })} /></Form.Group><Form.Group className="mb-3"><Form.Label>Autor</Form.Label><Form.Control required minLength={3} value={novoLivro.autor} onChange={(event) => setNovoLivro({ ...novoLivro, autor: event.target.value })} /></Form.Group><Form.Group className="mb-3"><Form.Label>ISBN</Form.Label><Form.Control required pattern="[0-9]{10}|[0-9]{13}" value={novoLivro.isbn} onChange={(event) => setNovoLivro({ ...novoLivro, isbn: event.target.value.replace(/\D/g, '') })} /></Form.Group><Form.Group><Form.Label>Quantidade total</Form.Label><Form.Control required type="number" min="0" value={novoLivro.quantidadeTotal} onChange={(event) => setNovoLivro({ ...novoLivro, quantidadeTotal: event.target.value })} /></Form.Group></Modal.Body><Modal.Footer><Button variant="secondary" onClick={() => setShowModalLivro(false)}>Cancelar</Button><Button variant="success" type="submit" disabled={salvando}>Salvar no Catálogo</Button></Modal.Footer></Form></Modal>
      <Modal show={showModalUsuario} onHide={() => setShowModalUsuario(false)} centered><Modal.Header closeButton><Modal.Title className="fw-bold">Editar Usuário</Modal.Title></Modal.Header><Form onSubmit={salvarUsuario}><Modal.Body><Form.Group className="mb-3"><Form.Label>Nome</Form.Label><Form.Control required value={formUsuario.nome} onChange={(event) => setFormUsuario({ ...formUsuario, nome: event.target.value })} /></Form.Group><Form.Group className="mb-3"><Form.Label>E-mail</Form.Label><Form.Control required type="email" value={formUsuario.email} onChange={(event) => setFormUsuario({ ...formUsuario, email: event.target.value })} /></Form.Group><Form.Group><Form.Label>Perfil</Form.Label><Form.Select value={formUsuario.role} onChange={(event) => setFormUsuario({ ...formUsuario, role: event.target.value })}><option value="USER">USER</option><option value="ADMIN">ADMIN</option></Form.Select></Form.Group></Modal.Body><Modal.Footer><Button variant="secondary" onClick={() => setShowModalUsuario(false)}>Cancelar</Button><Button variant="primary" type="submit" disabled={salvando}>Salvar alterações</Button></Modal.Footer></Form></Modal>
      <Modal show={showModalEmprestimo} onHide={() => setShowModalEmprestimo(false)} centered><Modal.Header closeButton><Modal.Title className="fw-bold">Registrar Empréstimo</Modal.Title></Modal.Header><Form onSubmit={salvarEmprestimo}><Modal.Body><Form.Group className="mb-3"><Form.Label>Leitor</Form.Label><Form.Select required value={novoEmprestimo.usuarioId} onChange={(event) => setNovoEmprestimo({ ...novoEmprestimo, usuarioId: event.target.value })}><option value="">Selecione um leitor</option>{usuarios.map((usuario) => <option key={usuario.id} value={usuario.id}>{usuario.nome} — {usuario.email}</option>)}</Form.Select></Form.Group><Form.Group><Form.Label>Livro disponível</Form.Label><Form.Select required value={novoEmprestimo.livroId} onChange={(event) => setNovoEmprestimo({ ...novoEmprestimo, livroId: event.target.value })}><option value="">Selecione um livro</option>{livros.filter((livro) => livro.quantidadeDisponivel > 0).map((livro) => <option key={livro.id} value={livro.id}>{livro.titulo} ({livro.quantidadeDisponivel} disponível/eis)</option>)}</Form.Select></Form.Group></Modal.Body><Modal.Footer><Button variant="secondary" onClick={() => setShowModalEmprestimo(false)}>Cancelar</Button><Button variant="primary" type="submit" disabled={salvando}>Registrar</Button></Modal.Footer></Form></Modal>
    </Container>
  );
}
