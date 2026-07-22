import { useContext } from 'react';
import { Card, Button, Badge } from 'react-bootstrap';
import { AuthContext } from '../contexts/AuthContext';

// Este é o componente REUTILIZÁVEL (Exigência do ReactJS no projeto)
// Ele recebe via "props" (livro, onExcluir, onSolicitar) tudo o que precisa para desenhar um cartão
export default function BookCard({ livro, onExcluir, onSolicitar }) {
  // Puxamos a informação do cargo para aplicar a regra de ocultação de botões
  const { isAdmin } = useContext(AuthContext);

  return (
    <Card className="shadow-sm h-100 border-0">
      {/* Capa ilustrativa com ícone do Bootstrap */}
      <div 
        className="text-center py-4 bg-light rounded-top d-flex align-items-center justify-content-center" 
        style={{ height: '180px', background: 'linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%)' }}
      >
        <i className="bi bi-book text-secondary" style={{ fontSize: '4.5rem' }}></i>
      </div>

      <Card.Body className="d-flex flex-column justify-content-between">
        <div>
          {/* Status do Livro (Disponível vs Emprestado) */}
          <div className="d-flex justify-content-between align-items-center mb-2">
            <Badge bg={livro.disponivel ? 'success' : 'danger'} className="px-2 py-1">
              {livro.disponivel ? 'Disponível' : 'Emprestado'}
            </Badge>
            <small className="text-muted">ID: #{livro.id}</small>
          </div>

          <Card.Title className="fw-bold mt-2 text-truncate" title={livro.titulo}>
            {livro.titulo}
          </Card.Title>
          
          <Card.Text className="text-muted small mb-3">
            <i className="bi bi-person me-1"></i> Autor: <strong>{livro.autor}</strong><br/>
            <i className="bi bi-tag me-1"></i> Categoria: {livro.categoria}
          </Card.Text>
        </div>

        {/* --- ÁREA DE BOTÕES COM CONTROLE POR CARGO (RBAC) --- */}
        <div className="pt-3 border-top mt-2">
          
          {/* REGRA 1: Usuário comum ou Admin podem ver detalhes */}
          <Button variant="outline-primary" size="sm" className="w-100 mb-2">
            <i className="bi bi-eye me-1"></i> Ver Detalhes
          </Button>

          {/* REGRA 2: Só exibe botão de "Solicitar" se o livro estiver DISPONÍVEL */}
          {livro.disponivel && !isAdmin && (
            <Button 
              variant="primary" 
              size="sm" 
              className="w-100 fw-bold"
              onClick={() => onSolicitar(livro)}
            >
              <i className="bi bi-journal-plus me-1"></i> Solicitar Empréstimo
            </Button>
          )}

          {/* REGRA 3: BOTÕES EXCLUSIVOS DO ADMINISTRADOR */}
          {isAdmin && (
            <div className="d-flex gap-2 mt-1">
              <Button variant="warning" size="sm" className="w-50 text-dark fw-bold">
                <i className="bi bi-pencil-square me-1"></i> Editar
              </Button>
              
              <Button 
                variant="danger" 
                size="sm" 
                className="w-50 fw-bold"
                onClick={() => onExcluir(livro.id)}
              >
                <i className="bi bi-trash3 me-1"></i> Excluir
              </Button>
            </div>
          )}
        </div>

      </Card.Body>
    </Card>
  );
}