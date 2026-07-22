import { useState, useEffect, useContext } from 'react';
import { Container, Row, Col, Form, InputGroup, Button, Spinner, Alert, Modal } from 'react-bootstrap';
import { AuthContext } from '../../contexts/AuthContext';
import BookCard from '../BookCard';

export default function Acervo() {
  const { isAdmin } = useContext(AuthContext);

  const [livros, setLivros] = useState([]);
  const [busca, setBusca] = useState('');
  const [loading, setLoading] = useState(true);
  const [mensagem, setMensagem] = useState(null);

  const [showModal, setShowModal] = useState(false);
  const [livroEditando, setLivroEditando] = useState(null);
  const [formTitulo, setFormTitulo] = useState('');
  const [formAutor, setFormAutor] = useState('');
  const [formCategoria, setFormCategoria] = useState('');

  const atualizarBancoLivros = (novaLista) => {
    setLivros(novaLista);
    localStorage.setItem('@Biblioteca:livros', JSON.stringify(novaLista));
  };

  useEffect(() => {
    setLoading(true);
    const carregarLivros = () => {
      const salvos = localStorage.getItem('@Biblioteca:livros');
      
      if (salvos) {
        setLivros(JSON.parse(salvos));
      } else {
        // AGORA TODOS OS 6 LIVROS NASCEM VERDES E PRONTOS PARA VOCÊ PEGAR!
        const listaInicial = [
          { id: 101, titulo: 'Clean Code: Habilidades Práticas', autor: 'Robert C. Martin', categoria: 'Programação', disponivel: true },
          { id: 102, titulo: 'Arquitetura Limpa', autor: 'Robert C. Martin', categoria: 'Engenharia de Software', disponivel: true },
          { id: 103, titulo: 'Entendendo Algoritmos', autor: 'Aditya Bhargava', categoria: 'Ciência da Computação', disponivel: true },
          { id: 104, titulo: 'Java: Como Programar', autor: 'Paul Deitel', categoria: 'Linguagens', disponivel: true },
          { id: 105, titulo: 'Padrões de Projeto (GoF)', autor: 'Erich Gamma', categoria: 'Engenharia de Software', disponivel: true },
          { id: 106, titulo: 'O Pragmático Programador', autor: 'Andrew Hunt', categoria: 'Carreira Tech', disponivel: true },
        ];
        setLivros(listaInicial);
        localStorage.setItem('@Biblioteca:livros', JSON.stringify(listaInicial));
      }
      setLoading(false);
    };

    setTimeout(carregarLivros, 400);
  }, []);

  const handleSolicitarEmprestimo = (livro) => {
    const listaAtualizada = livros.map(l => {
      if (l.id === livro.id) {
        return { ...l, disponivel: false };
      }
      return l;
    });
    atualizarBancoLivros(listaAtualizada);

    const emprestimosSalvos = JSON.parse(localStorage.getItem('@Biblioteca:emprestimosAtivos') || '[]');
    const dataHoje = new Date();
    const dataPrazo = new Date();
    dataPrazo.setDate(dataHoje.getDate() + 15);

    const novoEmprestimo = {
      id: livro.id,
      titulo: livro.titulo,
      dataEmprestimo: dataHoje.toLocaleDateString('pt-BR'),
      prazoDevolucao: dataPrazo.toLocaleDateString('pt-BR'),
      atrasado: false,
      diasAtraso: 0,
      multa: 'R$ 0,00'
    };

    localStorage.setItem('@Biblioteca:emprestimosAtivos', JSON.stringify([novoEmprestimo, ...emprestimosSalvos]));

    setMensagem({ 
      tipo: 'success', 
      texto: `🎉 Empréstimo de "${livro.titulo}" realizado! Acesse "Meus Empréstimos" no menu para ver o prazo.` 
    });
    setTimeout(() => setMensagem(null), 5000);
  };

  const abrirModalNovo = () => {
    setLivroEditando(null);
    setFormTitulo('');
    setFormAutor('');
    setFormCategoria('');
    setShowModal(true);
  };

  const abrirModalEditar = (livro) => {
    setLivroEditando(livro);
    setFormTitulo(livro.titulo);
    setFormAutor(livro.autor);
    setFormCategoria(livro.categoria);
    setShowModal(true);
  };

  const handleSalvarLivro = (e) => {
    e.preventDefault();

    if (livroEditando) {
      const listaAtualizada = livros.map(l => {
        if (l.id === livroEditando.id) {
          return { ...l, titulo: formTitulo, autor: formAutor, categoria: formCategoria };
        }
        return l;
      });
      atualizarBancoLivros(listaAtualizada);
      setMensagem({ tipo: 'success', texto: `✏️ Livro #${livroEditando.id} atualizado com sucesso!` });
    } else {
      const novoLivroObj = {
        id: Date.now(),
        titulo: formTitulo,
        autor: formAutor,
        categoria: formCategoria,
        disponivel: true
      };
      atualizarBancoLivros([novoLivroObj, ...livros]);
      setMensagem({ tipo: 'success', texto: `📚 Livro "${formTitulo}" cadastrado com sucesso no acervo!` });
    }

    setShowModal(false);
    setTimeout(() => setMensagem(null), 5000);
  };

  const handleExcluirLivro = (idParaExcluir) => {
    if (window.confirm('Tem certeza que deseja excluir este exemplar do acervo?')) {
      const listaAtualizada = livros.filter(l => l.id !== idParaExcluir);
      atualizarBancoLivros(listaAtualizada);
      setMensagem({ tipo: 'warning', texto: `🗑️ Livro #${idParaExcluir} excluído com sucesso!` });
      setTimeout(() => setMensagem(null), 4000);
    }
  };

  const livrosFiltrados = livros.filter((livro) => {
    const termo = busca.toLowerCase();
    return livro.titulo.toLowerCase().includes(termo) || livro.autor.toLowerCase().includes(termo);
  });

  return (
    <Container className="mt-4 mb-5">
      <div className="d-flex justify-content-between align-items-center flex-wrap gap-2 mb-4">
        <div>
          <h2 className="fw-bold mb-0"><i className="bi bi-collection-play text-primary me-2"></i>Acervo da Biblioteca</h2>
          <p className="text-muted small mb-0">Consulte os livros disponíveis no catálogo e faça suas solicitações</p>
        </div>

        {isAdmin && (
          <Button variant="success" size="lg" className="fw-bold shadow-sm" onClick={abrirModalNovo}>
            <i className="bi bi-plus-circle me-2"></i> Cadastrar Novo Livro
          </Button>
        )}
      </div>

      {mensagem && (
        <Alert variant={mensagem.tipo} onClose={() => setMensagem(null)} dismissible className="shadow-sm">
          <i className="bi bi-info-circle-fill me-2"></i> {mensagem.texto}
        </Alert>
      )}

      <Form className="mb-4">
        <InputGroup className="shadow-sm">
          <InputGroup.Text className="bg-white border-end-0">
            <i className="bi bi-search text-muted"></i>
          </InputGroup.Text>
          <Form.Control 
            type="text" 
            placeholder="Pesquise por título ou autor do livro..." 
            className="border-start-0 py-2"
            value={busca}
            onChange={(e) => setBusca(e.target.value)}
          />
          {busca && <Button variant="outline-secondary" onClick={() => setBusca('')}>Limpar</Button>}
        </InputGroup>
      </Form>

      {loading && (
        <div className="text-center py-5 my-5">
          <Spinner animation="border" variant="primary" style={{ width: '3.5rem', height: '3.5rem' }} />
          <p className="text-muted mt-3 fw-bold">Carregando acervo de livros via Axios...</p>
        </div>
      )}

      {!loading && livrosFiltrados.length === 0 && (
        <Alert variant="light" className="text-center py-5 border">
          <i className="bi bi-emoji-frown text-secondary" style={{ fontSize: '3rem' }}></i>
          <h5 className="mt-3">Nenhum livro encontrado</h5>
          <p className="text-muted mb-0">Não achamos nenhum livro que combine com "{busca}". Tente outro termo!</p>
        </Alert>
      )}

      {!loading && livrosFiltrados.length > 0 && (
        <Row xs={1} md={2} lg={3} className="g-4">
          {livrosFiltrados.map((livro) => (
            <Col key={livro.id}>
              <BookCard 
                livro={livro} 
                onExcluir={handleExcluirLivro}
                onSolicitar={handleSolicitarEmprestimo}
                onEditar={abrirModalEditar}
              />
            </Col>
          ))}
        </Row>
      )}

      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton className={livroEditando ? 'bg-warning' : 'bg-success text-white'}>
          <Modal.Title className="fw-bold">
            <i className={`bi ${livroEditando ? 'bi-pencil-square' : 'bi-book-half'} me-2`}></i>
            {livroEditando ? `Editar Livro #${livroEditando.id}` : 'Cadastrar Novo Livro'}
          </Modal.Title>
        </Modal.Header>

        <Form onSubmit={handleSalvarLivro}>
          <Modal.Body className="p-4">
            <Form.Group className="mb-3">
              <Form.Label className="fw-bold">Título do Livro</Form.Label>
              <Form.Control type="text" placeholder="Ex: Padrões de Projeto" required value={formTitulo} onChange={e => setFormTitulo(e.target.value)} />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label className="fw-bold">Autor</Form.Label>
              <Form.Control type="text" placeholder="Ex: Erich Gamma" required value={formAutor} onChange={e => setFormAutor(e.target.value)} />
            </Form.Group>

            <Form.Group className="mb-2">
              <Form.Label className="fw-bold">Categoria</Form.Label>
              <Form.Control type="text" placeholder="Ex: Engenharia de Software" required value={formCategoria} onChange={e => setFormCategoria(e.target.value)} />
            </Form.Group>
          </Modal.Body>

          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowModal(false)}>Cancelar</Button>
            <Button variant={livroEditando ? 'warning' : 'success'} type="submit" className="fw-bold">
              {livroEditando ? 'Salvar Alterações' : 'Cadastrar no Acervo'}
            </Button>
          </Modal.Footer>
        </Form>
      </Modal>
    </Container>
  );
}