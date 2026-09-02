import { useContext, useEffect, useState } from 'react';
import { Alert, Badge, Card, Container, Spinner, Tab, Table, Tabs } from 'react-bootstrap';
import { AuthContext } from '../../contexts/AuthContext.js';
import api, { getApiErrorMessage } from '../../services/api';

const formatDate = (date) => date ? new Intl.DateTimeFormat('pt-BR').format(new Date(`${date}T00:00:00`)) : '—';
const formatMoney = (value) => new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(value || 0);

function statusInfo(status) {
  const map = {
    ATIVO: { label: 'Em dia', variant: 'success' },
    ATRASADO: { label: 'Atrasado', variant: 'danger' },
    DEVOLVIDO: { label: 'Devolvido', variant: 'secondary' },
    CANCELADO: { label: 'Cancelado', variant: 'warning' },
  };
  return map[status] || { label: status, variant: 'secondary' };
}

export default function Emprestimos() {
  const { user } = useContext(AuthContext);
  const [abaAtiva, setAbaAtiva] = useState('ativos');
  const [emprestimos, setEmprestimos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [mensagem, setMensagem] = useState(null);

  useEffect(() => {
    const carregar = async () => {
      try {
        setLoading(true);
        const { data } = await api.get(`/api/v1/emprestimos/usuario/${user.id}`);
        setEmprestimos(data);
      } catch (error) {
        setMensagem({ tipo: 'danger', texto: getApiErrorMessage(error, 'Não foi possível carregar seus empréstimos.') });
      } finally {
        setLoading(false);
      }
    };
    carregar();
  }, [user.id]);

  const ativos = emprestimos.filter((item) => ['ATIVO', 'ATRASADO'].includes(item.status));
  const historico = emprestimos.filter((item) => !['ATIVO', 'ATRASADO'].includes(item.status));

  const tabela = (items, isHistory = false) => items.length === 0 ? (
    <Alert variant="light" className="text-center py-5 border"><i className="bi bi-journal-x text-secondary" style={{ fontSize: '3rem' }} /><h5 className="mt-3">Nenhum empréstimo encontrado</h5><p className="text-muted mb-0">Os dados exibidos são carregados diretamente da biblioteca.</p></Alert>
  ) : (
    <div className="table-responsive"><Table hover className="align-middle mb-0"><thead className="table-light"><tr><th>Empréstimo / Livro</th><th>Data Empréstimo</th><th>Prazo Devolução</th>{isHistory && <th>Data de Devolução</th>}<th>Situação</th><th>Multa</th></tr></thead><tbody>{items.map((item) => { const status = statusInfo(item.status); return <tr key={item.id} className={item.status === 'ATRASADO' ? 'table-danger' : ''}><td><strong>Empréstimo #{item.id}</strong><br /><small className="text-muted">Livro ID: #{item.livroId}</small></td><td>{formatDate(item.dataEmprestimo)}</td><td><span className="fw-bold">{formatDate(item.dataPrevistaDevolucao)}</span></td>{isHistory && <td>{formatDate(item.dataDevolucao)}</td>}<td><Badge bg={status.variant} text={status.variant === 'warning' ? 'dark' : 'light'} className="p-2">{status.label}</Badge></td><td><span className={item.valorMulta > 0 ? 'text-danger fw-bold' : 'text-muted'}>{formatMoney(item.valorMulta)}</span></td></tr>; })}</tbody></Table></div>
  );

  return (
    <Container className="mt-4 mb-5">
      <div className="mb-4"><h2 className="fw-bold"><i className="bi bi-journal-check text-primary me-2" />Meus Empréstimos</h2><p className="text-muted small">Área do leitor <strong>{user?.nome}</strong> — Acompanhe seus prazos de devolução, pendências e multas.</p></div>
      {mensagem && <Alert variant={mensagem.tipo} onClose={() => setMensagem(null)} dismissible className="shadow-sm">{mensagem.texto}</Alert>}
      {loading ? <div className="text-center py-5"><Spinner animation="border" variant="primary" /><p className="text-muted mt-3">Carregando empréstimos...</p></div> : <Card className="shadow-sm border-0"><Card.Body className="p-4"><Tabs activeKey={abaAtiva} onSelect={(key) => { setAbaAtiva(key); setMensagem(null); }} className="mb-4"><Tab eventKey="ativos" title={<span><i className="bi bi-book me-1" /> Empréstimos Ativos <Badge bg="primary" pill>{ativos.length}</Badge></span>}>{tabela(ativos)}</Tab><Tab eventKey="historico" title={<span><i className="bi bi-clock-history me-1" /> Histórico de Leitura <Badge bg="secondary" pill>{historico.length}</Badge></span>}>{tabela(historico, true)}</Tab></Tabs></Card.Body></Card>}
    </Container>
  );
}
