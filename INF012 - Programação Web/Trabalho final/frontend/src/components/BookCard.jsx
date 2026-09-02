import { useContext } from 'react';
import { Badge, Button, Card } from 'react-bootstrap';
import { AuthContext } from '../contexts/AuthContext.js';

export default function BookCard({ livro, onExcluir, onSolicitar, onEditar, onDetalhes }) {
  const { isAdmin } = useContext(AuthContext);

  return (
    <Card className="shadow-sm h-100 border-0">
      <div
        className="text-center py-4 bg-light rounded-top d-flex align-items-center justify-content-center"
        style={{ height: '180px', background: 'linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%)' }}
      >
        <i className="bi bi-book text-secondary" style={{ fontSize: '4.5rem' }} />
      </div>

      <Card.Body className="d-flex flex-column justify-content-between">
        <div>
          <div className="d-flex justify-content-between align-items-center mb-2">
            <Badge bg={livro.disponivel ? 'success' : 'danger'} className="px-2 py-1">
              {livro.disponivel ? 'Disponível' : 'Indisponível'}
            </Badge>
            <small className="text-muted">ID: #{livro.id}</small>
          </div>

          <Card.Title className="fw-bold mt-2 text-truncate" title={livro.titulo}>{livro.titulo}</Card.Title>
          <Card.Text className="text-muted small mb-3">
            <i className="bi bi-person me-1" /> Autor: <strong>{livro.autor}</strong><br />
            <i className="bi bi-upc me-1" /> ISBN: {livro.isbn}<br />
            <i className="bi bi-box-seam me-1" /> Exemplares disponíveis: {livro.quantidadeDisponivel}
          </Card.Text>
        </div>

        <div className="pt-3 border-top mt-2">
          <Button variant="outline-primary" size="sm" className="w-100 mb-2" onClick={() => onDetalhes(livro)}>
            <i className="bi bi-eye me-1" /> Ver Detalhes
          </Button>

          {livro.disponivel && !isAdmin && (
            <Button variant="primary" size="sm" className="w-100 fw-bold" onClick={() => onSolicitar(livro)}>
              <i className="bi bi-journal-plus me-1" /> Solicitar Empréstimo
            </Button>
          )}

          {isAdmin && (
            <div className="d-flex gap-2 mt-1">
              <Button variant="warning" size="sm" className="w-50 text-dark fw-bold" onClick={() => onEditar(livro)}>
                <i className="bi bi-pencil-square me-1" /> Editar
              </Button>
              <Button variant="danger" size="sm" className="w-50 fw-bold" onClick={() => onExcluir(livro.id)}>
                <i className="bi bi-trash3 me-1" /> Excluir
              </Button>
            </div>
          )}
        </div>
      </Card.Body>
    </Card>
  );
}
