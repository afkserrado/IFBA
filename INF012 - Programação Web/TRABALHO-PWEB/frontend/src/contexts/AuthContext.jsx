import { useCallback, useState } from 'react';
import { AuthContext } from './AuthContext';
import api, { clearStoredSession, getApiErrorMessage } from '../services/api';

function tokenExpirado(token) {
  try {
    const payload = JSON.parse(atob(token.split('.')[1].replace(/-/g, '+').replace(/_/g, '/')));
    return !payload.exp || payload.exp * 1000 <= Date.now();
  } catch {
    return true;
  }
}

function recuperarSessao() {
  const token = localStorage.getItem('@Biblioteca:token');
  const storedUser = localStorage.getItem('@Biblioteca:user');
  try {
    if (token && storedUser && !tokenExpirado(token)) return JSON.parse(storedUser);
  } catch {
    // A limpeza abaixo trata também dados antigos ou corrompidos do navegador.
  }
  clearStoredSession();
  return null;
}

export function AuthProvider({ children }) {
  const [user, setUser] = useState(recuperarSessao);

  const cadastrar = async ({ cpf, nome, email, senha }) => {
    try {
      const response = await api.post('/api/v1/usuarios', {
        cpf: cpf.replace(/\D/g, ''),
        nome: nome.trim(),
        email: email.trim(),
        senha,
        role: 'USER',
      });
      return response.data;
    } catch (error) {
      throw new Error(getApiErrorMessage(error, 'Não foi possível cadastrar o usuário.'), { cause: error });
    }
  };

  const login = async (email, senha) => {
    if (!email || !senha) throw new Error('Preencha e-mail e senha.');
    let data;
    try {
      ({ data } = await api.post('/auth/login', { email: email.trim(), senha }));
    } catch (error) {
      throw new Error(getApiErrorMessage(error, 'E-mail ou senha inválidos.'), { cause: error });
    }

    if (!data?.token || !data?.usuario) throw new Error('Resposta de autenticação inválida.');
    localStorage.setItem('@Biblioteca:token', data.token);
    localStorage.setItem('@Biblioteca:tipo', 'Bearer');
    localStorage.setItem('@Biblioteca:user', JSON.stringify(data.usuario));
    setUser(data.usuario);
    return data.usuario;
  };

  const updateUser = useCallback((updatedUser) => {
    localStorage.setItem('@Biblioteca:user', JSON.stringify(updatedUser));
    setUser(updatedUser);
  }, []);

  const logout = useCallback(() => {
    clearStoredSession();
    setUser(null);
  }, []);

  return <AuthContext.Provider value={{ signed: Boolean(user), user, isAdmin: user?.role === 'ADMIN', login, cadastrar, updateUser, logout, loading: false }}>{children}</AuthContext.Provider>;
}
