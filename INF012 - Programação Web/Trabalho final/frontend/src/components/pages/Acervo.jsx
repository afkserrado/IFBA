import { useCallback, useContext, useEffect, useState } from 'react';
import { Alert, Button, Col, Container, Form, InputGroup, Modal, Row, Spinner } from 'react-bootstrap';
import { AuthContext } from '../../contexts/AuthContext.js';
import api, { getApiErrorMessage } from '../../services/api';
import BookCard from '../BookCard';

const EMPTY_BOOK = { titulo: '', autor: '', isbn: '', quantidadeTotal: 1 };

function normalizarLivro(livro) {
  return {
    ...livro,
    quantidadeDisponivel: livro.quantidadeDisponivel ?? 0,
    disponivel: (livro.quantidadeDisponivel ?? 0) > 0,
  };
}

export default function Acervo() {
  const { isAdmin, user } = useContext(AuthContext);
  const [livros, setLivros] = useState([]);
  const [busca, setBusca] = useState('');
  const [tipoBusca, setTipoBusca] = useState('titulo');
  const [loading, setLoading] = useState(true);
  const [salvando, setSalvando] = useState(false);
  const [mensagem, setMensagem] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [livroEditando, setLivroEditando] = useState(null);
  const [formLivro, setFormLivro] = useState(EMPTY_BOOK);
  const [detalhes, setDetalhes] = useState(null);

  const carregarLivros = useCallback(async (term = '', field = 'titulo') => {
    try {
      setLoading(true);
      const normalizedTerm = term.trim();
      const url = normalizedTerm ? `/api/v1/livros/${field}` : '/api/v1/livros';
      const params = normalizedTerm
        ? { [field]: normalizedTerm, page: 0, size: 100 }
        : { page: 0, size: 100 };
      const { data } = await api.get(url, { params });
      setLivros((data.content || []).map(normalizarLivro));
    } catch (error) {
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível carregar o acervo.') });
      setLivros([]);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    Promise.resolve().then(() => carregarLivros(''));
  }, [carregarLivros]);

  const handleBuscar = (event) => {
    event.preventDefault();
    carregarLivros(busca, tipoBusca);
  };

  const abrirModalNovo = () => {
    setLivroEditando(null);
    setFormLivro(EMPTY_BOOK);
    setShowModal(true);
  };

  const abrirModalEditar = (livro) => {
    setLivroEditando(livro);
    setFormLivro({
      titulo: livro.titulo,
      autor: livro.autor,
      isbn: livro.isbn,
      quantidadeTotal: livro.quantidadeTotal ?? livro.quantidadeDisponivel,
    });
    setShowModal(true);
  };

  const handleSalvarLivro = async (event) => {
    event.preventDefault();
    setSalvando(true);
    const payload = { ...formLivro, quantidadeTotal: Number(formLivro.quantidadeTotal) };

    try {
      if (livroEditando) {
        await api.put(`/api/v1/livros/${livroEditando.id}`, payload);
        setMensagem({ tipo: 'success', texto: `Livro #${livroEditando.id} atualizado com sucesso.` });
      } else {
        await api.post('/api/v1/livros', payload);
        setMensagem({ tipo: 'success', texto: `Livro "${formLivro.titulo}" cadastrado com sucesso no acervo.` });
      }
      setShowModal(false);
      await carregarLivros(busca, tipoBusca);
    } catch (error) {
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível salvar o livro.') });
    } finally {
      setSalvando(false);
    }
  };

  const handleExcluirLivro = async (id) => {
    if (!window.confirm('Tem certeza que deseja excluir este exemplar do acervo?')) return;

    try {
      await api.delete(`/api/v1/livros/${id}`);
      setMensagem({ tipo: 'warning', texto: `Livro #${id} excluído com sucesso.` });
      await carregarLivros(busca, tipoBusca);
    } catch (error) {
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível excluir o livro.') });
    }
  };

  const handleDetalhes = async (livro) => {
    try {
      const [bookResponse, availabilityResponse] = await Promise.all([
        api.get(`/api/v1/livros/isbn/${livro.isbn}`),
        api.get(`/api/v1/livros/${livro.id}/disponibilidade`),
      ]);
      setDetalhes({ ...normalizarLivro(bookResponse.data), disponivel: availabilityResponse.data.disponivel });
    } catch (error) {
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível carregar os detalhes do livro.') });
    }
  };

  const alterarEstoque = async (acao) => {
    try {
      await api.post(`/api/v1/livros/${detalhes.id}/${acao}-estoque`);
      setMensagem({ tipo: 'success', texto: `Estoque ${acao === 'aumentar' ? 'aumentado' : 'reduzido'} com sucesso.` });
      await carregarLivros(busca, tipoBusca);
      await handleDetalhes(detalhes);
    } catch (error) {
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível atualizar o estoque.') });
    }
  };

  const handleSolicitarEmprestimo = async (livro) => {
    try {
      await api.post('/api/v1/emprestimos', { usuarioId: user.id, livroId: livro.id });
      setMensagem({ tipo: 'success', texto: `Empréstimo de "${livro.titulo}" registrado com sucesso.` });
      await carregarLivros(busca, tipoBusca);
    } catch (error) {
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível registrar o empréstimo.') });
    }
  };

  return (
    <Container className="mt-4 mb-5">
      <div className="d-flex justify-content-between align-items-center flex-wrap gap-2 mb-4">
        <div>
          <h2 className="fw-bold mb-0"><i className="bi bi-collection-play text-primary me-2" />Acervo da Biblioteca</h2>
          <p className="text-muted small mb-0">Consulte os livros disponíveis no catálogo e faça suas solicitações</p>
        </div>
        {isAdmin && (
          <Button variant="success" size="lg" className="fw-bold shadow-sm" onClick={abrirModalNovo}>
            <i className="bi bi-plus-circle me-2" /> Cadastrar Novo Livro
          </Button>
        )}
      </div>

      {mensagem && <Alert variant={mensagem.tipo} onClose={() => setMensagem(null)} dismissible className="shadow-sm"><i className="bi bi-info-circle-fill me-2" />{mensagem.texto}</Alert>}

      <Form className="mb-4" onSubmit={handleBuscar}>
        <InputGroup className="shadow-sm">
          <InputGroup.Text className="bg-white border-end-0"><i className="bi bi-search text-muted" /></InputGroup.Text>
          <Form.Select value={tipoBusca} onChange={(event) => setTipoBusca(event.target.value)} style={{ maxWidth: '130px' }} aria-label="Tipo de busca">
            <option value="titulo">Título</option>
            <option value="autor">Autor</option>
          </Form.Select>
          <Form.Control type="text" placeholder="Pesquise por título ou autor do livro..." className="border-start-0 py-2" value={busca} onChange={(event) => setBusca(event.target.value)} />
          {busca && <Button type="button" variant="outline-secondary" onClick={() => { setBusca(''); carregarLivros(''); }}>Limpar</Button>}
          <Button type="submit" variant="primary">Buscar</Button>
        </InputGroup>
      </Form>

      {loading && <div className="text-center py-5 my-5"><Spinner animation="border" variant="primary" style={{ width: '3.5rem', height: '3.5rem' }} /><p className="text-muted mt-3 fw-bold">Carregando acervo...</p></div>}
      {!loading && livros.length === 0 && <Alert variant="light" className="text-center py-5 border"><i className="bi bi-emoji-frown text-secondary" style={{ fontSize: '3rem' }} /><h5 className="mt-3">Nenhum livro encontrado</h5><p className="text-muted mb-0">Tente outro termo de busca.</p></Alert>}
      {!loading && livros.length > 0 && <Row xs={1} md={2} lg={3} className="g-4">{livros.map((livro) => <Col key={livro.id}><BookCard livro={livro} onExcluir={handleExcluirLivro} onSolicitar={handleSolicitarEmprestimo} onEditar={abrirModalEditar} onDetalhes={handleDetalhes} /></Col>)}</Row>}

      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton className={livroEditando ? 'bg-warning' : 'bg-success text-white'}><Modal.Title className="fw-bold"><i className={`bi ${livroEditando ? 'bi-pencil-square' : 'bi-book-half'} me-2`} />{livroEditando ? `Editar Livro #${livroEditando.id}` : 'Cadastrar Novo Livro'}</Modal.Title></Modal.Header>
        <Form onSubmit={handleSalvarLivro}>
          <Modal.Body className="p-4">
            <Form.Group className="mb-3"><Form.Label className="fw-bold">Título do Livro</Form.Label><Form.Control required minLength={3} value={formLivro.titulo} onChange={(event) => setFormLivro({ ...formLivro, titulo: event.target.value })} /></Form.Group>
            <Form.Group className="mb-3"><Form.Label className="fw-bold">Autor</Form.Label><Form.Control required minLength={3} value={formLivro.autor} onChange={(event) => setFormLivro({ ...formLivro, autor: event.target.value })} /></Form.Group>
            <Form.Group className="mb-3"><Form.Label className="fw-bold">ISBN</Form.Label><Form.Control required pattern="[0-9]{10}|[0-9]{13}" inputMode="numeric" placeholder="10 ou 13 dígitos" value={formLivro.isbn} onChange={(event) => setFormLivro({ ...formLivro, isbn: event.target.value.replace(/\D/g, '') })} /></Form.Group>
            <Form.Group className="mb-2"><Form.Label className="fw-bold">Quantidade total</Form.Label><Form.Control required type="number" min="0" value={formLivro.quantidadeTotal} onChange={(event) => setFormLivro({ ...formLivro, quantidadeTotal: event.target.value })} /></Form.Group>
          </Modal.Body>
          <Modal.Footer><Button variant="secondary" onClick={() => setShowModal(false)}>Cancelar</Button><Button variant={livroEditando ? 'warning' : 'success'} type="submit" className="fw-bold" disabled={salvando}>{salvando ? 'Salvando...' : livroEditando ? 'Salvar Alterações' : 'Cadastrar no Acervo'}</Button></Modal.Footer>
        </Form>
      </Modal>

      <Modal show={Boolean(detalhes)} onHide={() => setDetalhes(null)} centered>
        <Modal.Header closeButton><Modal.Title className="fw-bold">Detalhes do Livro</Modal.Title></Modal.Header>
        {detalhes && <><Modal.Body><p><strong>Título:</strong> {detalhes.titulo}</p><p><strong>Autor:</strong> {detalhes.autor}</p><p><strong>ISBN:</strong> {detalhes.isbn}</p><p><strong>Total:</strong> {detalhes.quantidadeTotal}</p><p className="mb-0"><strong>Disponíveis:</strong> {detalhes.quantidadeDisponivel}</p></Modal.Body>{isAdmin && <Modal.Footer><Button variant="outline-danger" disabled={detalhes.quantidadeDisponivel <= 0} onClick={() => alterarEstoque('reduzir')}><i className="bi bi-dash-circle me-1" /> Reduzir estoque</Button><Button variant="outline-success" disabled={detalhes.quantidadeDisponivel >= detalhes.quantidadeTotal} onClick={() => alterarEstoque('aumentar')}><i className="bi bi-plus-circle me-1" /> Aumentar estoque</Button></Modal.Footer>}</>}
      </Modal>
    </Container>
  );
}
