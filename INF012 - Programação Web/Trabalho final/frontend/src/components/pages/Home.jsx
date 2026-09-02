import { Container, Card } from 'react-bootstrap';

export default function Home() {
  return (
    <Container className="mt-4">
      <Card className="p-4 bg-light text-center border-0 shadow-sm">
        <h1><i className="bi bi-house-door text-primary me-2"></i>Página Inicial</h1>
        <p className="lead">Bem-vindo a Biblioteca do IFBA, vá em acervo para ver os nossos livros disponiveis!</p>
      </Card>
    </Container>
  );
}