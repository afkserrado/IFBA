import { useContext, useEffect, useState } from 'react';
import { Alert, Button, Card, Container, Form, Spinner } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../../contexts/AuthContext.js';
import api, { getApiErrorMessage } from '../../services/api';

export default function MinhaConta() {
  const { user, updateUser, logout } = useContext(AuthContext);
  const navigate = useNavigate();
  const [form, setForm] = useState({ nome: user.nome, email: user.email, role: user.role });
  const [loading, setLoading] = useState(true);
  const [salvando, setSalvando] = useState(false);
  const [mensagem, setMensagem] = useState(null);

  useEffect(() => {
    const carregar = async () => {
      try {
        const { data } = await api.get(`/api/v1/usuarios/${user.id}`);
        setForm({ nome: data.nome, email: data.email, role: data.role });
        updateUser(data);
      } catch (error) {
        setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível carregar seu perfil.') });
      } finally {
        setLoading(false);
      }
    };
    carregar();
  }, [updateUser, user.id]); // A sessão é atualizada apenas com a resposta recebida da API.

  const salvar = async (event) => {
    event.preventDefault();
    try {
      setSalvando(true);
      const { data } = await api.put(`/api/v1/usuarios/${user.id}`, form);
      updateUser(data);
      setMensagem({ tipo: 'success', texto: 'Seus dados foram atualizados com sucesso.' });
    } catch (error) {
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível atualizar seus dados.') });
    } finally {
      setSalvando(false);
    }
  };

  const excluir = async () => {
    if (!window.confirm('Deseja realmente excluir sua conta? Esta ação não pode ser desfeita.')) return;
    try {
      await api.delete(`/api/v1/usuarios/${user.id}`);
      logout();
      navigate('/login');
    } catch (error) {
      setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível excluir a conta.') });
    }
  };

  return <Container className="mt-4"><Card className="shadow-sm border-0 mx-auto" style={{ maxWidth: '620px' }}><Card.Body className="p-4"><h2 className="fw-bold"><i className="bi bi-person-gear text-primary me-2" />Minha Conta</h2><p className="text-muted">Atualize seus dados cadastrais ou gerencie a sua conta.</p>{mensagem && <Alert variant={mensagem.tipo} dismissible onClose={() => setMensagem(null)}>{mensagem.texto}</Alert>}{loading ? <div className="text-center py-4"><Spinner animation="border" /></div> : <Form onSubmit={salvar}><Form.Group className="mb-3"><Form.Label>Nome</Form.Label><Form.Control required minLength={2} value={form.nome} onChange={(event) => setForm({ ...form, nome: event.target.value })} /></Form.Group><Form.Group className="mb-3"><Form.Label>E-mail</Form.Label><Form.Control required type="email" value={form.email} onChange={(event) => setForm({ ...form, email: event.target.value })} /></Form.Group><Form.Group className="mb-4"><Form.Label>Perfil</Form.Label><Form.Control value={form.role} disabled /></Form.Group><div className="d-flex justify-content-between gap-3"><Button variant="outline-danger" type="button" onClick={excluir}>Excluir conta</Button><Button variant="primary" type="submit" disabled={salvando}>{salvando ? 'Salvando...' : 'Salvar alterações'}</Button></div></Form>}</Card.Body></Card></Container>;
}
