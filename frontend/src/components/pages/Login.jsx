import { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, Card, Form, Button, Alert, Tab, Tabs, Spinner } from 'react-bootstrap';
import { AuthContext } from '../../contexts/AuthContext';

export default function Login() {
  // Agora também puxamos a função cadastrar do Contexto!
  const { login, cadastrar } = useContext(AuthContext);
  const navigate = useNavigate();

  const [abaAtiva, setAbaAtiva] = useState('login');
  const [loading, setLoading] = useState(false);
  const [erro, setErro] = useState('');
  const [sucesso, setSucesso] = useState('');

  // Estados de LOGIN
  const [emailLogin, setEmailLogin] = useState('');
  const [senhaLogin, setSenhaLogin] = useState('');

  // Estados de CADASTRO
  const [nomeCad, setNomeCad] = useState('');
  const [cpfCad, setCpfCad] = useState('');
  const [emailCad, setEmailCad] = useState('');
  const [senhaCad, setSenhaCad] = useState('');
  const [confirmaSenhaCad, setConfirmaSenhaCad] = useState('');

  // --- LOGIN ---
  const handleLogin = async (e) => {
    e.preventDefault();
    setErro('');
    setLoading(true);

    try {
      await new Promise(resolve => setTimeout(resolve, 800));
      await login(emailLogin, senhaLogin);
      navigate('/');
    } catch (err) {
      // Exibe a mensagem real do erro (Ex: "E-mail ou senha incorretos!")
      setErro(err.message || 'Falha ao entrar no sistema.');
      setLoading(false);
    }
  };

  // --- CADASTRO ---
  const handleCadastro = async (e) => {
    e.preventDefault();
    setErro('');
    setSucesso('');

    if (senhaCad !== confirmaSenhaCad) {
      return setErro('As senhas não coincidem!');
    }

    if (senhaCad.length < 4) {
      return setErro('A senha deve ter pelo menos 4 caracteres.');
    }

    setLoading(true);

    try {
      await new Promise(resolve => setTimeout(resolve, 800));

      // SALVA DE VERDADE NO NOVO BANCO TEMPORÁRIO!
      await cadastrar({
        nome: nomeCad,
        cpf: cpfCad,
        email: emailCad,
        senha: senhaCad
      });

      setLoading(false);
      setSucesso('Cadastro realizado com sucesso! Use seu e-mail e senha para entrar.');
      
      // Limpa tudo e joga pra aba de login
      setNomeCad('');
      setCpfCad('');
      setEmailCad('');
      setSenhaCad('');
      setConfirmaSenhaCad('');
      setAbaAtiva('login');
    } catch (err) {
      setErro(err.message || 'Erro ao realizar o cadastro.');
      setLoading(false);
    }
  };

  return (
    <Container className="mt-5 d-flex justify-content-center">
      <Card style={{ width: '480px' }} className="shadow border-0">
        <Card.Body className="p-4">
          
          <div className="text-center mb-4">
            <i className="bi bi-book-half text-primary" style={{ fontSize: '3rem' }}></i>
            <h3 className="mt-2 fw-bold">LOGIN DA BIBLIOTECA IFBA</h3>
            <p className="text-muted small">Entre ou cadastre-se no nosso sistema</p>
          </div>

          {erro && <Alert variant="danger" onClose={() => setErro('')} dismissible><i className="bi bi-exclamation-triangle-fill me-2"></i>{erro}</Alert>}
          {sucesso && <Alert variant="success" onClose={() => setSucesso('')} dismissible><i className="bi bi-check-circle-fill me-2"></i>{sucesso}</Alert>}

          <Tabs
            activeKey={abaAtiva}
            onSelect={(k) => { setAbaAtiva(k); setErro(''); setSucesso(''); }}
            className="mb-4 nav-justified"
          >
            {/* --- ABA LOGIN --- */}
            <Tab eventKey="login" title={<span><i className="bi bi-box-arrow-in-right me-1"></i> Entrar</span>}>
              <Form onSubmit={handleLogin}>

                <Form.Group className="mb-3">
                  <Form.Label>E-mail</Form.Label>
                  <Form.Control type="email" placeholder="Digite seu e-mail" required value={emailLogin} onChange={(e) => setEmailLogin(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-4">
                  <Form.Label>Senha</Form.Label>
                  <Form.Control type="password" placeholder="Digite sua senha" required value={senhaLogin} onChange={(e) => setSenhaLogin(e.target.value)} />
                </Form.Group>

                <Button variant="primary" type="submit" className="w-100 py-2 fw-bold" disabled={loading}>
                  {loading ? <><Spinner as="span" animation="border" size="sm" className="me-2" />Entrando...</> : 'Entrar no Sistema'}
                </Button>
              </Form>
            </Tab>

            {/* --- ABA CADASTRO --- */}
            <Tab eventKey="cadastro" title={<span><i className="bi bi-person-plus me-1"></i> Cadastrar</span>}>
              <Form onSubmit={handleCadastro}>
                <Form.Group className="mb-3">
                  <Form.Label>Nome Completo</Form.Label>
                  <Form.Control type="text" placeholder="Seu nome completo" required value={nomeCad} onChange={(e) => setNomeCad(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>CPF</Form.Label>
                  <Form.Control type="text" placeholder="000.000.000-00" required value={cpfCad} onChange={(e) => setCpfCad(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>E-mail</Form.Label>
                  <Form.Control type="email" placeholder="Seu melhor e-mail" required value={emailCad} onChange={(e) => setEmailCad(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>Senha</Form.Label>
                  <Form.Control type="password" placeholder="Crie uma senha forte" required value={senhaCad} onChange={(e) => setSenhaCad(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-4">
                  <Form.Label>Confirme a Senha</Form.Label>
                  <Form.Control type="password" placeholder="Digite a senha novamente" required value={confirmaSenhaCad} onChange={(e) => setConfirmaSenhaCad(e.target.value)} />
                </Form.Group>

                <Button variant="success" type="submit" className="w-100 py-2 fw-bold" disabled={loading}>
                  {loading ? <><Spinner as="span" animation="border" size="sm" className="me-2" />Cadastrando...</> : 'Finalizar Cadastro'}
                </Button>
              </Form>
            </Tab>
          </Tabs>

        </Card.Body>
      </Card>
    </Container>
  );
}