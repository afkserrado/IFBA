import { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // 1. Ao iniciar, carrega a sessão e garante que exista um ADMIN pré-cadastrado para seus testes!
  useEffect(() => {
    const storedUser = localStorage.getItem('@Biblioteca:user');
    const storedToken = localStorage.getItem('@Biblioteca:token');
    const usuariosSalvos = localStorage.getItem('@Biblioteca:usuarios');

    // Se ainda não existir o banco de usuários no navegador, criamos com o ADMIN padrão
    if (!usuariosSalvos) {
      const adminPadrao = [
        {
          id: 1,
          nome: 'Administrador Principal',
          cpf: '000.000.000-00',
          email: 'admin@biblioteca.com',
          senha: 'admin', // Senha simples para teste
          role: 'ADMIN'
        }
      ];
      localStorage.setItem('@Biblioteca:usuarios', JSON.stringify(adminPadrao));
    }

    if (storedUser && storedToken) {
      setUser(JSON.parse(storedUser));
    }
    setLoading(false);
  }, []);

  // 2. NOVA FUNÇÃO DE CADASTRO: Salva o usuário no nosso "banco" temporário
  const cadastrar = async (novoUsuario) => {
    const usuariosSalvos = JSON.parse(localStorage.getItem('@Biblioteca:usuarios') || '[]');

    // Verifica se o e-mail já existe
    const emailJaExiste = usuariosSalvos.some(u => u.email.toLowerCase() === novoUsuario.email.toLowerCase());
    if (emailJaExiste) {
      throw new Error('Este e-mail já está cadastrado no sistema!');
    }

    // Regra do projeto: Usuário padrão que se registra sozinho vira sempre 'USER'
    const usuarioParaSalvar = {
      id: Date.now(), // Gera um ID único baseado no tempo
      nome: novoUsuario.nome,
      cpf: novoUsuario.cpf,
      email: novoUsuario.email,
      senha: novoUsuario.senha,
      role: 'USER'
    };

    usuariosSalvos.push(usuarioParaSalvar);
    localStorage.setItem('@Biblioteca:usuarios', JSON.stringify(usuariosSalvos));
    return usuarioParaSalvar;
  };

  // 3. LOGIN ATUALIZADO: Agora valida de verdade se o usuário existe!
  const login = async (email, senha) => {
    if (!email || !senha) {
      throw new Error('Por favor, preencha todos os campos!');
    }

    // Pega a lista de usuários salvos
    const usuariosSalvos = JSON.parse(localStorage.getItem('@Biblioteca:usuarios') || '[]');

    // Busca alguém com o mesmo e-mail E mesma senha
    const usuarioEncontrado = usuariosSalvos.find(
      u => u.email.toLowerCase() === email.toLowerCase() && u.senha === senha
    );

    // SE NÃO ENCONTRAR, BLOQUEIA O LOGIN!
    if (!usuarioEncontrado) {
      throw new Error('E-mail ou senha incorretos! Verifique seus dados ou faça o cadastro.');
    }

    // Se encontrou, gera o token e salva a sessão
    const mockToken = `jwt_simulado_${Math.random()}`;
    const dadosSessao = {
      id: usuarioEncontrado.id,
      nome: usuarioEncontrado.nome,
      email: usuarioEncontrado.email,
      role: usuarioEncontrado.role
    };

    localStorage.setItem('@Biblioteca:token', mockToken);
    localStorage.setItem('@Biblioteca:user', JSON.stringify(dadosSessao));
    
    setUser(dadosSessao);
    return dadosSessao;
  };

  const logout = () => {
    localStorage.removeItem('@Biblioteca:token');
    localStorage.removeItem('@Biblioteca:user');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{
      signed: !!user,
      user,
      isAdmin: user?.role === 'ADMIN',
      login,
      cadastrar, // Disponibilizamos a função de cadastrar
      logout,
      loading
    }}>
      {children}
    </AuthContext.Provider>
  );
};