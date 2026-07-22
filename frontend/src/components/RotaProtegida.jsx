import { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext';
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

  // 2. REGRA DE OURO DO RBAC: Se a página exige ADMIN e a pessoa logada NÃO É ADMIN:
  if (requireAdmin && !isAdmin) {
    // Usamos o setTimeout para o navegador não bloquear o pop-up do alert!
    setTimeout(() => {
      alert('⛔ ACESSO NEGADO! Esta área é de acesso exclusivo para Administradores, você não tem permissão para acessar.');
    }, 100);
    
    return <Navigate to="/acervo" replace />; // Chuta o usuário comum de volta pro Acervo!
  }

  return children;
}