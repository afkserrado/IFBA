import { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext.js';
import { Spinner } from 'react-bootstrap';

// Adicionamos a propriedade "requireAdmin" (por padrão é falso)
export default function RotaProtegida({ children, requireAdmin = false }) {
  const { signed, isAdmin, loading } = useContext(AuthContext);

  if (loading) {
    return (
      <div className="text-center my-5 py-5">
        <Spinner animation="border" variant="primary" />
      </div>
    );
  }

  // 1. Se NÃO estiver logado, joga para o /login
  if (!signed) {
    return <Navigate to="/login" replace />;
  }

  // 2. Se a página exige ADMIN e a pessoa logada não é administradora.
  if (requireAdmin && !isAdmin) {
    return <Navigate to="/acervo" replace state={{ mensagem: 'Acesso restrito a administradores.' }} />;
  }

  return children;
}
