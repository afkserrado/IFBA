import { useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Navbar as BootstrapNavbar, Nav, Container, Button, Badge } from 'react-bootstrap';
import { AuthContext } from '../contexts/AuthContext.js';

export default function Navbar() {
  // Puxamos os dados da sessão usando o Hook useContext!
  const { signed, user, isAdmin, logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();               // Limpa o token e o usuário da memória
    navigate('/login');     // Manda de volta pra tela de login
  };

  return (
    <BootstrapNavbar bg="dark" variant="dark" expand="lg" className="shadow">
      <Container>
        {/* Logo */}
        <BootstrapNavbar.Brand as={Link} to="/" className="d-flex align-items-center">
          <i className="bi bi-book-half me-2 fs-4 text-warning"></i>
          <strong>Biblioteca IFBA</strong>
        </BootstrapNavbar.Brand>

        <BootstrapNavbar.Toggle aria-controls="menu-navegacao" />

        <BootstrapNavbar.Collapse id="menu-navegacao">
          {/* Links da Esquerda */}
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/">Início</Nav.Link>
            <Nav.Link as={Link} to="/acervo">Acervo</Nav.Link>
            {/* OLHE AQUI: Esse link só aparece se o usuário estiver logado (signed)! */}
            {signed && (
              <Nav.Link as={Link} to="/meus-emprestimos">
                <i className="bi bi-journal-check me-1"></i>
                Empréstimos
              </Nav.Link>
            )}
            {signed && (
              <Nav.Link as={Link} to="/minha-conta">
                <i className="bi bi-person-gear me-1"></i>
                Minha Conta
              </Nav.Link>
            )}
          {/* OLHE AQUI: Só aparece no menu se o usuário for ADMIN! */}
          {isAdmin && (
            <Nav.Link as={Link} to="/admin" className="text-warning fw-bold ms-2">
              <i className="bi bi-shield-lock-fill me-1"></i>
              Administração
            </Nav.Link>
            )}
          </Nav>
          
          {/* Lado Direito do Menu: Muda dinamicamente! */}
          <Nav className="align-items-center">
            {/* SE ESTIVER LOGADO (signed === true) */}
            {signed ? (
              <>
                <span className="text-light me-3">
                  <i className="bi bi-person-circle me-1"></i>
                  Olá, <strong>{user?.nome}</strong>
                </span>

                {/* Se for ADMIN, mostra uma etiqueta de destaque */}
                {isAdmin && (
                  <Badge bg="danger" className="me-3 px-2 py-1">
                    ADMIN
                  </Badge>
                )}

                <Button variant="outline-danger" size="sm" onClick={handleLogout}>
                  <i className="bi bi-box-arrow-right me-1"></i>
                  Sair
                </Button>
              </>
            ) : (
              /* SE NÃO ESTIVER LOGADO (signed === false) */
              <Nav.Link as={Link} to="/login" className="btn btn-outline-light px-3 ms-2">
                <i className="bi bi-box-arrow-in-right me-1"></i>
                Entrar
              </Nav.Link>
            )}
          </Nav>
        </BootstrapNavbar.Collapse>
      </Container>
    </BootstrapNavbar>
  );
}
